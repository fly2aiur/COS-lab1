package draw.impl;

import draw.Drawable;
import exception.BarChartException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;

public class LineChartDrawer extends ApplicationFrame implements Drawable {

    private static final String DEFAULT_WINDOW_TITLE = "Line chart window";
    private static final String DEFAULT_CATEGORY_LABEL = "X";
    private static final String DEFAULT_VALUE_LABEL = "Y";

    private static final int WINDOW_X = 640;
    private static final int WINDOW_Y = 640;

    public static ChartPanel createLineChart(double[] x, double[] y, String chartTitle, String label) throws BarChartException {
        IntervalXYDataset dataset = createDataSet(x, y, label);
        JFreeChart chart = createChart(chartTitle,DEFAULT_CATEGORY_LABEL, DEFAULT_VALUE_LABEL, dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        final XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.cyan);
        plot.setRenderer(renderer);
        return chartPanel;
    }

    public LineChartDrawer(double[] x, double[] y, String chartTitle, String label) throws BarChartException {
        super(DEFAULT_WINDOW_TITLE);
        IntervalXYDataset dataset = createDataSet(x, y, label);
        JFreeChart chart = createChart(chartTitle,DEFAULT_CATEGORY_LABEL, DEFAULT_VALUE_LABEL, dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(WINDOW_X, WINDOW_Y));
        final XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.cyan);
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    private static IntervalXYDataset createDataSet(double[] x, double[] y, String label) throws BarChartException {
        if (x.length != y.length) {
            throw new BarChartException("x length != y length");
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(label);
        for (int i = 0; i < x.length; i++) {
            series.add(x[i], y[i]);
        }
        dataset.addSeries(series);
        return dataset;
    }

    private static JFreeChart createChart(String title, String categoryAxisLabel, String valueAxisLabel, IntervalXYDataset dataset) {
        final JFreeChart chart = ChartFactory.createXYBarChart(
                title,
                categoryAxisLabel,
                false,
                valueAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        return chart;
    }

    @Override
    public void draw() {
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
}