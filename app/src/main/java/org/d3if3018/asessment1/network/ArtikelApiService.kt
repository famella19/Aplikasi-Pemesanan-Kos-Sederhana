package org.d3if3018.asessment1.network

import android.media.Image
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3018.asessment1.model.Artikel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/famella19/artikel-json/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ArtikelApiService {
    @GET("artikel.json")
    suspend fun getArtikel() : List<Artikel>
}

object ArtikelApi {
    val service: ArtikelApiService by lazy {
        retrofit.create(ArtikelApiService::class.java)
    }

    fun getArtikelUrl(image: String) : String {
        return "$BASE_URL$image"
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED}