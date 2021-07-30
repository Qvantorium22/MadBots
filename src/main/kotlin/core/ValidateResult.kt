package core

sealed class ValidateResult {
    object Success : ValidateResult()
    class Error(data: String? = null) : ValidateResult()
}
