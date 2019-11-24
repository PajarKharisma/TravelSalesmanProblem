package gui;

import algorithm.Astar;
import node.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResultPanel extends JPanel implements ActionListener {

    public Astar astar;
    public JTextArea txtResult;
    public JComboBox jcInit;

    private JButton btnResult;
    private DrawPanel drawPanel;
    private ControlPanel controlPanel;

    public ResultPanel(){
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBounds(640, 0, 280, 520);

        txtResult = new JTextArea();
        txtResult.setPreferredSize(new Dimension(280, 400));
        add(txtResult);

        jcInit = new JComboBox();
        jcInit.setPreferredSize(new Dimension(100, 30));
        add(jcInit);

        btnResult = new JButton("Proses");
        btnResult.setPreferredSize(new Dimension(100, 30));
        btnResult.addActionListener(this);
        add(btnResult);
    }

    public void setOtherPanel(DrawPanel drawPanel, ControlPanel controlPanel){
        this.drawPanel = drawPanel;
        this.controlPanel = controlPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if (ob.equals(btnResult)){
            Node startNode = null;

            for(Node node:drawPanel.listNode){
                if(node.getName().equals(jcInit.getSelectedItem())){
                    startNode = node;
                }
            }

            ArrayList<Node> listOfSolution = astar.getResult(drawPanel.listNode, startNode);
            String result = "";
            Node currentNode = listOfSolution.get(0);
            for (Node solutionNode : listOfSolution){
                result += solutionNode.getName() + " -> " + astar.getDistance(currentNode.getId(), solutionNode.getId()) + "\n";
                currentNode = solutionNode;
            }

            txtResult.setText(result);
        }
    }
}
