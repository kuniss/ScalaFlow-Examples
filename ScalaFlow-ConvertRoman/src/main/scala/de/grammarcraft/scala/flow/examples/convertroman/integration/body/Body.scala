package de.grammarcraft.scala.flow.examples.convertroman.integration.body

import de.grammarcraft.scala.flow.InputPort
import de.grammarcraft.scala.flow.OutputPort1
import de.grammarcraft.scala.flow.OutputPort2
import de.grammarcraft.scala.flow.FunctionUnit
import de.grammarcraft.scala.flow.examples.convertroman.operations.conversions.ConvertFromRoman
import de.grammarcraft.scala.flow.examples.convertroman.operations.conversions.ConvertToRoman
import de.grammarcraft.scala.flow.examples.convertroman.operations.conversions.DetermineNumberType
import de.grammarcraft.scala.flow.examples.convertroman.operations.conversions.ValidateArabicNumber
import de.grammarcraft.scala.flow.examples.convertroman.operations.conversions.ValidateRomanNumber

import de.grammarcraft.scala.flow.ControlStructures._


final class Body extends FunctionUnit("Body") 
  with InputPort[String]
  with OutputPort1[String]
  with OutputPort2[String]
{
 	// for meaningful names on binding to context
	val result = OutputPort1("result")
	val error = OutputPort2("error")
		
	val determine_number_type = new DetermineNumberType
	val validate_roman_number = new ValidateRomanNumber
	val validate_arabic_number = new ValidateArabicNumber
	val convert_from_roman = new ConvertFromRoman
	val convert_to_roman = new ConvertToRoman
	
	def processInput(msg: String) {
    determine_number_type.input <= msg
  }
    
  determine_number_type.romanNumber -> validate_roman_number
  determine_number_type.arabicNumber -> validate_arabic_number
	
	validate_roman_number.valid -> convert_from_roman
	validate_roman_number.invalid -> error

	validate_arabic_number.valid -> convert_to_roman
	validate_arabic_number.invalid -> error
	
	convert_to_roman -> result
	convert_from_roman -> result
	
}