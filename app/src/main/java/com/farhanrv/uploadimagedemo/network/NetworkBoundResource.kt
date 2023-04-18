package com.farhanrv.uploadimagedemo.network

import android.util.Log
import com.farhanrv.uploadimagedemo.network.api.ApiResource
import com.farhanrv.uploadimagedemo.network.api.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<ApiResource<ResultType>> = flow {
        emit(ApiResource.Loading())
//        val dbSource = loadFromDB().first()
        // if (shouldFetch(dbSource)) {
        if (shouldFetch()) { // TODO: should fetch when internet connection is true
            emit(ApiResource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
//                    saveCallResult(apiResponse.data)
//                    emitAll(loadFromDB().map { ApiResource.Success(it) })
                    emitAll(emitFromNetwork(apiResponse.data).map { ApiResource.Success(it) })
                }
                is ApiResponse.Empty -> {
//                    emitAll(loadFromDB().map { ApiResource.Success(it) })
//                    emitAll(emitFromNetwork(apiResponse.data).map { ApiResource.Success(it) })
                    Log.e("NetworkBoundResource", "ApiResponse.Empty")
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(ApiResource.Error(apiResponse.errorMessage))
                }
            }
        } else {
//            emitAll(loadFromDB().map { ApiResource.Success(it) })
            Log.e("Internet Connection", "False")
        }
    }

    protected open fun onFetchFailed() {}

//    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract suspend fun emitFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract fun shouldFetch(): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

//    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<ApiResource<ResultType>> = result
}