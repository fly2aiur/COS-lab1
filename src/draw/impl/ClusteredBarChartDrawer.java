package draw.impl;

import draw.Drawable;
import exception.BarChartException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ClusteredBarChartDrawer extends ApplicationFrame implements Drawable {


    private static final String DEFAULT_WINDOW_TITLE = "Clustered bar chart window";
    private static final String DEFAULT_CATEGORY_LABEL = "X";
    private static final String DEFAULT_VALUE_LABEL = "Y";
    private static final double BAR_WIDTH = 0.3;

    private static final int WINDOW_X = 640;
    private static final int WINDOW_Y = 640;

    public static ChartPanel createClusteredBarChart(double[] x, double[] y1, double[] y2, String chartTitle, String label1, String label2) throws BarChartException {
        IntervalXYDataset dataSet = createDataSet(x, y1, y2, label1, label2);
        JFreeChart chart = createChart(chartTitle, DEFAULT_CATEGORY_LABEL, DEFAULT_VALUE_LABEL, dataSet);
        return new ChartPanel(chart);
    }

    public ClusteredBarChartDrawer(double[] x, double[] y1, double[] y2, String chartTitle, String label1, String label2) throws BarChartException {
        super(DEFAULT_WINDOW_TITLE);
        IntervalXYDataset dataset = createDataSet(x, y1, y2, label1, label2);
        JFreeChart chart = createChart(chartTitle, DEFAULT_CATEGORY_LABEL, DEFAULT_VALUE_LABEL, dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(WINDOW_X, WINDOW_Y));
        setContentPane(chartPanel);
    }

    private static IntervalXYDataset createDataSet(double[] x, double[] y1, double[] y2, String label1, String label2) throws BarChartException {

        if (x.length != y1.length || x.length != y2.length) {
            throw new BarChartException("x length != y length");
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries(label1);
        XYSeries series2 = new XYSeries(label2);
        for (int i = 0; i < x.length; i++) {
            series1.add(x[i], y1[i]);
            series2.add(x[i], y2[i]);
        }
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.setIntervalWidth(BAR_WIDTH);
        return dataset;
    }

    private static JFreeChart createChart(String title, String categoryAxisLabel, String valueAxisLabel, IntervalXYDataset dataset) {

        NumberAxis domainAxis = new NumberAxis(categoryAxisLabel);
        domainAxis.setAutoRangeIncludesZero(false);

        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        XYBarRenderer renderer = new ClusteredXYBarRenderer();

        XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(PlotOrientation.VERTICAL);

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);

        return chart;
    }

    @Override
    public void draw() {
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }
}