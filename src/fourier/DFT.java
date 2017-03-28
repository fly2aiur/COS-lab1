package fourier;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;

public class DFT {
    public static Complex[] transform(Complex[] x, boolean normalize, boolean inverse) {
        final int N = x.length;

        Complex[] c = new Complex[N];

        for (int k = 0; k < N; k++) {
            Complex ci = Complex.ZERO;
            for (int m = 0; m < N; m++) {
                int arg = k * m;
                Complex omega = inverse ? w(-arg, N) : w(arg ,N);
                ci = ci.add(omega.multiply(x[m]));
            }
            c[k] = ci;
        }

        if (normalize) {
            for (int k = 0; k < N; k++) {
                c[k] = c[k].divide(N);
            }
        }

        return c;
    }

    public static Complex[] transform(Complex[] x, boolean normalize) {
        return transform(x, normalize, false);
    }

    public static Complex[] transform(final double[] x, boolean normalize, boolean inverse) {
        return transform(ComplexUtils.convertToComplex(x), normalize, inverse);
    }

    public static Complex[] transform(final double[] x, boolean normalize) {
        return transform(x, normalize, false);
    }

    public static Complex[] reverse(Complex[] c) {
        return transform(c, false, true);
    }

    public static Complex w(int k, int N) {
        if (k % N == 0) return Complex.ONE;
        double arg = -2 * Math.PI * k / N;
        return new Complex(Math.cos(arg), Math.sin(arg));
    }
}
