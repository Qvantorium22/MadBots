package core

sealed class Validate<T>(val data: T? = null, val error : String? = null) {
    class Success<T>(data: T) : Validate<T>(data)
    class Error<T>(message: String?, data: T? = null) : Validate<T>(data, message)
}
