import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.data.statistics.HistogramDataset;
import java.awt.*;

public class ProbVizualizer {
    private double[] data;
    ProbVizualizer(double[] data) {
        this.data = data;
    }

    public JFreeChart visualizeProb() {
        HistogramDataset probDistribution = new HistogramDataset();
        probDistribution.addSeries("Probability of x", this.data, 8);

        JFreeChart probViz = ChartFactory.createHistogram(
                "Probability Distribution",
                "x",
                "Frequency",
                probDistribution
        );

        //probViz.setBackgroundPaint(new Color(0xd4c3a7));

        return probViz;
    }
}
