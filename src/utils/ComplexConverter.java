package utils;

import org.apache.commons.math3.complex.Complex;

public class ComplexConverter {
    public static double[] getReals(Complex[] complexes){
        double[] doubles = new double[complexes.length];
        for (int i = 0; i < complexes.length; i++) {
            doubles[i] = complexes[i].getReal();
        }
        return doubles;
    }

    public static double[] getImaginaries(Complex[] complexes){
        double[] doubles = new double[complexes.length];
        for (int i = 0; i < complexes.length; i++) {
            doubles[i] = complexes[i].getImaginary();
        }
        return doubles;
    }

    public static double[] getAbs(Complex[] complexes){
        double[] doubles = new double[complexes.length];
        for (int i = 0; i < complexes.length; i++) {
            doubles[i] = complexes[i].abs();
        }
        return doubles;
    }

    public static double[] getArguments(Complex[] complexes){
        double[] doubles = new double[complexes.length];
        for (int i = 0; i < complexes.length; i++) {
            doubles[i] = complexes[i].getArgument();
        }
        return doubles;
    }
}
