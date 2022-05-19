package fi.co_de.identifi_nz.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.co_de.identifi_nz.data.IdentityFragment
import fi.co_de.identifi_nz.network.IpfsApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.http.Url

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
        getIdentityFragment("Qmcdq1igg9ivZQBbhx6yLfQ12sSWiT3jjJixL4yxfmALpZ")
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

    private fun getIdentityFragment(@Url url: String) {
        viewModelScope.launch {
            try {
                // FIXME this is a complete hack, do this properly after testing
                delay(2000)

                val ipfsIdentityFragment = IpfsApi.retrofitService.getIdentityFragment(url)
                Log.v(TAG, "Success: $ipfsIdentityFragment")

                // Convert to Identity Fragment
                val identityFragment = IdentityFragment(ipfsIdentityFragment)

                val oldList: List<IdentityFragment>? = _identityFragments.value
                if (oldList == null) {
                    // Create a list of the identity fragment and set it
                    _identityFragments.value = listOf(identityFragment)
                } else {
                    // Create a mutable list of the existing identity fragments and add to it
                    val mutableList = oldList.toMutableList()
                    mutableList.add(identityFragment)
                    _identityFragments.value = mutableList
                }

            } catch (e: Exception) {
                Log.e(TAG, "Failure: ${e.message}")
            }
        }
    }
}