import java.util.*;

// Graph Node for A* Algorithm
class Node implements Comparable<Node> {
    String name;
    int g, h;
    Node parent;
    
    Node(String name, int g, int h) {
        this.name = name;
        this.g = g;
        this.h = h;
    }
    
    int f() { return g + h; }
    
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.f(), o.f());
    }
}

// Edge Class for Kruskal’s Algorithm
class Edge implements Comparable<Edge> {
    int src, dest, weight;
    
    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

// Disjoint Set for Kruskal’s Algorithm
class DisjointSet {
    private Map<Integer, Integer> parent = new HashMap<>();
    
    void makeSet(int v) { parent.put(v, v); }
    
    int find(int v) {
        if (parent.get(v) == v) return v;
        parent.put(v, find(parent.get(v)));  // Path compression
        return parent.get(v);
    }
    
    void union(int a, int b) {
        int rootA = find(a), rootB = find(b);
        if (rootA != rootB) parent.put(rootA, rootB);
    }
}

public class AdvancedAlgorithms {
    
    // A* Algorithm Implementation
    public static void aStarSearch(Map<String, List<Node>> graph, String start, String goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();
        openSet.add(new Node(start, 0, heuristic(start, goal)));
        
        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.name.equals(goal)) {
                reconstructPath(current);
                return;
            }
            closedSet.add(current.name);
            
            for (Node neighbor : graph.getOrDefault(current.name, new ArrayList<>())) {
                if (closedSet.contains(neighbor.name)) continue;
                Node nextNode = new Node(neighbor.name, current.g + neighbor.g, heuristic(neighbor.name, goal));
                nextNode.parent = current;
                openSet.add(nextNode);
            }
        }
    }
    
    private static int heuristic(String a, String b) { return Math.abs(a.hashCode() - b.hashCode()) % 10; }
    
    private static void reconstructPath(Node node) {
        List<String> path = new ArrayList<>();
        while (node != null) {
            path.add(node.name);
            node = node.parent;
        }
        Collections.reverse(path);
        System.out.println("A* Path: " + path);
    }
    
    // Kruskal’s Algorithm Implementation
    public static void kruskalMST(List<Edge> edges, int vertices) {
        Collections.sort(edges);
        DisjointSet ds = new DisjointSet();
        edges.forEach(e -> { ds.makeSet(e.src); ds.makeSet(e.dest); });
        
        List<Edge> mst = new ArrayList<>();
        for (Edge edge : edges) {
            if (ds.find(edge.src) != ds.find(edge.dest)) {
                mst.add(edge);
                ds.union(edge.src, edge.dest);
            }
        }
        
        System.out.println("Kruskal MST: " + mst);
    }
    
    // Kahn’s Algorithm for Topological Sorting
    public static void kahnTopSort(Map<Integer, List<Integer>> graph, int vertices) {
        int[] inDegree = new int[vertices];
        for (List<Integer> neighbors : graph.values()) {
            for (int v : neighbors) inDegree[v]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }
        
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (--inDegree[neighbor] == 0) queue.add(neighbor);
            }
        }
        
        System.out.println("Kahn’s Topological Sort: " + order);
    }
    
    public static void main(String[] args) {
        // Example Usage:
        Map<String, List<Node>> graph = new HashMap<>();
        graph.put("A", Arrays.asList(new Node("B", 1, 3), new Node("C", 4, 1)));
        graph.put("B", Arrays.asList(new Node("D", 2, 1)));
        graph.put("C", Arrays.asList(new Node("D", 1, 2)));
        aStarSearch(graph, "A", "D");
        
        List<Edge> edges = Arrays.asList(new Edge(0, 1, 4), new Edge(1, 2, 8), new Edge(2, 3, 7));
        kruskalMST(edges, 4);
        
        Map<Integer, List<Integer>> dag = new HashMap<>();
        dag.put(0, Arrays.asList(1, 2));
        dag.put(1, Arrays.asList(3));
        dag.put(2, Arrays.asList(3));
        kahnTopSort(dag, 4);
    }
}
