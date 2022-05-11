package fi.co_de.identifi_nz.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fi.co_de.identifi_nz.network.json.IpfsActivity
import fi.co_de.identifi_nz.network.json.IpfsIdentityFragment
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://co-de-fi.mypinata.cloud/ipfs/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getIpfsActivities] method.
 */
interface IpfsGatewayService {
    @GET("QmSB44zJDQ4mDY9Ks5FTeMVw7LyHJLsJUHqWieK5QLkFv5/Activities")
    suspend fun getIpfsActivities(): List<IpfsActivity>

    @GET("QmbvMAcCPm9wqZA3B4mWRnDHuM2TxerjaAxn8a3TqT4oKu/IdentityFragments")
    suspend fun getIpfsIdentityFragments(): List<IpfsIdentityFragment>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service.
 */
object IpfsApi {
    val retrofitService: IpfsGatewayService by lazy {
        retrofit.create(IpfsGatewayService::class.java)
    }
}