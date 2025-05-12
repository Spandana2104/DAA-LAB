import java.util.*;

public class WaterSupplyNetwork {

    // Utility class to represent an edge
    static class Edge {
        int source, destination, weight;
        
        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Method to find the minimum spanning tree using Prim's algorithm
    public static void primMST(int n, int[][] graph) {
        // Array to keep track of the parent node of each node in the MST
        int[] parent = new int[n];
        // Array to store the minimum cost to connect each node to the MST
        int[] key = new int[n];
        // Set to check if the node is already included in the MST
        boolean[] inMST = new boolean[n];
        
        // Initialize all keys as infinity and parent as -1
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        
        // Start from the first node (arbitrary choice)
        key[0] = 0;

        // Loop until all vertices are included in MST
        for (int count = 0; count < n - 1; count++) {
            // Find the vertex with the minimum key value not yet included in MST
            int u = minKey(key, inMST, n);
            inMST[u] = true;

            // Update key values of adjacent vertices of u
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        // Print the constructed MST (the optimized water supply network)
        printMST(parent, graph);
    }

    // Find the vertex with the minimum key value not yet included in MST
    public static int minKey(int[] key, boolean[] inMST, int n) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        
        for (int v = 0; v < n; v++) {
            if (!inMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        
        return minIndex;
    }

    // Print the MST (optimized water distribution network)
    public static void printMST(int[] parent, int[][] graph) {
        System.out.println("\nOptimized Water Supply Network (MST):");
        for (int i = 1; i < parent.length; i++) {
            System.out.println("Area " + (parent[i] + 1) + " -> Area " + (i + 1) + " (Cost: " + graph[i][parent[i]] + ")");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of areas (nodes) in the region
        System.out.print("Enter the number of areas in the region: ");
        int n = sc.nextInt();
        
        // Initialize graph (cost of infrastructure between areas)
        int[][] graph = new int[n][n];

        // Input the cost between each pair of areas
        System.out.println("Enter the cost between the areas (0 if no connection):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    System.out.print("Cost between Area " + (i + 1) + " and Area " + (j + 1) + ": ");
                    graph[i][j] = sc.nextInt();
                }
            }
        }

        // Call Prim's algorithm to find the MST
        primMST(n, graph);
        sc.close();
    }
}
