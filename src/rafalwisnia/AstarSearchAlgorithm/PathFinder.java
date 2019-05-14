package rafalwisnia.AstarSearchAlgorithm;

import java.util.LinkedList;
import java.util.List;

public class PathFinder extends AStar<PathFinder.Node> {
    private int[][] map;

    public static class Node {
        public int x;
        public int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ") ";
        }
    }

    public PathFinder(int[][] map) {
        this.map = map;
    }

    protected boolean isGoal(Node node) {
        return (node.x == map[0].length - 1) && (node.y == map.length - 1);
    }

    protected Double g(Node from, Node to) {

        if (from.x == to.x && from.y == to.y)
            return 0.0;

        if (map[to.y][to.x] == 1)
            return 1.0;

        return Double.MAX_VALUE;
    }

    protected Double h(Node from, Node to) {
        /* Use the Manhattan distance heuristic.  */
        return new Double((map[0].length - 1 - to.x) + Math.abs(map.length - 1 - to.y));
    }

    protected List<Node> generateSuccessors(Node node) {
        List<Node> ret = new LinkedList<Node>();
        int x = node.x;
        int y = node.y;
        if (y < map.length - 1 && map[y + 1][x] == 1)
            ret.add(new Node(x, y + 1));

        if (x < map[0].length - 1 && map[y][x + 1] == 1)
            ret.add(new Node(x + 1, y));

        return ret;
    }
}