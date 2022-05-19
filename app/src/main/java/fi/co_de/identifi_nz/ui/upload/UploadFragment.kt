package fi.co_de.identifi_nz.ui.upload

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
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
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import fi.co_de.identifi_nz.R
import fi.co_de.identifi_nz.databinding.FragmentUploadBinding
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class UploadFragment : Fragment() {

    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!

    private val uploadViewModel: UploadViewModel by activityViewModels()

    private var imageCapture: ImageCapture? = null

    private lateinit var cameraExecutor: ExecutorService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

        Log.v(TAG, "setting output options")
        // Create output options object which contains file
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                File(
                    Environment.getExternalStorageDirectory().path +
                            "/Pictures/upload.jpg"
                )
            ).build()

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

                    // Save the photo uri in the view model
                    uploadViewModel.setPhotoUri(output.savedUri)

                    // Construct debug message
                    val message = "Photo capture succeeded: ${output.savedUri}"
                    Log.d(TAG, message)

                    // Show message as snackbar
                    Snackbar.make(
                        requireView().findViewById(R.id.coordinator_layout),
                        message,
                        Snackbar.LENGTH_LONG
                    ).show()

                    // Create Dialog fragment to send image to IPFS
                    val dialog = UploadDialogFragment()
                    dialog.showDialog(requireActivity().supportFragmentManager)
                }
            }
        )
        Log.v(TAG, "takePhoto ending")
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