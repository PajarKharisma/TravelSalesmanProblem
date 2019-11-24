package gui;

import node.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    public ArrayList<Point> listStartPos = new ArrayList<Point>();
    public ArrayList<Point> listEndPos = new ArrayList<Point>();
    public ArrayList<Point> points;
    public ArrayList<Node> listNode = new ArrayList<Node>();
    public ArrayList<DistanceData> listDistance = new ArrayList<DistanceData>();
    public boolean fixMap = false;

    private ControlPanel controlPanel;
    private ResultPanel resultPanel;

    private int cityNameAcii = 65;
    private int cityId = 0;

    public DrawPanel(){
        points = new ArrayList<Point>();
        setPreferredSize(new Dimension(600, 400));
        setBounds(20,20, 600,400);
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if(!fixMap) {
                    Node node = new Node(
                            cityId,
                            String.valueOf(Character.toChars(cityNameAcii)),
                            false,
                            new Point(mouseEvent.getX(), mouseEvent.getY())
                    );
                    listNode.add(node);

                    points.add(node.getPos());
                    controlPanel.jcStart.addItem(node.getName());
                    controlPanel.jcEnd.addItem(node.getName());
                    resultPanel.jcInit.addItem(node.getName());

                    cityId++;
                    cityNameAcii++;
                    repaint();
                }
            }
        });
    }

    public void setOtherPanel(ControlPanel controlPanel, ResultPanel resultPanel){
        this.resultPanel = resultPanel;
        this.controlPanel = controlPanel;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font currentFont = g2.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.5F);
        g2.setFont(newFont);

        int index = 65;

        g2.setColor(Color.RED);
        for (DistanceData dd:listDistance){
            g2.drawString(""+dd.getDistance(), dd.getPos().x, dd.getPos().y + 25);
        }

        g2.setColor(Color.BLACK);
        for(int i=0; i<listStartPos.size(); i++){
            int corX1 = (int)listStartPos.get(i).getX() + 25;
            int corY1 = (int)listStartPos.get(i).getY() + 25;
            int corX2 = (int)listEndPos.get(i).getX() +25;
            int corY2 = (int)listEndPos.get(i).getY() + 25;
            g2.drawLine(corX1, corY1, corX2, corY2);
        }

        for(Point point:points){
            g2.setColor(Color.BLACK);
            g2.fillOval(point.x, point.y, 50, 50);
            g2.setColor(Color.WHITE);
            g2.drawString(String.valueOf(Character.toChars(index)), point.x + 20, point.y + 30);
            index++;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setLayout(null);
                frame.add(new DrawPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(960,600);
                frame.setVisible(true);
            }
        });
    }
}

class DistanceData{
    private Point startPos;
    private Point endPos;
    private int distance;

    public DistanceData() {
    }

    public DistanceData(Point startPos, Point endPos, int distance) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.distance = distance;
    }

    public Point getStartPos() {
        return startPos;
    }

    public void setStartPos(Point startPos) {
        this.startPos = startPos;
    }

    public Point getEndPos() {
        return endPos;
    }

    public void setEndPos(Point endPos) {
        this.endPos = endPos;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Point getPos(){
        return new Point((Math.abs(startPos.x + endPos.x))/2, (Math.abs(startPos.y + endPos.y))/2);
    }
}
