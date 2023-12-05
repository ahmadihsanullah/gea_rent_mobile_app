package il.massive.gea_rent.repository

import il.massive.gea_rent.api.ApiConfig
import okhttp3.RequestBody

class UserRepository {
    private val client = ApiConfig.getApiService()
    suspend fun userRegister(requestBody: RequestBody) = client.userRegister(requestBody)
}