package de.grammarcraft.scala.flow.examples.convertroman.operations.conversions

import de.grammarcraft.scala.flow.InputPort
import de.grammarcraft.scala.flow.OutputPort
import de.grammarcraft.scala.flow.FunctionUnit


class ConvertFromRoman extends FunctionUnit("ConvertFromRoman")
  with InputPort[String]
  with OutputPort[String]
{
    def processInput(romanNumber: String) {
        val values = mapRomanNumberToValues(romanNumber)
        val negatedValues = applySubstrationRule(values)
        output <= { Integer.toString(negatedValues.reduce(_+_)) }
    }
    
    val mapR2I: Map[Char, Int] = Map(
        'I' -> 1,
        'V' -> 5,
        'X' -> 10,
        'L' -> 50,
        'C' -> 100,
        'D' -> 500,
        'M' -> 1000
    )
    def mapRomanNumberToValues(romanNumber: String): List[Int] = {
            romanNumber.map(d => mapR2I.get(d.toUpper) match {
              case Some(arabicValue) => arabicValue
              case None => throw new NumberFormatException("unsuported roman number: " + d)
            }).toList
    }
    
    def applySubstrationRule(values: List[Int]): List[Int] = {
        if (values.size <= 1) return values
        val negatedValues = for { 
          i <- 0 until values.size-1
        } yield (
            if (values(i + 1) > values(i))
              -values(i)
            else
              values(i)
        )
        negatedValues.toList:+values.last
    }
}
