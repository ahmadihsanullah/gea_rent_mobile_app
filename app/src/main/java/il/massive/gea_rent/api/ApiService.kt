package il.massive.gea_rent.api

import il.massive.gea_rent.model.UserResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("users")
    suspend fun userRegister(
        @Query("username") username: String,
        @Query("name") name: String,
        @Query("password") password: String,
    ): UserResponse
}