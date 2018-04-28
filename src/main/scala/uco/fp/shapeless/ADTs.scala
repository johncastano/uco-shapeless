package uco.fp.shapeless

sealed trait Shape
final case class Rectangle(width: Double, height: Double) extends Shape
final case class Circle(radius: Double)                   extends Shape

object GenericShapes {
  type RectangleGen = (Double, Double)
  type CircleGen    = Double
  type ShapeGen     = Either[RectangleGen, CircleGen]
}