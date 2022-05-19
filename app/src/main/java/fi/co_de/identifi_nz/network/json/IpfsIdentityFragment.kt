package fi.co_de.identifi_nz.network.json

import com.squareup.moshi.Json

class IpfsIdentityFragment(
    @Json(name = "item_type") val item_type: String,
    @Json(name = "icon") val icon: String,
    @Json(name = "status") val status: String,
    @Json(name = "item_description") val item_description: String,
    //TODO add the image file/link to file as well
)