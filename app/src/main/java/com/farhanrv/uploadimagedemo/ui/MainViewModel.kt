package com.farhanrv.uploadimagedemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.uploadimagedemo.network.AppRepository
import com.farhanrv.uploadimagedemo.network.api.NetworkDataSource
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class MainViewModel(private val network: NetworkDataSource, private val repository: AppRepository) : ViewModel() {

    val getImageList = repository.getImageList().asLiveData()

    fun uploadImage(file: File, name: String, time: String ) {

        val fileRB: RequestBody = RequestBody.create(
            "File/*".toMediaType(),
            file
        )

        val nameRB = "profile".toRequestBody("text/plain;charset=utf-8".toMediaType())

        val timeRB = time.toRequestBody("text/plain;charset=utf-8".toMediaType())

        val fileNew = MultipartBody.Part.createFormData("shitfile", file.name, fileRB)

        network.uploadImage(fileNew, nameRB, timeRB)
    }
}