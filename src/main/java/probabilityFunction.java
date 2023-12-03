import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import java.util.Arrays;
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
    Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    Insets margins = new Insets(10, 20, 10, 20);
    int size;
    double [] values = new double[size];
    String inputText;

    public probabilityFunction() {
        cardPanel.setLayout(cl);
        btnStart.addActionListener(e -> onStart());
        btnStart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnStart.setMargin(margins);
        btnGraph.addActionListener(e -> onNext());
        btnGraph.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnReturn.addActionListener(e -> onReturn());
        btnReturn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnRandomize.addActionListener(e -> onRandomize());
        btnRandomize.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnCompute.addActionListener(e -> onCompute());
        btnCompute.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        chkPopu.addActionListener(e -> onPopu());
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
        resMedian.setEditable(false);
        resMode.setEditable(false);
        resVariance.setEditable(false);
        resStdDev.setEditable(false);
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

    private void onRandomize(){
        Random random = new Random();

        StringBuilder inputTextBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
           double randomNum = random.nextDouble();
            values[i] = randomNum;
            inputTextBuilder.append(" ").append(randomNum);
        }
        inputText = inputTextBuilder.toString();
        txtInput.setText(inputText);

    }
    private void onCompute(){
        double mean, median, mode, variance, std, sum = 0;

        for(int i = 0; i < size; i++){
            sum = sum + values[i];
        }
        mean = sum / size;

    }
    private void onStart() {cl.show(cardPanel, "Card1");}
    private void onNext() {
        cl.show(cardPanel, "Card2");
        ProbVizualizer probChart = new ProbVizualizer(values);
        ChartPanel chartPanel = new ChartPanel(probChart.visualizeProb());
        resCenter.add(chartPanel);
    }
    private void onReturn() {
        cl.show(cardPanel, "Card1");
    }
    private void onPopu() {
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
