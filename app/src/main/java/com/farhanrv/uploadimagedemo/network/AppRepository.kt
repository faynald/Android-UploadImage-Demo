package com.farhanrv.uploadimagedemo.network

import com.farhanrv.uploadimagedemo.domain.ImageList
import com.farhanrv.uploadimagedemo.network.api.ApiResource
import com.farhanrv.uploadimagedemo.network.api.ApiResponse
import com.farhanrv.uploadimagedemo.network.api.NetworkDataSource
import com.farhanrv.uploadimagedemo.network.response.ImageListResponse
import com.farhanrv.uploadimagedemo.utils.ImageListMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AppRepository(private val network: NetworkDataSource) {
    fun getImageList(): Flow<ApiResource<List<ImageList>>> =
        object : NetworkBoundResource<List<ImageList>, List<ImageListResponse>>() {
            override suspend fun emitFromNetwork(data: List<ImageListResponse>): Flow<List<ImageList>> =
                network.getImageList().map {
                    ImageListMapper.mapResponseToDomain(data)
                }

            override fun shouldFetch(): Boolean = true // TODO : check internet connection

            override suspend fun createCall(): Flow<ApiResponse<List<ImageListResponse>>> =
                network.getImageList()

        }.asFlow()

}