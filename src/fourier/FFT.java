package fourier;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;

import static fourier.DFT.w;

public class FFT {
    public static Complex[] transform(Complex[] x, boolean normalize) {
        Complex[] c = decimationInTime(x, false);

        if (normalize) {
            for (int i = 0; i < c.length; i++) {
                c[i] = c[i].divide(c.length);
            }
        }

        return c;
    }

    public static Complex[] transform(double[] x, boolean normalize) {
        return transform(ComplexUtils.convertToComplex(x), normalize);
    }

    public static Complex[] reverse(Complex[] c) {
        return decimationInTime(c, true);
    }

    private static Complex[] decimationInTime(Complex[] x, boolean inverse) {
        if (x.length == 1) return x;

        final int N = x.length;

        Complex[] xOdd = new Complex[N / 2];
        Complex[] xEven = new Complex[N / 2];

        for (int i = 0; i < N / 2; i++) {
            xOdd[i] = x[2 * i + 1];
            xEven[i] = x[2 * i];
        }

        Complex[] cOdd = decimationInTime(xOdd, inverse);
        Complex[] cEven = decimationInTime(xEven, inverse);

        Complex[] c = new Complex[N];

        for (int j = 0; j < N / 2; j++) {
            Complex omega = inverse ? w(-j, N) : w(j, N);
            c[j] = cEven[j].add(omega.multiply(cOdd[j]));
            c[j + N / 2] = cEven[j].subtract(omega.multiply(cOdd[j]));
        }

        return c;
    }
}
