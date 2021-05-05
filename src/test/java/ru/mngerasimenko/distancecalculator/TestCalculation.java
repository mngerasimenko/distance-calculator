package ru.mngerasimenko.distancecalculator;

import junit.framework.TestCase;
import org.junit.Test;
import ru.mngerasimenko.distancecalculator.calculator.DegreeAndRadian;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;

public class TestCalculation extends TestCase {

    //@Test
    public void testDegreeToDouble() throws InvalidCoordinateFormatException {

        double expResult = -0.7522;
        double result = DegreeAndRadian.degreeToDouble("0.7522W");
        assertEquals(expResult, result);

        expResult = 30.75;
        result = DegreeAndRadian.degreeToDouble("30.75N");
        assertEquals(expResult, result);

        expResult = -22;
        result = DegreeAndRadian.degreeToDouble("22S");
        assertEquals(expResult, result);

        try {
            expResult = 0;
            result = DegreeAndRadian.degreeToDouble("0s");
            assertEquals(expResult, result);
        } catch (InvalidCoordinateFormatException ex) {
            System.out.println("InvalidCoordinateFormatException");
        }

        try {
            expResult = 0;
            result = DegreeAndRadian.degreeToDouble("0.0N");
            assertEquals(expResult, result);
        } catch (InvalidCoordinateFormatException ex) {
            System.out.println("InvalidCoordinateFormatException1");
        }

        try {
            expResult = 0;
            result = DegreeAndRadian.degreeToDouble("S");
            assertEquals(expResult, result);
        } catch (InvalidCoordinateFormatException ex) {
            System.out.println("InvalidCoordinateFormatException2");
        }

        try {
            expResult = 0;
            result = DegreeAndRadian.degreeToDouble("1.3333SNN");
            assertEquals(expResult, result);
        } catch (InvalidCoordinateFormatException ex) {
            System.out.println("InvalidCoordinateFormatException3");
        }

        try {
            expResult = 0;
            result = DegreeAndRadian.degreeToDouble("1.333");
            assertEquals(expResult, result);
        } catch (InvalidCoordinateFormatException ex) {
            System.out.println("InvalidCoordinateFormatException4");
        }

    }
}
