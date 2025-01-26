import kotlin.math.PI
import kotlin.math.pow

open class Circle(_name: String) : Shape(_name) {

    var radius : Double = 0.0

    fun setDimensions(_radius : Double){
        radius = _radius
    }

    override fun getArea(): Double {
        return PI * radius.pow(2)
    }

    override fun printDimensions() {
        println("Radius: $radius")
    }
}