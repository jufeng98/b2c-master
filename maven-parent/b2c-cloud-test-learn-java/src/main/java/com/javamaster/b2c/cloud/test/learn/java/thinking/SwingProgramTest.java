package com.javamaster.b2c.cloud.test.learn.java.thinking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author yu
 */
public class SwingProgramTest {
    static JLabel label = new JLabel("hello world");

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            label.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("you click me");
                }

            });
            frame.add(BorderLayout.CENTER, label);
            frame.setVisible(true);

            new Timer(1000, e -> {
                int i = (int) (Math.random() * 10);
                if (i % 2 == 0) {
                    label.setText("ni hao shi jie");
                } else {
                    label.setText("hello world");
                }

            }).start();
        });
    }
}
