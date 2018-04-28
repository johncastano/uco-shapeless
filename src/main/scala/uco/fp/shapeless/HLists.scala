package uco.fp.shapeless

import shapeless.{ HList, ::, HNil }
object HLists {

  val product: String :: Int :: Boolean :: HNil =
    "Sunday" :: 1 :: false :: HNil

  val first  = product.head
  val second = product.tail.head
  val rest   = product.tail.tail

  val newProduct: Long :: String :: Int :: Boolean :: HNil =
    42L :: product

}
