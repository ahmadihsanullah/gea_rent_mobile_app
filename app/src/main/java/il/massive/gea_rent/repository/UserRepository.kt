package il.massive.gea_rent.repository

import il.massive.gea_rent.api.ApiConfig

class UserRepository {
    private val client = ApiConfig.getApiService()
    suspend fun userRegister(username: String,name:String, password:String) = client.userRegister(username,name,password)
}