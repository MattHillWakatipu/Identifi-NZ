package fi.co_de.identifi_nz.data

import androidx.annotation.DrawableRes
import fi.co_de.identifi_nz.R
import fi.co_de.identifi_nz.network.json.IpfsActivity

class Activity(ipfsActivity: IpfsActivity) {
    @DrawableRes
    val imageResourceId = when (ipfsActivity.icon) {
        "verified" -> R.drawable.ic_activity_icon_verified
        "outgoing" -> R.drawable.ic_activity_icon_request_outgoing
        "incoming" -> R.drawable.ic_activity_icon_request_incoming
        "rejected" -> R.drawable.ic_activity_icon_rejected
        else -> error("incorrect icon provided")
    }
    val summary = ipfsActivity.summary
}
