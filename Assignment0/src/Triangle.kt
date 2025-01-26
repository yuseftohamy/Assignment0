import kotlin.math.sqrt

open class Triangle(_name: String) : Shape(_name) {
    var side1 : Double = 0.0
    var side2 : Double = 0.0
    var side3 : Double = 0.0

    fun setDimensions(_side1 : Double, _side2 : Double, _side3 : Double){
        side1 = _side1
        side2 = _side2
        side3 = _side3
    }

    override fun getArea(): Double {
        val halfPerimeter = (side1 + side2 + side3) / 2
        return sqrt(halfPerimeter * (halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3))
    }

    override fun printDimensions() {
        println("Side 1: $side1")
        println("Side 2: $side2")
        println("Side 3: $side3")
    }
}