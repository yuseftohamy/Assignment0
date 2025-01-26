import kotlin.math.*

open class EquilateralTriangle(_name: String) : Triangle(_name) {
    var side : Double = 0.0

    fun setDimensions(_side : Double){
        side = _side
    }

    override fun getArea(): Double {
        return (sqrt(3.0) / 4) * side.pow(2)
    }

    override fun printDimensions() {
        println("Side: $side")
    }
}