package sample;

import javax.swing.*;

/**
 * Created by YuryBandarchuk on 5/28/17.
 */
public class Mode2DWindow extends JFrame implements Runnable {

    private JPanel panel;

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

    public void start() throws InterruptedException {
        getContentPane().add(panel);
        setVisible(true);
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
