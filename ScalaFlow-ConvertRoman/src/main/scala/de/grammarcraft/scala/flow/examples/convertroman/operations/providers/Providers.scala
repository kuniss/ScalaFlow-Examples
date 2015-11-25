package de.grammarcraft.scala.flow.examples.convertroman.operations.providers

import java.util.Scanner
import de.grammarcraft.scala.flow.examples.convertroman.contracts.IOutputProvider
import de.grammarcraft.scala.flow.examples.convertroman.contracts.IInputProvider

class Providers extends IInputProvider with IOutputProvider {
    
    def read_number_to_convert(): String =
    {
        val s = new Scanner(System.in)
        print("Enter roman or arabic number: ")
        return s.nextLine().trim()
    }
    
    def display_result(result: String) {
        println(result)
    }
    
    def display_error(errorMessage: String) {
        println("Error: " + errorMessage)
    }
    
}