import javax.swing.*;
import java.awt.*;

public class CLayoutTest {

    JFrame frame = new JFrame("Card Layout");
    JPanel panelCont = new JPanel();
    JPanel panelFirst = new JPanel();
    JPanel panelSecond = new JPanel();
    JButton btnOne = new JButton("Switch to panel 1");
    JButton btnTWo = new JButton("Switch to panel 2");
    CardLayout cl = new CardLayout();
    public CLayoutTest() {
        panelCont.setLayout(cl);
        panelFirst.add(btnOne);
        panelSecond.add(btnTWo);
        panelFirst.setBackground(Color.BLUE);
        panelSecond.setBackground(Color.GREEN);

        panelCont.add(panelFirst, "1");
        panelCont.add(panelSecond, "2");
        cl.show(panelCont, "Layout 1");

        btnOne.addActionListener(e -> cl.show(panelCont, "2"));
        btnTWo.addActionListener(e -> cl.show(panelCont, "1"));

        frame.add(panelCont);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CLayoutTest::new);
    }
}
