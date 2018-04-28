package uco.fp.shapeless

trait CsvEncoder[A] {
  def encode(value: A): List[String]
}

object Library {

  implicit val employeeEncoder: CsvEncoder[Employee] =
    e => List(e.name, e.number.toString, if (e.manager) "yes" else "no")

  //Another way to write the code above

  /*
   implicit val employeeEncoder: CsvEncoder[Employee] =
     new CsvEncoder[Employee] {
       def encode(e: Employee): List[String] =
         List(e.name, e.number.toString, if(e.manager) "yes" else "no")
     }
   */

  def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
    values.map(value => enc.encode(value).mkString(",")).mkString("\n")

  val employees: List[Employee] =
    List(
      Employee("Bill", 1, true),
      Employee("Peter", 2, false),
      Employee("Milton", 3, false)
    )

  val we: String = writeCsv(employees)
  //String =
  // Bill,1,yes
  // Peter,2,no
  // Milton,3,no

  implicit val iceCreamEncoder: CsvEncoder[IceCream] =
    i => List(i.name, i.numCherries.toString, if (i.inCone) "yes" else "no")

  //Another way to write the code above
  /*
   implicit val iceCreamEncoder: CsvEncoder[IceCream] =
     new CsvEncoder[IceCream] {
       def encode(i: IceCream): List[String] =
         List(i.name, i.numCherries.toString, if (i.inCone) "yes" else "no")
     }
   */

  val iceCreams: List[IceCream] =
    List(
      IceCream("Sundae", 1, false),
      IceCream("Cornetto", 0, true),
      IceCream("Banana Split", 0, false)
    )

  val wic = writeCsv(iceCreams)

  implicit def pairEncoder[A, B](
      implicit aEncoder: CsvEncoder[A],
      bEncoder: CsvEncoder[B]
  ): CsvEncoder[(A, B)] =
    new CsvEncoder[(A, B)] {
      def encode(pair: (A, B)): List[String] = {
        val (a, b) = pair
        aEncoder.encode(a) ++ bEncoder.encode(b)
      }
    }

  writeCsv[(Employee, IceCream)](List((Employee("Juan", 3, false), IceCream("Chocolate", 4, true))))

}
