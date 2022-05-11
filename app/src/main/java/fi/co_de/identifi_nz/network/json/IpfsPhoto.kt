package fi.co_de.identifi_nz.network.json

import com.squareup.moshi.Json

class IpfsPhoto(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String
)