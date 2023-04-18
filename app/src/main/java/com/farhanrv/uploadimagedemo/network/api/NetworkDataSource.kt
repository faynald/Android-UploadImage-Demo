package com.farhanrv.uploadimagedemo.network.api

import android.util.Log
import com.farhanrv.uploadimagedemo.network.ApiService
import com.farhanrv.uploadimagedemo.network.response.ImageListResponse
import com.farhanrv.uploadimagedemo.network.response.UploadResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkDataSource(private val apiService: ApiService) {

    fun uploadImage(
        file: MultipartBody.Part,
        name: RequestBody,
        time: RequestBody
    ) {
        val call : Call<UploadResponse> = apiService.uploadImage(file, name, time)
//        val call : Call<UploadResponse> = apiService.uploadImage(file)
        call.enqueue(object : Callback<UploadResponse> {
            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                Log.e("RemoteDataSource upload Success", response.code().toString())
            }
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                Log.e("RemoteDataSource upload", "Error$t")
                t.printStackTrace()
            }
        })
        apiService.uploadImage(file, name, time)
//        apiService.uploadImage(file)
    }

    suspend fun getImageList(): Flow<ApiResponse<List<ImageListResponse>>> {
        return flow {
            try {
                val response = apiService.getImageList()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}