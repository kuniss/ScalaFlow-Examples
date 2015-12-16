package de.grammarcraft.scala.flow.examples.convertroman.operations.conversions

import de.grammarcraft.scala.flow.InputPort
import de.grammarcraft.scala.flow.OutputPort
import de.grammarcraft.scala.flow.FunctionUnit

class ConvertToRoman extends FunctionUnit("ConvertToRoman")
  with InputPort[Int]
  with OutputPort[String]
{
    def processInput(arabicNumber: Int) {
        val factors = factorizeArabicNumber(arabicNumber)
        val digits = mapFactorsToDigits(factors)
        output <= { digits mkString }
    }
    
    val MAP: Map[Int,String] = Map(
        1000 -> "M", 900 -> "CM", 500 -> "D", 400 -> "CD",
        100 -> "C", 90 -> "XC", 50 -> "L", 40 -> "XL",
        10 -> "X", 9 -> "IX", 5 -> "V", 4 -> "IV",
        1 -> "I"
    )
    
    def factorizeArabicNumber(arabicNumberIn: Int): List[Int] = {
        var factors = List[Int]()
        var arabicNumber = arabicNumberIn
        val sortedKeys = MAP.keys.toSeq.sorted.reverse
        while (arabicNumber > 0) {
            for(f <- sortedKeys) {
                while (arabicNumber >= f) {
                    factors = factors:+f
                    arabicNumber -= f;
                }
            }
        }
        return factors
    }
 
    def mapFactorsToDigits(factors: List[Int]): List[String] = {
        factors map(f => MAP.get(f) match {
          case Some(s) => s
          case None => ""
        })
    }
}