package fi.co_de.identifi_nz.ui.verify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.co_de.identifi_nz.network.IpfsApi
import kotlinx.coroutines.launch

class VerifyViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    /**
     * Call getIpfsPhoto() on init so we can display status immediately.
     */
    init {
        getIpfsString()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [IpfsPhoto] [List] [LiveData].
     */
    private fun getIpfsString() {
        viewModelScope.launch {
            try {
                val listResult = IpfsApi.retrofitService.getPageAsString()
                _status.value = "Success: $listResult"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

}