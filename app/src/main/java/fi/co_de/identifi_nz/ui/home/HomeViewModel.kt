package fi.co_de.identifi_nz.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.co_de.identifi_nz.data.Activity
import fi.co_de.identifi_nz.network.IpfsApi
import kotlinx.coroutines.launch

const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    private val _activities = MutableLiveData<List<Activity>>()
    val activities: LiveData<List<Activity>> = _activities

    /**
     * Call getActivitiesFromIpfs on init so we can display status immediately.
     */
    init {
        getActivitiesFromIpfs()
    }

    /**
     * Gets activity information from IPFS API Retrofit service and updates the LiveData List.
     */
    private fun getActivitiesFromIpfs() {
        viewModelScope.launch {
            try {
                val listIpfsActivities = IpfsApi.retrofitService.getIpfsActivities()
                Log.v(TAG, "Success: $listIpfsActivities")

                // Create list of Activity from list of Ipfs Activities
                val listActivity = mutableListOf<Activity>()
                for (ipfsActivity in listIpfsActivities) listActivity.add(Activity(ipfsActivity))
                _activities.value = listActivity

            } catch (e: Exception) {
                Log.e(TAG, "Failure: ${e.message}")
            }
        }
    }
}