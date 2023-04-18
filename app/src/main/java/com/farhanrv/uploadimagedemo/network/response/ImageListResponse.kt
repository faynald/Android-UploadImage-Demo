package com.farhanrv.uploadimagedemo.network.response

import com.google.gson.annotations.SerializedName

data class ImageListResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("time")
    val time: String
)
