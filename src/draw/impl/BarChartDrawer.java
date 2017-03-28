package draw.impl;

import draw.Drawable;
import exception.BarChartException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChartDrawer extends ApplicationFrame implements Drawable {

    private static final double BAR_WIDTH = 0.5;
    private static final String DEFAULT_WINDOW_TITLE = "Bar chart window";
    private static final String DEFAULT_CHART_TITLE = "Bar chart";
    private static final String DEFAULT_LABEL = "Data";
    private static final String DEFAULT_CATEGORY_LABEL = "X";
    private static final String DEFAULT_VALUE_LABEL = "Y";

    private static final int WINDOW_X = 640;
    private static final int WINDOW_Y = 480;

    public BarChartDrawer(double[] x, double[] y) throws BarChartException {
        this(x, y, DEFAULT_CHART_TITLE, DEFAULT_LABEL);
    }

    public BarChartDrawer(double[] x, double[] y, String chartTitle, String label) throws BarChartException {
        super(DEFAULT_WINDOW_TITLE);
        IntervalXYDataset dataset = createDataset(x, y, label);
        JFreeChart chart = createChart(chartTitle, DEFAULT_CATEGORY_LABEL, DEFAULT_VALUE_LABEL, dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(WINDOW_X, WINDOW_Y));
        setContentPane(chartPanel);
    }
    private IntervalXYDataset createDataset(double[] x, double[] y, String label) throws BarChartException {
        if (x.length != y.length) {
            throw new BarChartException("x length != y length");
        }
        final XYSeries series = new XYSeries(label);
        for (int i = 0; i < x.length; i++) {
            series.add(x[i], y[i]);
        }
        final XYSeriesCollection dataset = new XYSeriesCollection(series);
        dataset.setIntervalWidth(BAR_WIDTH);
        return dataset;
    }

    private JFreeChart createChart(String title, String categoryAxisLabel, String valueAxisLabel, IntervalXYDataset dataset) {
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