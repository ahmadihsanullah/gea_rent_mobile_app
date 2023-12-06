package il.massive.gea_rent.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import il.massive.gea_rent.api.RequestState
import il.massive.gea_rent.model.GetCurrentUserResponse
import il.massive.gea_rent.model.UserLoginResponse
import il.massive.gea_rent.model.UserLogoutResponse
import il.massive.gea_rent.model.UserResponse
import il.massive.gea_rent.repository.UserRepository
import okhttp3.RequestBody
import retrofit2.HttpException

//proses request disini
class UserViewModel: ViewModel() {
    private val repo : UserRepository = UserRepository()

    fun userRegister(requestBody: RequestBody): LiveData<RequestState<UserResponse>> = liveData{
        try {
            emit(RequestState.loading)
            val response = repo.userRegister(requestBody)
            emit(RequestState.success(response))
        }catch (e: HttpException){
            emit(RequestState.error("Failed to register user: ${e.response()?.errorBody()?.string()}", ))
        }
    }

    fun userLogin(requestBody: RequestBody): LiveData<RequestState<UserLoginResponse>> = liveData{
        try {
            emit(RequestState.loading)
            val response = repo.userLogin(requestBody)
            emit(RequestState.success(response))
        }catch (e: HttpException){
            emit(RequestState.error("Failed to login user: ${e.response()?.errorBody()?.string()}", ))
        }
    }

    fun getCurrentUser(authorization:String): LiveData<RequestState<GetCurrentUserResponse>> = liveData{
        try {
            emit(RequestState.loading)
            val response = repo.getUserCurrent(authorization)
            emit(RequestState.success(response))
        }catch (e: HttpException){
            emit(RequestState.error("Failed to login user: ${e.response()?.errorBody()?.string()}", ))
        }
    }

    fun logout(authorization:String): LiveData<RequestState<UserLogoutResponse>> = liveData{
        try {
            emit(RequestState.loading)
            val response = repo.logout(authorization)
            emit(RequestState.success(response))
        }catch (e: HttpException){
            emit(RequestState.error("Failed to logout : ${e.response()?.errorBody()?.string()}", ))
        }
    }
}