import java.util.*;

public class OptimalRoadNetwork {
    static int[] parent;

    static int find(int node) {
        if (parent[node] != node)
            parent[node] = find(parent[node]); // Path compression
        return parent[node];
    }

    static boolean union(int nodeA, int nodeB) {
        int rootA = find(nodeA), rootB = find(nodeB);
        if (rootA == rootB) return false;
        parent[rootA] = rootB;
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of towns: ");
        int numTowns = scanner.nextInt();
        System.out.print("Number of roads: ");
        int numRoads = scanner.nextInt();

        int[][] edges = new int[numRoads][3];
        System.out.println("Enter road data (src dest weight): ");
        for (int i = 0; i < numRoads; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            edges[i] = new int[]{src, dest, weight};
        }

        Arrays.sort(edges, Comparator.comparingInt(e -> e[2])); // sort by weight

        parent = new int[numTowns];
        for (int i = 0; i < numTowns; i++) 
            parent[i] = i;
        int totalCost = 0;
        System.out.println("\nOptimal Road Network (MST):");
        for (int[] edge : edges) {
            int src = edge[0], dest = edge[1], weight = edge[2];
            if (union(src, dest)) {
                System.out.println(src + " - " + dest + " : " + weight);
                totalCost += weight;
            }
        }
        System.out.println("Total road length: " + totalCost);
        scanner.close();
    }
}