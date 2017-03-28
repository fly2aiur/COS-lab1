package main;

import draw.impl.ClusteredBarChartDrawer;
import draw.impl.LineChartDrawer;
import exception.BarChartException;
import fourier.DFT;
import fourier.FFT;
import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartPanel;
import utils.ComplexConverter;

import javax.swing.*;

import java.awt.*;

import static java.lang.Math.*;

/*
        № варианта      Сигнал              Алгоритм БПФ                        N
        1                y=cos(3x)+sin(2x)   БПФ с прореживанием по времени      8
 */

public class Lab1 {
    private static int N = 8;

    private double[] xTime;
    private double[] xFreq;
    private double[] xOriginal;
    private Complex[] XDFT;
    private Complex[] xDFTReversed;

    private Complex[] XFFT;
    private Complex[] xFFTReversed;

    static double func(double x) {
        return cos(3 * x) + sin(2 * x);
    }

    public static void main(String[] args) {
        Lab1 lab1 = new Lab1();
        lab1.makeCalculations();
        try {
            lab1.showCharts();
        } catch (BarChartException e) {
            e.printStackTrace();
        }
    }

    private void makeCalculations() {
        xTime = new double[N];
        xFreq = new double[N];
        xOriginal = new double[N];

        for (int k = 0; k < N; k++) {
            double xi = k * 2 * PI / N;
            xTime[k] = xi;
            xFreq[k] = k;
            xOriginal[k] = func(xi);
        }

        XDFT = DFT.transform(xOriginal, true);
        xDFTReversed = DFT.reverse(XDFT);

        XFFT = FFT.transform(xOriginal, true);
        xFFTReversed = FFT.reverse(XFFT);
    }

    private void showCharts() throws BarChartException {
        double[] y;
        double[] y1;
        double[] y2;

        JFrame f = new JFrame();
        f.setTitle("Lab 1");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(2, 3));

        y = xOriginal;
        ChartPanel chartPanelOriginal = LineChartDrawer.createLineChart(xTime, y, "Original", "Value");
        chartPanelOriginal.setMouseWheelEnabled(true);
        chartPanelOriginal.setHorizontalAxisTrace(true);
        chartPanelOriginal.setVerticalAxisTrace(true);
        f.add(chartPanelOriginal);

        y1 = ComplexConverter.getAbs(XDFT);
        y2 = ComplexConverter.getAbs(XFFT);
        ChartPanel chartPanelFourier = ClusteredBarChartDrawer.createClusteredBarChart(xFreq, y1, y2, "Fourier transform", "DFT", "FFT");
        chartPanelFourier.setMouseWheelEnabled(true);
        chartPanelFourier.setHorizontalAxisTrace(true);
        chartPanelFourier.setVerticalAxisTrace(true);
        f.add(chartPanelFourier);

        y = ComplexConverter.getReals(xDFTReversed);
        ChartPanel chartPanelDFTReversed = LineChartDrawer.createLineChart(xTime, y, "DFT reversed", "Value");
        chartPanelDFTReversed.setMouseWheelEnabled(true);
        chartPanelDFTReversed.setHorizontalAxisTrace(true);
        chartPanelDFTReversed.setVerticalAxisTrace(true);
        f.add(chartPanelDFTReversed);

        y = ComplexConverter.getReals(xFFTReversed);
        ChartPanel chartPanelFFTReversed = LineChartDrawer.createLineChart(xTime, y, "FFT reversed", "Value");
        chartPanelFFTReversed.setMouseWheelEnabled(true);
        chartPanelFFTReversed.setHorizontalAxisTrace(true);
        chartPanelFFTReversed.setVerticalAxisTrace(true);
        f.add(chartPanelFFTReversed);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
