package fi.co_de.identifi_nz.ui.verify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.co_de.identifi_nz.network.IpfsApi
import fi.co_de.identifi_nz.network.json.IpfsIdentityFragment
import kotlinx.coroutines.launch

class VerifyViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val testingURL: String = ""

    /**
     * Call getIpfsPhoto() on init so we can display status immediately.
     */
    init {
        getIpfsString()
    }

    /**
     * Gets status information from the IPFS API Retrofit service.
     */
    private fun getIpfsString() {
        viewModelScope.launch {
            try {
                val identityFragment = IpfsIdentityFragment(
                    item_type = "IdentityFragment",
                    icon = "document",
                    status = "pending",
                    item_description = "upload test"
                )
                val result = IpfsApi.retrofitService.uploadIdentityFragment(
                    testingURL,
                    identityFragment
                )
                _status.value = "Success: $result"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}