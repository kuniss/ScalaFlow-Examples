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
	  val romanNumber = output1
	  val arabicNumber = output2
		override protected val OutputPort1Name = "romanNumber"
		override protected val OutputPort2Name = "arabicNumber"
    
    def processInput(number: String) {
        try {
            forwardOutput2(Integer.parseInt(number))
        }
        catch {
          case _:NumberFormatException => forwardOutput1(number)
        }
    }
    

}


class ValidateRomanNumber extends FunctionUnit("ValidateRomanNumber") 
  with InputPort[String]
  with OutputPort1[String]
  with OutputPort2[String]
{
    
    // for meaningful names on binding to context
  	val valid = output1
  	val invalid = output2
  	override protected val OutputPort1Name = "valid"
  	override protected val OutputPort2Name = "invalid"

    def processInput(romanNumber: String) {
        if (Pattern.matches("^[IVXLCDM]+$", romanNumber.toUpperCase))
            forwardOutput1(romanNumber)
        else
            forwardOutput2("Invalid roman digit found in " + romanNumber)
    }
    
}


class ValidateArabicNumber extends FunctionUnit("ValidateRomanNumber") 
  with InputPort[Int]
  with OutputPort1[Int]
  with OutputPort2[String]
{
    // for meaningful names on binding to context
  	val valid = output1
  	val invalid = output2
  	override protected val OutputPort1Name = "valid"
  	override protected val OutputPort2Name = "invalid"
    
    def processInput(arabicNumber: Int) {
        if (arabicNumber >= 0 && arabicNumber <= 3000)
            forwardOutput1(arabicNumber)
        else
            forwardOutput2("Invalid arabic number " + arabicNumber + "; must be in range 1..3000")
    }
    
}
