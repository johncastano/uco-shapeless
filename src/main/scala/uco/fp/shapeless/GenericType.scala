package uco.fp.shapeless

import shapeless.{ Generic, HNil }

import shapeless.Generic.Aux

object GenericType {

  val iceCreamGen: Aux[IceCream, shapeless.::[String, shapeless.::[Int, shapeless.::[Boolean, HNil]]]] =
    Generic[IceCream]

  val iceCream = IceCream("Sundae", 1, false)

  val repr = iceCreamGen.to(iceCream)

  val iceCream2 = iceCreamGen.from(repr)

  val employee = Generic[Employee]
    .from(
      Generic[IceCream]
        .to(iceCream)
    )

  val tupleGen = Generic[(String, Int, Boolean)]

  tupleGen.to(("Hello", 123, true))

}
