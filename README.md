# Advanced Graph Algorithms in Java  

This repository contains Java implementations of fundamental graph algorithms, including A* Search, Kruskal’s Minimum Spanning Tree (MST), and Kahn’s Topological Sorting Algorithm. These implementations demonstrate efficient pathfinding, graph traversal, and sorting techniques used in computer science and real-world applications of.  

## Features  

### A* Search Algorithm  
- Implements an **informed search algorithm** to find the shortest path between nodes.  
- Uses a **priority queue** (min-heap) to efficiently process the most promising nodes first.  
- Employs a **heuristic function** to guide the search towards the goal.  
- Supports dynamic graph structures with customizable heuristics.  

### Kruskal’s Algorithm (Minimum Spanning Tree)  
- Implements **Kruskal’s MST algorithm** using the **Greedy approach**.  
- Utilizes a **Disjoint Set (Union-Find) data structure** with **path compression** for efficiency.  
- Sorts edges by weight and incrementally adds them to the MST while preventing cycles.  
- Suitable for **network design problems** such as laying out electrical grids or road networks.  

### Kahn’s Algorithm (Topological Sorting)  
- Implements **Kahn’s algorithm** to produce a valid topological ordering of a **Directed Acyclic Graph (DAG)**.  
- Uses **in-degree tracking** and a **queue-based approach** for efficient sorting.  
- Ensures that dependencies are processed in the correct order.  
- Useful in **task scheduling, dependency resolution, and compiler optimization**.  

## How to Use  

1. **Clone the repository**  
   ```bash
   git clone https://github.com/your-username/AdvancedGraphAlgorithms.git
   cd AdvancedGraphAlgorithms
   ```
   
2. **Compile and run the Java program**  
   ```bash
   javac AdvancedAlgorithms.java
   java AdvancedAlgorithms
   ```

3. **Modify the graph structure**  
   - Edit the `main` method to customize input graphs for A*, Kruskal’s MST, or Kahn’s sorting.  
   - Adjust heuristic functions or edge weights for different use cases.  

## Example Usage 

```java
// Define a sample graph for A* search
Map<String, List<Node>> graph = new HashMap<>();
graph.put("A", Arrays.asList(new Node("B", 1, 3), new Node("C", 4, 1)));
graph.put("B", Arrays.asList(new Node("D", 2, 1)));
graph.put("C", Arrays.asList(new Node("D", 1, 2)));

// Run A* Search from "A" to "D"
aStarSearch(graph, "A", "D");

// Define edges for Kruskal’s MST
List<Edge> edges = Arrays.asList(new Edge(0, 1, 4), new Edge(1, 2, 8), new Edge(2, 3, 7));
kruskalMST(edges, 4);

// Define a DAG for Kahn’s Topological Sorting
Map<Integer, List<Integer>> dag = new HashMap<>();
dag.put(0, Arrays.asList(1, 2));
dag.put(1, Arrays.asList(3));
dag.put(2, Arrays.asList(3));
kahnTopSort(dag, 4);
```

## Applications  
- **A* Search**: AI pathfinding (e.g., game development, GPS navigation).  
- **Kruskal’s Algorithm**: Network design (e.g., computer networks, transport systems).  
- **Kahn’s Algorithm**: Task scheduling (e.g., project management, compiler design).  

## Contributions  
Contributions, optimizations, and improvements are welcome! Feel free to fork this repository and submit pull requests.

Output:
A* Path: [A, B, D]

Kruskal MST: [Edge@764c12b6, Edge@c387f44, Edge@4e0e2f2a]

Kahn’s Topological Sort: [0, 1, 2, 3]
