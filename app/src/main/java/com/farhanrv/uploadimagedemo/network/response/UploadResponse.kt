package com.farhanrv.uploadimagedemo.network.response

import com.google.gson.annotations.SerializedName

data class UploadResponse(
    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("status_message")
    val status_message: String
)
