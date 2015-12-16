package de.grammarcraft.scala.flow.examples.convertroman

import de.grammarcraft.scala.flow.examples.convertroman.operations.conversions.ConvertFromRoman
import de.grammarcraft.scala.flow.examples.convertroman.operations.conversions.ConvertToRoman
import org.junit.Test

import org.junit.Assert

class ConversionTest {
    
    private def test_from_roman(romanNumber: String, arabicNumber: Int) {
        val c = new ConvertFromRoman
        c.output -> { x => Assert.assertEquals(Integer.toString(arabicNumber), x)}
        c.input <= romanNumber
    }

    @Test
    def test_from_roman() {
        test_from_roman("I", 1)
        test_from_roman("IV", 4)
        test_from_roman("MCMLXXXIV", 1984)
        test_from_roman("MMXV", 2015)
    }
    
    private def test_to_roman(arabicNumber: Int, romanNumber: String) {
        val c = new ConvertToRoman
        c.output -> { x => Assert.assertEquals(romanNumber, x) } 
        c.input <= arabicNumber
    }
    
    @Test
    def test_to_roman() {
        test_to_roman(1, "I")
        test_to_roman(4, "IV")
        test_to_roman(1984, "MCMLXXXIV")
        test_to_roman(2015, "MMXV")
    }
}