import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

public class probabilityFunction extends JFrame {
    private JPanel cardPanel;
    private JButton btnNextPage;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel buttonBar;
    private JPanel pnlCenterMain;
    private JPanel pnlMainNorth;
    private JPanel pnlMainCenter;
    private JButton btnRandomize;
    private JButton btnGraph;
    private JTextField txtSize;
    private JCheckBox chkPopu;
    private JCheckBox chkSample;
    private JTextField txtInput;
    private JTextField txtMean;
    private JTextField txtMedian;
    private JTextField txtMode;
    private JTextField txtVariance;
    private JTextField txtStdDev;
    private JPanel resEast;
    private JPanel resCenter;
    private JTextArea txtInterpret;
    private JTextField resMean;
    private JTextField resMedian;
    private JTextField resMode;
    private JTextField resVariance;
    private JTextField resStdDev;
    private JButton btnReturn;
    private JButton btnCompute;
    private JButton btnStart;
    private JPanel panel0;
    private JTextField txtDistance;

    CardLayout cl = new CardLayout();
    Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    int size;
    double[] values;
    String inputText, samplingMethod;

    public probabilityFunction() {
        cardPanel.setLayout(cl);
        btnStart.addActionListener(e -> onStart());

        btnGraph.addActionListener(e -> onNext());
        btnReturn.addActionListener(e -> onReturn());
        btnRandomize.addActionListener(e -> onRandomize());
        btnCompute.addActionListener(e -> onCompute());
        chkPopu.addActionListener(e -> onPopulation());
        chkSample.addActionListener(e -> onSample());

        txtMean.setEditable(false);
        txtMean.setBorder(emptyBorder);
        txtMedian.setEditable(false);
        txtMedian.setBorder(emptyBorder);
        txtMode.setEditable(false);
        txtMode.setBorder(emptyBorder);
        txtVariance.setEditable(false);
        txtVariance.setBorder(emptyBorder);
        txtStdDev.setEditable(false);
        txtStdDev.setBorder(emptyBorder);
        resMean.setEditable(false);
        resMean.setBorder(emptyBorder);
        resMedian.setEditable(false);
        resMedian.setBorder(emptyBorder);
        resMode.setEditable(false);
        resMode.setBorder(emptyBorder);
        resVariance.setEditable(false);
        resVariance.setBorder(emptyBorder);
        resStdDev.setEditable(false);
        resStdDev.setBorder(emptyBorder);
        txtInterpret.setEditable(false);
        txtDistance.setBorder(emptyBorder);
        
        cardPanel.add(panel0, "Card0");
        cardPanel.add(panel1, "Card1");
        cardPanel.add(panel2, "Card2");
        cl.show(cardPanel, "LAYOUT");

        //WINDOW SETTINGS
        this.add(cardPanel);
        this.setContentPane(cardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Probability Calculator");
        this.setResizable(false);
        this.setSize(720,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onRandomize() {
        int start = 1, end = 500;
        Random random = new Random();
        size = Integer.parseInt(txtSize.getText());
        double[] values = new double[size];

        StringBuilder inputTextBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            double randomNum = start + (random.nextDouble() * (end - start));
            values[i] = Double.parseDouble(String.format("%.2f", randomNum));
            inputTextBuilder.append(" ").append(values[i]);
        }
        inputText = inputTextBuilder.toString();
        txtInput.setText(inputText);
    }

    private void onCompute(){
        size = Integer.parseInt(txtSize.getText());
        values = new double[size];
        inputReader(); //return each input value to the values list

        //MEAN
        double mean = getMean();
        txtMean.setText(String.format("%.2f", mean));
        resMean.setText(String.format("%.2f", mean));

        //MEDIAN
        double median = getMedian();
        txtMedian.setText(String.valueOf(median));
        resMedian.setText(String.valueOf(median));

        //MODE
        ArrayList<Double> modeList = getMode();
        StringBuilder modeStr = new StringBuilder();
        for(int i=0; i < modeList.size(); i++) {
            if(i == modeList.size() - 1) {
                modeStr.append(modeList.get(i));
                break;
            }
            modeStr.append(modeList.get(i)).append(", ");
        }
        txtMode.setText(modeStr.toString());
        resMode.setText(modeStr.toString());

        //VARIANCE
        double variance = getVariance(mean);
        txtVariance.setText(String.valueOf(String.format("%.2f", variance)));
        resVariance.setText(String.valueOf(String.format("%.2f", variance)));

        //STD
        double standardDev = getStandardDev(variance);
        txtStdDev.setText(String.valueOf(String.format("%.2f", standardDev)));
        resStdDev.setText(String.valueOf(String.format("%.2f", standardDev)));

    }
    private void inputReader() {
        String input = txtInput.getText();
        String[] parts = input.split(" ");
        for (int i = 0; i < size; i++) {
            values[i] = Double.parseDouble(parts[i]);
        }
    }

    private double getMean() {
        double sum = 0;

        for(int i = 0; i < size; i++){
            sum = sum + values[i];
        }
        double mean = sum / size;
        return mean;
    }

    private double getMedian() {
        //Duplicate values list in getting the median
        double[] sortedValues = new double[size];
        System.arraycopy(values, 0, sortedValues, 0, size);

        //Sort the duplicate list copy
        Arrays.sort(sortedValues);

        //Get the middle value of the new sorted list
        double median;
        if(size % 2 != 0){
            median =  values[size / 2];
        }
        else{
            int index1, index2;
            index1 = size / 2;
            index2 = (size/2)+1;
            median = (values[index1] + values[index2]) / 2.0;
        }
        return median;
    }

    private ArrayList<Double> getMode() {
        //NOTE: An array of values can have multiple modes. Therefore, we will return a double array that contains the mode/s

        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        ArrayList<Double> modeList = new ArrayList<>();
        int maxFrequency = 0;

        for(double val : values) {
            int frequency = frequencyMap.getOrDefault(val, 0) + 1;
            frequencyMap.put(val, frequency);

            if(frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }

        for (HashMap.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modeList.add(entry.getKey());
            }
        }

        return modeList;
    }

    private double getVariance(double mean) {
        //return all instances of (x - mean)^2 into an array
        double variance = 0, sum = 0;
        for(int i=0; i<size; i++) {
            sum += Math.pow(values[i] - mean, 2);
        }

        //Depending on the sampling method, solve for the value of the variance
        variance = switch (samplingMethod) {
            case "Population" -> sum / size;
            case "Sample" -> sum / (size - 1);
            default -> variance;
        };
        return variance;
    }

    private double getStandardDev(double variance) {
        return Math.sqrt(variance);
    }

    private void onStart() {
        cl.show(cardPanel, "Card1");}
    private void onNext() {
        cl.show(cardPanel, "Card2");
        ProbVizualizer probChart = new ProbVizualizer(values);
        ChartPanel chartPanel = new ChartPanel(probChart.visualizeProb());
        resCenter.add(chartPanel);
    }
    private void onReturn() {
        cl.show(cardPanel, "Card1");
    }
    private void onPopulation() {
        samplingMethod = "Population";
    }
    private void onSample() {
        samplingMethod = "Sample";
    }
    public static void main(String[] args) {
        new probabilityFunction();
    }

}