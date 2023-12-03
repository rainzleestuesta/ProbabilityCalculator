import org.jfree.chart.ChartPanel;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

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
    inputComputation compute = new inputComputation();
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
        //this.setUndecorated(true);
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

        for (int i = 0; i < compute.getSize(); i++) {
            double randomNum = start + (random.nextDouble() * (end - start));
            values[i] = Double.parseDouble(String.format("%.2f", randomNum));
            inputTextBuilder.append(" ").append(values[i]);
        }
        compute.setInputText(inputTextBuilder.toString());
        txtInput.setText(compute.getInputText());
    }

    private void onCompute(){
        compute.setSize(Integer.parseInt(txtSize.getText()));
        compute.setValues(new double[compute.getSize()]);
        inputReader(); //return each input value to the values list

        //MEAN
        compute.computeMean();
        txtMean.setText(String.format("%.2f", compute.getMean()));
        resMean.setText(String.format("%.2f", compute.getMean()));

        //MEDIAN
        txtMedian.setText(String.valueOf(compute.computeMedian()));
        resMedian.setText(String.valueOf(compute.computeMedian()));

        //MODE
        ArrayList<Double> modeList = compute.computeMode();
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
        txtVariance.setText(String.valueOf(String.format("%.2f", compute.computeVariance(compute.getMean()))));
        resVariance.setText(String.valueOf(String.format("%.2f", compute.computeVariance(compute.getMean()))));

        //STD
        txtStdDev.setText(String.valueOf(String.format("%.2f", compute.computeStandardDev(compute.getVariance()))));
        resStdDev.setText(String.valueOf(String.format("%.2f", compute.computeStandardDev(compute.getVariance()))));
    }
    private void inputReader() {
        String input = txtInput.getText();
        String[] parts = input.split(" ");
        for (int i = 0; i < compute.getSize(); i++) {
            compute.getValues()[i] = Double.parseDouble(parts[i]);
        }
    }
    private void onStart() {
        cl.show(cardPanel, "Card1");}
    private void onNext() {
        cl.show(cardPanel, "Card2");
        ProbVizualizer probChart = new ProbVizualizer(compute.getValues());
        ChartPanel chartPanel = new ChartPanel(probChart.visualizeProb());
        resCenter.add(chartPanel);
    }
    private void onReturn() {
        cl.show(cardPanel, "Card1");
    }
    private void onPopulation() {
        compute.setSamplingMethod("Population");
    }
    private void onSample() {
        compute.setSamplingMethod("Sample");
    }
    public static void main(String[] args) {
        new probabilityFunction();
    }

}