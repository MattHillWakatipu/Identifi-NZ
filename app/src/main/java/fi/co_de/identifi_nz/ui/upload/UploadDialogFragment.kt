package fi.co_de.identifi_nz.ui.upload

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import fi.co_de.identifi_nz.databinding.DialogUploadBinding
import fi.co_de.identifi_nz.network.IpfsApi
import fi.co_de.identifi_nz.network.json.IpfsIdentityFragment
import kotlinx.coroutines.launch
import java.io.File
import java.util.*


class UploadDialogFragment : DialogFragment() {

    private var _binding: DialogUploadBinding? = null
    private val binding get() = _binding!!

    // This is empty as it utilises the base URL
    private val uploadUrl: String = ""

    /**
     * The system calls this to get the DialogFragment's layout, regardless
     * of whether it's being displayed as a dialog or an embedded fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout to use as dialog or embedded fragment
        _binding = DialogUploadBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.uploadDialogCancel.setOnClickListener { dismiss() }
        binding.uploadDialogSend.setOnClickListener { uploadDataToIpfs() }

        return root
    }

    fun showDialog(fragmentManager: FragmentManager) {
        val newFragment = UploadDialogFragment()
        // newFragment.setStyle(STYLE_NORMAL, R.style.CustomDialog)
        val transaction = fragmentManager.beginTransaction()
        // For a little polish, specify a transition animation
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        // To make it fullscreen, use the 'content' root view as the container
        // for the fragment, which is always the root view for the activity
        transaction
            .add(android.R.id.content, newFragment)
            .addToBackStack(null)
            .commit()
    }

    fun setImage(savedUri: Uri?) {
        Log.v(TAG, "In set image")

        Log.v(TAG, savedUri.toString())
        Log.v(TAG, savedUri?.path.toString())

        var imgFile = savedUri?.path?.let { File(it) }

        imgFile = File("/sdcard/Pictures/1652413318817.jpg")

        if (imgFile != null && imgFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            binding.itemImage.setImageBitmap(bitmap)
        }
    }

    /**
     * Upload data contained within the dialog to IPFS.
     */
    private fun uploadDataToIpfs() {
        lifecycleScope.launch {

            val identityFragment = createIdentityFragment()

            // Upload the identity fragment to IPFS with retrofit API
            val message = try {
                val result = IpfsApi.retrofitService.uploadIdentityFragment(
                    uploadUrl,
                    identityFragment
                )
                "Success: $result"
            } catch (e: Exception) {
                "Failure: ${e.message}"
            }

            // Create Snackbar containing results of IPFS upload
            Snackbar.make(
                requireView(),
                message,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Create an identity fragment from the information contained within the dialog.
     *
     * @return An IpfsIdentityFragment containing the information in the dialog.
     */
    private fun createIdentityFragment(): IpfsIdentityFragment {
        // Get the item type from dropdown and set the icon accordingly
        val itemType = binding.uploadDialogItemType.editText?.text.toString()
        val icon = itemType.lowercase(Locale.getDefault())

        // Get the item description from the text field
        val itemDescription = binding.uploadDialogItemDescription.editText?.text.toString()

        // Create the identity fragment to upload
        return IpfsIdentityFragment(
            item_type = itemType,
            icon = icon,
            status = "pending",
            item_description = itemDescription
        )
    }

    companion object {
        private const val TAG = "UploadDialogFragment"
    }
}