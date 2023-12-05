package il.massive.gea_rent.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import il.massive.gea_rent.api.RequestState
import il.massive.gea_rent.model.UserResponse
import il.massive.gea_rent.repository.UserRepository
import retrofit2.HttpException

//proses request disini
class UserViewModel: ViewModel() {
    private val repo : UserRepository = UserRepository()

    fun userRegister(username: String,name:String, password:String): LiveData<RequestState<UserResponse>> = liveData{
        try {
            val response = repo.userRegister(username, name, password)
            emit(RequestState.loading)
            emit(RequestState.success(response))
        }catch (e: HttpException){
            emit(RequestState.error(e.response()?.errorBody().toString()))
        }

    }
}