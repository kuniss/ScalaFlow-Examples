package de.grammarcraft.scala.flow.examples.convertroman.integration.head

import de.grammarcraft.scala.flow.examples.convertroman.operations.providers.Providers
import de.grammarcraft.scala.flow.examples.convertroman.contracts.IInputProvider
import de.grammarcraft.scala.flow.examples.convertroman.contracts.IOutputProvider
import de.grammarcraft.scala.flow.examples.convertroman.integration.body.Body

object Program extends App
{
    val providers = new Providers
    val input = providers
    val output = providers
    
    val body = new Body
    val head = new Head(input, body, output)
    
    head.run
}