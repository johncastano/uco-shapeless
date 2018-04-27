package uco.fp.shapeless

//Shapeless is a library for GENERIC programming in Scala.

//Whats generic programming? Start explaining types

//Types are very helpful:
  //Prevent bugs
  //Code safety
  //Avoid a kind of errors when whe are coding

case class Employee(name: String, number: Int, manager: Boolean)
case class IceCream(name: String, numCherries: Int, inCone: Boolean)

//   ***Explain relation between two classes above, similar attributes***

//Sometimes types are too specifics and there are situations when we need avoid repetition.
//We want to write a generic operation to serialize to any text type like JSON, XML or CSV.
// We have to write two separate serialization methods

object serializator {
  def employeeCsv (e: Employee): List[String] = List(e. name , e. number . toString , e. manager . toString )
  def iceCreamCsv (c: IceCream): List[String] = List(c. name , c. numCherries . toString , c. inCone . toString )
}

//Generic programming help us avoid code repetition, it converts specific types with things in common to generic types.