package core

sealed class ValidateResult {
    object Success : ValidateResult()
    class Error(val data: String? = null) : ValidateResult(){
        override fun toString(): String {
            return data.toString()
        }
    }
}
