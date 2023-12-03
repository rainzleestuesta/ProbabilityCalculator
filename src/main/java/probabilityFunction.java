import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


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
    int size;
    double [] values = new double[size];
    String inputText;

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
        txtMedian.setEditable(false);
        txtMode.setEditable(false);
        txtVariance.setEditable(false);
        txtStdDev.setEditable(false);
        resMean.setEditable(false);
        resMedian.setEditable(false);
        resMode.setEditable(false);
        resVariance.setEditable(false);
        resStdDev.setEditable(false);
        txtInterpret.setEditable(false);
        
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
        double mean, median, mode, variance, std, sum = 0;

        //MEAN
        for(int i = 0; i < size; i++){
            sum = sum + values[i];
        }
        mean = sum / size;
        txtMean.setText(String.format("%.2f", mean));

        //MEDIAN
        if(size % 2 != 0){
            median =  values[size / 2];
        }
        else{
            int index1, index2;
            index1 = size / 2;
            index2 = (size/2)+1;
            median = (values[index1] + values[index2]) / 2.0;
        }
        txtMedian.setText(String.valueOf(median));
        //MODE

        //VARIANCE

        //STD

    }
    private void inputReader() {
        String input = txtInput.getText();
        String[] parts = input.split(", ");
        for (int i = 0; i < size; i++) {
                values[i] = Double.parseDouble(parts[i]);
        }
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
        //add code here
        chkSample.setSelected(false);
    }
    private void onSample() {
        //add code here
        chkPopu.setSelected(false);
    }
    public static void main(String[] args) {
        new probabilityFunction();
    }

}