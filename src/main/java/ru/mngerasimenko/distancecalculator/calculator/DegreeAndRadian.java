package ru.mngerasimenko.distancecalculator.calculator;

import ru.mngerasimenko.distancecalculator.exception.InvalidCoordinateFormatException;
import ru.mngerasimenko.distancecalculator.settings.Settings;

public class DegreeAndRadian {

    public static double degreeToRadian(double degree) {
        return degree * Settings.PI_180;
    }

    public static double degreeToDouble(String degree) throws InvalidCoordinateFormatException {
        double result;
        if (degree == null || degree.length() < 2) throw new InvalidCoordinateFormatException();
        degree = degree.toUpperCase();
        final int len = degree.length();
        char[] dArray = new char[len - 1];
        for (int i = 0; i < len - 1; i++) {
            dArray[i] = degree.charAt(i);
        }
        char lastChar = degree.charAt(len - 1);
        try {
            double numberDegree = Double.parseDouble(new String(dArray));
            if (lastChar == 'S' || lastChar == 'W') {
                if (numberDegree != 0) {
                    result = numberDegree * (-1);
                } else result = numberDegree;
            } else if (lastChar == 'N' || lastChar == 'E') {
                result = numberDegree;
            } else throw new InvalidCoordinateFormatException();
        } catch (NumberFormatException ex) {
            throw new InvalidCoordinateFormatException();
        }

        return result;
    }
}
