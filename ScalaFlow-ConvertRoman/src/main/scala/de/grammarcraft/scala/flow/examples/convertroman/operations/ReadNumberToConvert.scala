package de.grammarcraft.scala.flow.examples.convertroman.operations

import de.grammarcraft.scala.flow.InputPort
import de.grammarcraft.scala.flow.OutputPort
import de.grammarcraft.scala.flow.FunctionUnit
import de.grammarcraft.scala.flow.examples.convertroman.contracts.IInputProvider


class ReadNumberToConvert(val inputProvider: IInputProvider)
  extends FunctionUnit("ReadNumberToConvert")
  with InputPort[Unit]
  with OutputPort[String]
{
    protected def processInput(msg: Unit) {
        output <= this.inputProvider.read_number_to_convert()
    }
    
    def run() {
        processInput(Unit)
    }
	
}