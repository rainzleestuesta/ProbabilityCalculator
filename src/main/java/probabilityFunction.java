import org.jfree.chart.ChartPanel;

import javax.swing.*;
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
    private JCheckBox populationCheckBox;
    private JCheckBox sampleCheckBox;
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

    private int size;
    private double[] values;
    public probabilityFunction() {
        cardPanel.setLayout(cl);
        btnStart.addActionListener(e -> onStart());
        btnGraph.addActionListener(e -> onNext());
        btnReturn.addActionListener(e -> onReturn());
        btnRandomize.addActionListener(e -> onRandomize());

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

        for(int i =0; i < size; i++){
            int randomNum = random.nextInt();
            values[i] = randomNum;
        }
        txtInput.setText(Arrays.toString(values));
    }
    private void onStart() {cl.show(cardPanel, "Card1");}
    private void onNext() {
        cl.show(cardPanel, "Card2");
        ProbVizualizer probChart = new ProbVizualizer(new double[] {1,2,2,3,3,3,4,4,5});
        ChartPanel chartPanel = new ChartPanel(probChart.visualizeProb());
        resCenter.add(chartPanel);
    }
    private void onReturn() {
        cl.show(cardPanel, "Card1");
    }

    public static void main(String[] args) {
        new probabilityFunction();
    }
}
