package com.farhanrv.uploadimagedemo.network

import com.farhanrv.uploadimagedemo.network.response.ImageListResponse
import com.farhanrv.uploadimagedemo.network.response.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST(".")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("time") time: RequestBody
    ): Call<UploadResponse>

    @GET(".")
    suspend fun getImageList(): List<ImageListResponse>
}