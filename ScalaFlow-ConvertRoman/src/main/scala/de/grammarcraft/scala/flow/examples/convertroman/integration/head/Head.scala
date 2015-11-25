package de.grammarcraft.scala.flow.examples.convertroman.integration.head

import de.grammarcraft.scala.flow.examples.convertroman.operations.DisplayError
import de.grammarcraft.scala.flow.examples.convertroman.operations.DisplayResult
import de.grammarcraft.scala.flow.examples.convertroman.operations.ReadNumberToConvert
import de.grammarcraft.scala.flow.examples.convertroman.contracts.IInputProvider
import de.grammarcraft.scala.flow.examples.convertroman.contracts.IOutputProvider
import de.grammarcraft.scala.flow.examples.convertroman.integration.body.Body

class Head(input: IInputProvider, body:  Body, output: IOutputProvider)
{
  
  val read_number_to_convert = new ReadNumberToConvert(input)
  val display_result = new DisplayResult(output)
  val display_error = new DisplayError(output)
  val convert = body
    
  // setup flow
  read_number_to_convert -> convert
	convert.result -> display_result.input
	convert.error -> display_error.input
	
	def run() {
	    read_number_to_convert.run
	}
	
}