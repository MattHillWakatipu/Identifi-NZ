package fi.co_de.identifi_nz.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
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
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getPageAsString] method.
 */
interface IpfsGatewayService {
    @GET("QmTrLTCQPtfzVzh1fW8QnMRKenMtFXXbaqBMdQ5xDmnDqU")
    suspend fun getPageAsString(): String
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service.
 */
object IpfsApi {
    val retrofitService: IpfsGatewayService by lazy {
        retrofit.create(IpfsGatewayService::class.java)
    }
}