package node;

import java.awt.*;
import java.util.ArrayList;

public class Node {

    private int id;
    private String name;
    private boolean visited;
    private Point pos;
    private ArrayList<Node> listChild;

    public Node() {
        this.listChild = new ArrayList<>();
    }

    public Node(int id, String name, boolean visited, Point pos) {
        this.id = id;
        this.name = name;
        this.visited = visited;
        this.pos = pos;
        this.listChild = new ArrayList<>();
    }

    public void addChild(Node node){
        this.listChild.add(node);
    }

    public ArrayList<Node> getListChild(){
        return this.listChild;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void printData(){
        System.out.println("Nama kota : " + this.name);
        for (Node child:listChild){
            System.out.println("-> " + child.getName() + " : " + child.isVisited());
        }
        System.out.println("====================================");
    }
}
