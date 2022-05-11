package fi.co_de.identifi_nz.network.json

import com.squareup.moshi.Json

class IpfsActivity(
    @Json(name = "item_type") val item_type: String,
    @Json(name = "icon") val icon: String,
    @Json(name = "summary") val summary: String
)