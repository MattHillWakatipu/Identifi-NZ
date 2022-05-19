package fi.co_de.identifi_nz.ui.upload

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UploadViewModel : ViewModel() {

    private val _photoUri = MutableLiveData<Uri>().apply {
        value = null
    }
    val photoUri: LiveData<Uri> = _photoUri

    fun setPhotoUri(savedUri: Uri?) {
        _photoUri.value = savedUri
    }
}