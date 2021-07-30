package core

interface MadMatrixValidator {
    fun validate(point: Point): ValidateResult
}