package sample;

import ru.ifmo.ctddev.bandarchuk.expression.DivisionByZeroException;
import ru.ifmo.ctddev.bandarchuk.expression.OverflowException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

/**
 * Created by YuryBandarchuk on 5/28/17.
 */
public class Mode2DWindow extends JFrame implements Runnable {

    private sample.Type currentMode;

    private JPanel panel;
    private Graphics2D graphics;

    public Mode2DWindow() {
        panel = new JPanel();
        setTitle("2D Mode");
        setBounds(300, 500, 600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        panel.setLayout(null);
        setFocusable(true);
        currentMode = sample.Type.E_FIELD;
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    switchMode();
                    repaintAfterModeUpdate();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double x = e.getX();
                double y = e.getY();
                double value = 0.0;
                try {
                    value = getValue(x, y, 0.0);
                } catch (OverflowException | DivisionByZeroException e1) {
                    e1.printStackTrace();
                }
                String valueName = "напряженности";
                if (currentMode == sample.Type.PHI_FIELD) {
                    valueName = "потенциала";
                }
                JOptionPane.showMessageDialog(null, "Значение " + valueName + " в выбранной вами точке = " + value);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


    private final double radius = 1.5;
    private final double halfRadius = radius / 2.0;
    private final float borderAlpha = 0.2f;
    private final int halfValueOfBrokenAlpha = (int)(600.0 * 400.0 / (2.0 * radius * radius)) + 5;
    private int counter = 0;

    private void resetCounter() {
        counter = 0;
    }

    private void ensureCounter() {
        if (counter > halfValueOfBrokenAlpha) {
            JOptionPane.showMessageDialog(null, "Большинство значений рассчитываемой величины слишком малы, нарисуем обратный график");
            sample.Type wasType = currentMode;
            currentMode = sample.Type.BLUE_COLOR;
            paint(this.getGraphics());
            currentMode = wasType;
        }
    }

    private AlphaComposite makeComposite(float alpha) {
        alpha = Math.abs(alpha);
        alpha = Math.min(alpha, 1.0f);
        alpha = Math.max(alpha, 0.0f);
        int type = AlphaComposite.SRC_OVER;
        if (alpha < borderAlpha) {
            ++counter;
        }
        return (AlphaComposite.getInstance(type, alpha));
    }

    private double getValue(double x, double y, double z) throws OverflowException, DivisionByZeroException {
        double value = 0.0;
        switch (currentMode) {
            case E_FIELD:
                value = Controller.field.getEAt(x, y, z);
                break;
            case PHI_FIELD:
                value = Controller.field.getPhiAt(x, y, z);
                break;
            default:
                break;
        }
        return value;
    }

    private void switchMode() {
        if (currentMode == sample.Type.E_FIELD) {
            currentMode = sample.Type.PHI_FIELD;
        } else {
            currentMode = sample.Type.E_FIELD;
        }
    }

    private void repaintAfterModeUpdate() {
        this.getGraphics().setColor(Color.WHITE);
        this.getGraphics().fillRect(0, 0, 600, 400);
        paint(this.getGraphics());
    }

    public void paint(Graphics g) {
        resetCounter();
        super.paint(g);
        this.graphics = (Graphics2D) g;
        double maxValue = 0;
        for (double x = 0.1; x <= 600.0; x += radius) {
            for (double y = 0.1; y <= 399.9; y += radius) {
                double value = 0;
                try {
                    value = getValue(x, y, 0.0);
                } catch (OverflowException | DivisionByZeroException e) {
                    e.printStackTrace();
                }
                maxValue = Math.max(maxValue, Math.abs(value));
            }
        }
        double add = 0.0;
        if (maxValue == 0) {
            maxValue = 1.0;
            add = maxValue;
        }
        for (double px = 0.1; px <= 600.0; px += radius) {
            for (double py = 0.1; py <= 399.9; py += radius) {
                try {
                    double y = 400.0 - py;
                    double value = getValue(px, y, 0.0) + add;
                    this.graphics.setComposite(makeComposite((float)(Math.abs(value) / maxValue)));
                    this.graphics.setColor(Color.RED);
                    if (currentMode == sample.Type.PHI_FIELD) {
                        this.graphics.setColor(Color.GREEN);
                    } else if (currentMode == sample.Type.BLUE_COLOR) {
                        this.graphics.setColor(Color.BLUE);
                    }
                    Ellipse2D.Double ellipse2D = new Ellipse2D.Double(px - halfRadius, y - halfRadius, radius, radius);
                    this.graphics.fill(ellipse2D);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (currentMode != sample.Type.BLUE_COLOR) {
            ensureCounter();
        }
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
