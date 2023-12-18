package il.massive.gea_rent.repository

import il.massive.gea_rent.api.ApiConfig
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UserRepository {
    private val client = ApiConfig.getApiService()
    suspend fun userRegister(requestBody: RequestBody) = client.userRegister(requestBody)
    suspend fun userLogin(requestBody: RequestBody) = client.userLogin(requestBody)
    suspend fun getUserCurrent(authorization: String) = client.getUserCurrent(authorization)
    suspend fun logout(authorization: String) = client.logout(authorization)
    suspend fun updateUser(
        authorization: String,
        profile: MultipartBody.Part?, requestBody: RequestBody) = client.updateUser(authorization, profile, requestBody)
}