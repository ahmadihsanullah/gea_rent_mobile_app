package il.massive.gea_rent.api

import il.massive.gea_rent.model.GetCurrentUserResponse
import il.massive.gea_rent.model.LogoutUserResponse
import il.massive.gea_rent.model.UserLoginResponse
import il.massive.gea_rent.model.UserRegisterResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("users")
    suspend fun userRegister(
        @Body RequestBody: RequestBody
    ): UserRegisterResponse

    @POST("users/login")
    suspend fun userLogin(
        @Body RequestBody: RequestBody
    ): UserLoginResponse

    @GET("users/current")
    suspend fun getUserCurrent(
        @Header("Authorization") authorization: String
    ):GetCurrentUserResponse
    @DELETE("users/logout")
    suspend fun logout(
        @Header("Authorization") authorization: String
    ):LogoutUserResponse
}