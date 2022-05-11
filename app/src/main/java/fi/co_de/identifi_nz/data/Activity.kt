package fi.co_de.identifi_nz.data

import androidx.annotation.DrawableRes
import fi.co_de.identifi_nz.R
import fi.co_de.identifi_nz.network.json.IpfsActivity

class Activity(IpfsActivity: IpfsActivity) {
    @DrawableRes
    val imageResourceId = when (IpfsActivity.icon) {
        "verified" -> R.drawable.ic_identity_fragment_status_verified
        "outgoing" -> R.drawable.ic_activity_icon_request_outgoing
        "incoming" -> R.drawable.ic_activity_icon_request_incoming
        "rejected" -> R.drawable.ic_activity_icon_rejected
        else -> error("incorrect icon provided")
    }
    val string = IpfsActivity.summary
}
