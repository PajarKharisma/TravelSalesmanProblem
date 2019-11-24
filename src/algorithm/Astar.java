package algorithm;

import node.Node;

import java.util.ArrayList;

public class Astar {

    private int[][] matrixDistance;
    private int numIndex;
    private ArrayList<Node> listOfSolution = new ArrayList<>();

    public Astar(int numIndex){
        this.numIndex = numIndex;
        matrixDistance = new int[numIndex][numIndex];
    }

    public void setMatrix(int value, int x, int y){
        this.matrixDistance[x][y] = value;
        this.matrixDistance[y][x] = value;
    }

    public int getDistance(int x, int y){
        return this.matrixDistance[x][y];
    }

    public ArrayList<Node> getResult(ArrayList<Node> listNode, Node initNode){
        initNode.setVisited(true);
        listOfSolution.add(initNode);
        while(listOfSolution.size() < listNode.size()){
            int minDistance = Integer.MAX_VALUE;
            Node minNode = null;
            Node currentNode = listOfSolution.get(listOfSolution.size()-1);
            for(Node childCurrNode:currentNode.getListChild()){
                int distance = matrixDistance[currentNode.getId()][childCurrNode.getId()];
                if (distance < minDistance && !childCurrNode.isVisited()){
                    minDistance = distance;
                    minNode = childCurrNode;
                }
            }
            minNode.setVisited(true);
            listOfSolution.add(minNode);
        }
        return listOfSolution;
    }

    public void printMatrix(){
        System.out.println("========================================");
        for (int i=0; i<numIndex; i++){
            for (int j=0; j<numIndex; j++){
                System.out.print(matrixDistance[i][j] + " ");
            }
            System.out.println();
        }
    }
}
