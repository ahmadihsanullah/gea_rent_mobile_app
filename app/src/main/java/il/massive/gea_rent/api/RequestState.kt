package il.massive.gea_rent.api

sealed class RequestState<out R> private constructor(){
    data class success<out T>(val data: T): RequestState<T>()
    data class error(val message: String): RequestState<Nothing>()

    object loading : RequestState<Nothing>()
}