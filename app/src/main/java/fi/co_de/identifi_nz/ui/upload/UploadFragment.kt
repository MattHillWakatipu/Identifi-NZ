package fi.co_de.identifi_nz.ui.upload

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fi.co_de.identifi_nz.databinding.FragmentUploadBinding
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class UploadFragment : Fragment() {

    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!

    private var imageCapture: ImageCapture? = null
    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null

    private lateinit var cameraExecutor: ExecutorService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val uploadViewModel =
            ViewModelProvider(this)[UploadViewModel::class.java]

        _binding = FragmentUploadBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Check if we have permission to use camera, if not request permission
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissionLauncher.launch(REQUIRED_PERMISSIONS)
        }

        // Set up click listeners
        binding.imageCaptureButton.setOnClickListener { takePhoto() }
        binding.videoCaptureButton.setOnClickListener { captureVideo() }

        cameraExecutor = Executors.newSingleThreadExecutor()

        return root
    }


    /**
     * Check if we have the required permissions to continue use of the app.
     *
     * @return True if we have the permissions, otherwise False.
     */
    private fun allPermissionsGranted(): Boolean = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * TODO
     */
    private fun takePhoto() {
        Log.v(TAG, "takePhoto called")
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        Log.v(TAG, "create media store entry")
        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                Log.v(TAG, "Check")
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        Log.v(TAG, "setting output options")
        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                requireActivity().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        Log.v(TAG, "taking picture")
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val message = "Photo capture succeeded: ${output.savedUri}"
                    Toast.makeText(
                        requireActivity().baseContext,
                        message,
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d(TAG, message)
                }
            }
        )
        Log.v(TAG, "takePhoto ending")
    }

    private fun captureVideo() {
        TODO()
    }

    /**
     * TODO
     */
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also { it.setSurfaceProvider(binding.viewFinder.surfaceProvider) }

            imageCapture = ImageCapture.Builder().build()

            // Select back camera as default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (e: Exception) {
                Log.e(TAG, "Use case binding failed", e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }


    /**
     *  Register the permissions callback, which handles the user's response to the
     *  system permissions dialog. Save the return value, an instance of ActivityResultLauncher.
     */
    private val requestPermissionLauncher =
        registerForActivityResult(
            RequestMultiplePermissions()
        ) { permissions ->
            val granted = permissions.entries.all {
                it.value == true
            }
            if (granted) {
                startCamera()
            } else {
                // TODO("this should be a dialog instead of a toast")
                Toast.makeText(
                    context,
                    "Camera permissions are required to upload to Identifi-NZ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    /**
     * Destroy the view, call the shutdown method on the [cameraExecutor] then clear [_binding].
     */
    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
        _binding = null
    }

    companion object {
        private const val TAG = "UploadFragment"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private val REQUIRED_PERMISSIONS = mutableListOf(
            Manifest.permission.CAMERA,
        ).apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
    }

}