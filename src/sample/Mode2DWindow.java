package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by YuryBandarchuk on 5/28/17.
 */
public class Mode2DWindow extends JFrame {

    public Mode2DWindow(){
        JPanel panel=new JPanel();
        getContentPane().add(panel);
        setSize(450,450);

        JButton button =new JButton("press");
        panel.add(button);
    }

    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(100, 100, 250, 260);
        g2.draw(lin);
    }
}
