package fi.co_de.identifi_nz.ui.verify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VerifyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is verify Fragment"
    }
    val text: LiveData<String> = _text
}