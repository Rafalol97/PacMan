package rafalwisnia.AstarSearchAlgorithm;

import rafalwisnia.Entity.Entity;
import rafalwisnia.Entity.Pacmann;
import rafalwisnia.LevelUtilities.Board;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class PathFinder extends AStar<PathFinder.Node> {
    private int[][] map;
    private Entity pacman;
    private Board board;
    public static class Node {
        public int x;
        public int y;

        public  Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ") ";
        }
    }

    @Override
    public void getMap() {
        for(int i=0;i<14;i++){
            for(int j=0;j<20;j++){
                this.tempMap[j][i]=map[j][i];
            }
        }
    }

    public PathFinder(int[][] map, Entity pacman, Board board) {
        this.map = new int [20][14];
        for(int i=0;i<14;i++){
            for(int j=0;j<20;j++){
             this.map[j][i]=map[i][j];
            }
        }
        this.pacman =pacman;
        this.board = board;
    }

    protected boolean isGoal(Node node) {
        int coordinates[] = board.getTileWhereAmI(pacman.getX(),pacman.getY());
        return (node.x == coordinates[1]) && (node.y ==coordinates[0]);
    }

    protected Double g(Node from, Node to) {

        if (from.x == to.x && from.y == to.y)
            return 0.0;

        if (map[to.x][to.y] == 0)
            return 1.0;

        return Double.MAX_VALUE;
    }

    protected Double h(Node from, Node to) {

        /* Use the Manhattan distance heuristic.  */
          return Double.valueOf((Math.abs(from.x - to.x) + Math.abs(from.y - to.y)));
    }

    protected List<Node> generateSuccessors(Node node) {
        List<Node> ret = new LinkedList<Node>();
        int x = node.x;
        int y = node.y;
        if(y>0&& y<14 &&x>0&&x<20) {
            if ( tempMap[x][y + 1] == 0) {
                ret.add(new Node(x, y + 1));
                tempMap[x][y] = 1;
            }
            if ( tempMap[x + 1][y] == 0) {
                ret.add(new Node(x + 1, y));
                tempMap[x][y] = 1;
            }
            if ( tempMap[x][y - 1] == 0) {
                ret.add(new Node(x, y - 1));
                tempMap[x][y] = 1;
            }
            if ( tempMap[x - 1][y] == 0) {
                ret.add(new Node(x - 1, y));
                tempMap[x][y] = 1;
            }
        }
        return ret;
    }
}