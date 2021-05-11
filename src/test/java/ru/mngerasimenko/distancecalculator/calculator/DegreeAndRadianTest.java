package ru.mngerasimenko.distancecalculator.calculator;

import org.junit.Assert;
import org.junit.Test;
import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;

import static org.junit.Assert.*;

public class DegreeAndRadianTest {

    @Test
    public void degreeToRadian() {
        double expResult = 0;
        double result = DegreeAndRadian.degreeToRadian(0);
        Assert.assertTrue(expResult == result);
    }

    @Test
    public void degreeToDouble() throws InvalidCoordinateFormatException {
        double expResult = -0.7522;
        double result = DegreeAndRadian.degreeToDouble("0.7522W");
        Assert.assertTrue(expResult == result);
    }
    @Test
    public void degreeToDouble1() throws InvalidCoordinateFormatException {
        double expResult = 30.75;
        double result = DegreeAndRadian.degreeToDouble("30.75N");
        Assert.assertTrue(expResult == result);
    }
    @Test
    public void degreeToDouble2() throws InvalidCoordinateFormatException {
        double expResult = -22;
        double result = DegreeAndRadian.degreeToDouble("22S");
        Assert.assertTrue(expResult == result);
    }
    @Test()
    public void degreeToDouble3() throws InvalidCoordinateFormatException {
        double expResult = 0;
        double result = DegreeAndRadian.degreeToDouble("0S");
        Assert.assertTrue(expResult == result);
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void degreeToDoubleError2() throws InvalidCoordinateFormatException {
        double expResult = 0;
        double result = DegreeAndRadian.degreeToDouble("1.3333SNN");
        Assert.assertTrue(expResult == result);
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void degreeToDoubleError3() throws InvalidCoordinateFormatException {
        double expResult = 0;
        double result = DegreeAndRadian.degreeToDouble("S");
        Assert.assertTrue(expResult == result);
    }
    @Test(expected = InvalidCoordinateFormatException.class)
    public void degreeToDoubleError4() throws InvalidCoordinateFormatException {
        double expResult = 0;
        double result = DegreeAndRadian.degreeToDouble("1.333");
        Assert.assertTrue(expResult == result);
    }

}