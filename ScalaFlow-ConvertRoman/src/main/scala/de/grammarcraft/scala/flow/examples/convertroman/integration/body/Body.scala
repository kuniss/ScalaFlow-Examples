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


final class Body extends FunctionUnit("Body") 
  with InputPort[String]
  with OutputPort1[String]
  with OutputPort2[String]
{
 	// for meaningful names on binding to context
	val result = output1
	val error = output2
	override protected val OutputPort1Name = "result"
	override protected val OutputPort2Name = "error"
	
	// for meaningful names on binding internally
	private[this] val _result = forwardOutput1(_)
	private[this] val _error = forwardOutput2(_)
	
	val determine_number_type = new DetermineNumberType
	val validate_roman_number = new ValidateRomanNumber
	val validate_arabic_number = new ValidateArabicNumber
	val convert_from_roman = new ConvertFromRoman
	val convert_to_roman = new ConvertToRoman
	
	def processInput(msg: String) {
	  // number -> determine_number_type 
    // is same as:
    determine_number_type.input(msg)
  }
    
	// determine_number_type.romanNumber -> validate_roman_number
  determine_number_type.romanNumber -> validate_roman_number.input
	// determine_number_type.arabicNumber -> validate_arabic_number
  determine_number_type.arabicNumber -> validate_arabic_number.input
	
	// validate_roman_number.valid -> convert_from_roman
	validate_roman_number.valid -> convert_from_roman.input
	// validate_roman_number.invalid -> error
	validate_roman_number.invalid -> _error

	// validate_arabic_number.valid -> convert_to_roman
	validate_arabic_number.valid -> convert_to_roman.input
	// validate_arabic_number.invalid -> error
	validate_arabic_number.invalid -> _error
	
	// convert_to_roman -> result
	convert_to_roman.output -> _result
	// convert_from_roman -> result
	convert_from_roman.output -> _result

}