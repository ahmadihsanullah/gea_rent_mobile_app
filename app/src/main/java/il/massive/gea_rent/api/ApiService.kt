package il.massive.gea_rent.api

import il.massive.gea_rent.model.UserResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users")
    suspend fun userRegister(
        @Body RequestBody: RequestBody
    ): UserResponse
}