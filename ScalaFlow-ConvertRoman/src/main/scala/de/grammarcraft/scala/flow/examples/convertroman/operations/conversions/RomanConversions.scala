package de.grammarcraft.scala.flow.examples.convertroman.operations.conversions

import de.grammarcraft.scala.flow.InputPort
import de.grammarcraft.scala.flow.OutputPort1
import de.grammarcraft.scala.flow.OutputPort2
import de.grammarcraft.scala.flow.FunctionUnit
import java.util.regex.Pattern

class DetermineNumberType extends FunctionUnit("DetermineNumberType") 
  with InputPort[String]
  with OutputPort1[String]
  with OutputPort2[Int]
{
    // for meaningful names on binding to context
	  val romanNumber = OutputPort1("romanNumber")
	  val arabicNumber = OutputPort2("arabicNumer")
    
    def processInput(number: String) {
        try {
            arabicNumber <= Integer.parseInt(number)
        }
        catch {
          case _:NumberFormatException => 
            romanNumber <= number
        }
    }
    

}


class ValidateRomanNumber extends FunctionUnit("ValidateRomanNumber") 
  with InputPort[String]
  with OutputPort1[String]
  with OutputPort2[String]
{
    
    // for meaningful names on binding to context
  	val valid = OutputPort1("valid")
  	val invalid = OutputPort2("invalid")

    def processInput(romanNumber: String) {
        if (Pattern.matches("^[IVXLCDM]+$", romanNumber.toUpperCase))
            valid <= romanNumber
        else
            invalid <= "Invalid roman digit found in " + romanNumber
    }
    
}


class ValidateArabicNumber extends FunctionUnit("ValidateRomanNumber") 
  with InputPort[Int]
  with OutputPort1[Int]
  with OutputPort2[String]
{
    // for meaningful names on binding to context
  	val valid = OutputPort1("valid")
  	val invalid = OutputPort2("invalid")
    
    def processInput(arabicNumber: Int) {
        if (arabicNumber >= 0 && arabicNumber <= 3000)
            valid <= arabicNumber
        else
            invalid <= "Invalid arabic number " + arabicNumber + "; must be in range 1..3000"
    }
    
}
