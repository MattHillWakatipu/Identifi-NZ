package fi.co_de.identifi_nz.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.co_de.identifi_nz.data.IdentityFragment
import fi.co_de.identifi_nz.network.IpfsApi
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    companion object {
        private val TAG = ProfileViewModel::class.java.simpleName
    }

    private val _identityFragments = MutableLiveData<List<IdentityFragment>>()
    val identityFragments: LiveData<List<IdentityFragment>> = _identityFragments

    /**
     * Call getIdentityFragmentsFromIpfs on init so we can display status immediately.
     */
    init {
        getIdentityFragmentsFromIpfs()
    }

    /**
     * Gets identity fragment information from IPFS API Retrofit service and updates the
     * LiveData List.
     */
    private fun getIdentityFragmentsFromIpfs() {
        viewModelScope.launch {
            try {
                val listIpfsIdentityFragments = IpfsApi.retrofitService.getIpfsIdentityFragments()
                Log.v(TAG, "Success: $listIpfsIdentityFragments")

                // Create list of Identity Fragment from list of Ipfs Identity Fragment
                val listIdentityFragments = mutableListOf<IdentityFragment>()
                for (ipfsActivity in listIpfsIdentityFragments) {
                    listIdentityFragments.add(IdentityFragment(ipfsActivity))
                }
                _identityFragments.value = listIdentityFragments

            } catch (e: Exception) {
                Log.e(TAG, "Failure: ${e.message}")
            }
        }
    }
}