package sample;

import ru.ifmo.ctddev.bandarchuk.expression.DivisionByZeroException;
import ru.ifmo.ctddev.bandarchuk.expression.OverflowException;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by YuryBandarchuk on 5/28/17.
 */
public class Mode2DWindow extends JFrame implements Runnable {

    private JPanel panel;
    private Graphics2D graphics;

    public Mode2DWindow(){
        panel = new JPanel();
        setTitle("2D Mode");
        setBounds(300, 500, 600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        panel.setLayout(null);
        setFocusable(true);
    }

    private AlphaComposite makeComposite(float alpha) {
        alpha = Math.abs(alpha);
        alpha = Math.min(alpha, 1.0f);
        alpha = Math.max(alpha, 0.0f);
        //alpha = 1.0f;
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.graphics = (Graphics2D) g;
        double maxE = 0;
        for (double x = 0.1; x <= 600.0; x += 2) {
            for (double y = 0.1; y <= 399.9; y += 2) {
                double z = 0.0;
                try {
                    double e = Controller.field.getPhiAt(x, y, z);
                    maxE = Math.max(maxE, Math.abs(e));
                } catch (OverflowException | DivisionByZeroException e1) {
                    e1.printStackTrace();
                }
            }
        }
        double add = 0.0;
        if (maxE == 0) {
            maxE = 1.0;
            add = maxE;
        }
        for (double px = 0.1; px <= 600.0; px += 2) {
            for (double py = 0.1; py <= 399.9; py += 2) {
                double z = 0.0;
                try {
                    double x = px;
                    double y = 400.0 - py;
                    double e = Controller.field.getPhiAt(x, y, z) + add;
                    this.graphics.setComposite(makeComposite((float)(Math.abs(e) / maxE)));
                    this.graphics.setColor(Color.RED);
                    Ellipse2D.Double ellipse2D = new Ellipse2D.Double(x - 2, y - 2, 4, 4);
                    this.graphics.fill(ellipse2D);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void start() throws InterruptedException {
        getContentPane().add(panel);
        setVisible(true);
        paint(this.getGraphics());
    }

    public void close() {
        setVisible(false);
        dispose();
    }

    @Override
    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
