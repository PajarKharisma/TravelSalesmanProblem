package gui;

import algorithm.Astar;
import algorithm.Ecludian;
import node.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener {

    public JComboBox jcStart, jcEnd;
    public JButton btnSet, btnFixMap;

    private DrawPanel drawPanel;
    private ResultPanel resultPanel;
    private Ecludian ecludian = new Ecludian();

    public ControlPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBounds(20, 420, 600, 90);

        jcStart = new JComboBox();
        jcStart.setPreferredSize(new Dimension(80, 30));
        add(jcStart);

        jcEnd = new JComboBox();
        jcEnd.setPreferredSize(new Dimension(80, 30));
        add(jcEnd);

        btnSet = new JButton("Set Jarak");
        btnSet.setPreferredSize(new Dimension(120, 30));
        btnSet.addActionListener(this);
        add(btnSet);

        btnFixMap = new JButton("Fix Map");
        btnFixMap.setPreferredSize(new Dimension(120, 30));
        btnFixMap.addActionListener(this);
        add(btnFixMap);
    }

    public void setOtherPanel(DrawPanel drawPanel, ResultPanel resultPanel) {
        this.resultPanel = resultPanel;
        this.drawPanel = drawPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if(ob.equals(btnFixMap)){
            drawPanel.fixMap = true;
            resultPanel.astar = new Astar(jcStart.getItemCount());
            JOptionPane.showMessageDialog(null, "State Locked");
        }

        if (ob.equals(btnSet)) {
            String startPoint = (String) jcStart.getSelectedItem();
            String endPoint = (String) jcEnd.getSelectedItem();
            if (startPoint.equals(endPoint)) {
                JOptionPane.showMessageDialog(null, "Titik awal dan tujuan harus berbeda");
            } else {
                Node startNode = null;
                Node endNode = null;
                for(Node node:drawPanel.listNode){
                    if(node.getName().equals(startPoint)){
                        startNode = node;
                        drawPanel.listStartPos.add(node.getPos());
                    }
                    if(node.getName().equals(endPoint)){
                        endNode = node;
                        drawPanel.listEndPos.add(node.getPos());
                    }
                }
                startNode.addChild(endNode);
                endNode.addChild(startNode);
                int distance = ecludian.getDistance(startNode.getPos(), endNode.getPos());
                drawPanel.listDistance.add(new DistanceData(startNode.getPos(), endNode.getPos(), distance));
                resultPanel.astar.setMatrix(distance, startNode.getId(), endNode.getId());
                drawPanel.repaint();
            }
        }
    }
}
