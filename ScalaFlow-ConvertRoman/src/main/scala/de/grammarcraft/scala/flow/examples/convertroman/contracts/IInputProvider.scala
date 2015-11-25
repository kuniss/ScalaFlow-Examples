package de.grammarcraft.scala.flow.examples.convertroman.contracts

trait IInputProvider {
    def read_number_to_convert() : String
}