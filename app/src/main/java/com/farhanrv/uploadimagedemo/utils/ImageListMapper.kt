package com.farhanrv.uploadimagedemo.utils

import com.farhanrv.uploadimagedemo.domain.ImageList
import com.farhanrv.uploadimagedemo.network.response.ImageListResponse

object ImageListMapper {
    fun mapResponseToDomain(input: List<ImageListResponse>): List<ImageList> {
        val imageList = ArrayList<ImageList>()
        input.map {
            val image = ImageList(
                id = it.id,
                userId = it.userId,
                name = it.name,
                time = it.time
            )
            imageList.add(image)
        }
        return imageList
    }
}