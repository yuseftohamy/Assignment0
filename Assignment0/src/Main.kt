import java.util.*

fun main() {
    fun main(){
        val reader = Scanner(System.`in`)

        val triangle = Triangle("triangle")
        print("Enter the length of the first side of the triangle: ")
        val firstSide : Double = reader.nextDouble()

        print("Enter the length of the second side of the triangle: ")
        val secondSide : Double = reader.nextDouble()

        print("Enter the length of the third side of the triangle: ")
        val thirdSide : Double = reader.nextDouble()
        triangle.setDimensions(firstSide, secondSide, thirdSide)

        val square = Square("square")
        print("Enter the length of the square: ")
        val length : Double = reader.nextDouble()

        print("Enter the height of the square: ")
        val height : Double = reader.nextDouble()
        square.setDimensions(length, height)

        val circle = Circle("circle")
        print("Enter the radius of the circle: ")
        val radius : Double = reader.nextDouble()
        circle.setDimensions(radius)

        val equilateralTriangle = EquilateralTriangle("equilateralTriangle")
        print("Enter the side for the equilateral triangle: ")
        val side : Double = reader.nextDouble()
        equilateralTriangle.setDimensions(side)

        println("Triangle")
        triangle.printDimensions()
        println("Area: ${triangle.getArea()}")

        println("Square")
        square.printDimensions()
        println("Area: ${square.getArea()}")

        println("Circle")
        circle.printDimensions()
        println("Area: ${circle.getArea()}")

        println("Equilateral Triangle")
        equilateralTriangle.printDimensions()
        println("Area: ${equilateralTriangle.getArea()}")
    }

    main()
}