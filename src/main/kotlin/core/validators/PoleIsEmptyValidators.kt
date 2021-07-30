package core.validators

import core.MadMatrixValidator
import core.Matrix
import core.Point
import core.ValidateResult

class PoleIsEmptyValidators(val getPole: (Point) -> Int) : MadMatrixValidator {
    override fun validate(point: Point): ValidateResult {
        return if (getPole(point) == 0)
            ValidateResult.Success
        else
            ValidateResult.Error("Поле занято")
    }
}