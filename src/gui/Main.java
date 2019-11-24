package gui;

import com.sun.javaws.jnl.RContentDesc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private static DrawPanel drawPanel;
    private static ControlPanel controlPanel;
    private static ResultPanel resultPanel;

    public Main(){
        super("Muhammad Pajar Kharisma Putra");
        setLayout(null);
        setSize(960,550);
        setLocationRelativeTo(null);

        add(controlPanel);
        add(drawPanel);
        add(resultPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                drawPanel = new DrawPanel();
                controlPanel = new ControlPanel();
                resultPanel = new ResultPanel();

                drawPanel.setOtherPanel(controlPanel, resultPanel);
                controlPanel.setOtherPanel(drawPanel, resultPanel);
                resultPanel.setOtherPanel(drawPanel, controlPanel);

                new Main();
            }
        });
    }
}
