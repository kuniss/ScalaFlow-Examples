package de.grammarcraft.scala.flow.examples.convertroman.contracts

trait IOutputProvider {
    def display_result (result: String)
    def display_error (errorMessage: String)
}