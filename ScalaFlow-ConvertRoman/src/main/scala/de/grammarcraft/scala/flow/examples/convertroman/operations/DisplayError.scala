package de.grammarcraft.scala.flow.examples.convertroman.operations

import de.grammarcraft.scala.flow.InputPort
import de.grammarcraft.scala.flow.OutputPort
import de.grammarcraft.scala.flow.FunctionUnit
import de.grammarcraft.scala.flow.examples.convertroman.contracts.IOutputProvider


class DisplayError(val outputProvider: IOutputProvider)  
  extends FunctionUnit("DisplayError")
  with InputPort[String]
{
    protected def processInput(msg: String) {
        this.outputProvider.display_error(msg)
    }
    
}