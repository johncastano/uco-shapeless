package uco.fp.shapeless

import shapeless.{ Generic, HList, ::, HNil }

trait CsvEncoder[A] {
  def encode(value: A): List[String]
}

object CsvEncoder {

  def createEncoder[A](func: A => List[String]): CsvEncoder[A] =
    new CsvEncoder[A] {
      def encode(value: A): List[String] = func(value)
    }

  implicit val stringEncoder: CsvEncoder[String] =
    createEncoder(str => List(str))

  implicit val intEncoder: CsvEncoder[Int] =
    createEncoder(num => List(num.toString))

  implicit val booleanEncoder: CsvEncoder[Boolean] =
    createEncoder(bool => List(if (bool) "yes" else "no"))

  implicit val hnilEncoder: CsvEncoder[HNil] =
    createEncoder(hnil => Nil)

  implicit def hlistEncoder[H, T <: HList](
      implicit
      hEncoder: CsvEncoder[H],
      tEncoder: CsvEncoder[T]
  ): CsvEncoder[H :: T] =
    createEncoder {
      case h :: t => hEncoder.encode(h) ++ tEncoder.encode(t)
    }

  implicit def genericEncoder[A, R](
      implicit
      gen: Generic[A] { type Repr = R },
      enc: CsvEncoder[R]
  ): CsvEncoder[A] =
    createEncoder(a => enc.encode(gen.to(a)))

}

object Test extends App {

  def encodeCsv[A](value: A)(implicit enc: CsvEncoder[A]): List[String] =
    enc.encode(value)

  def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
    values.map(value => enc.encode(value).mkString(",")).mkString("\n")

  val iceCreams: List[IceCream] =
    List(
      IceCream("Vanila", 3, false),
      IceCream("Chocolate", 2, true),
      IceCream("Brownie", 5, true)
    )

  //Example for iceCreamEncoder
  println("IceCreams as CSV:\n" + writeCsv(iceCreams))
}
