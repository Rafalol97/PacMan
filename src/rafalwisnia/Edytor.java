package rafalwisnia;

import rafalwisnia.UI.Sprite;

import javax.swing.*;
import java.awt.*;

class EdytorPanel extends JPanel {



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0 ,0, 100,100);
    }
}

public class Edytor extends JPanel {

    EdytorPanel edytor = new EdytorPanel();

    public JFrame frame;

    public Edytor() {
        frame = new JFrame();
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setSize(1400, 900);
        frame.setLocationRelativeTo(null);
        frame.add(edytor);
        edytor.setBounds(0, 0, 1400, 900);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
