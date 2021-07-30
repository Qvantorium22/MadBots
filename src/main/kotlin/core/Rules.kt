package core

interface Rules {
    fun validate(point: Point): Validate<String>
}