import java.util.*;

public class OptimalRoadRoutes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of cities: ");
        int n = sc.nextInt();
        System.out.print("Enter the number of roads: ");
        int m = sc.nextInt();

        // Adjacency matrix to represent the road network
        int[][] adjMatrix = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(adjMatrix[i], Integer.MAX_VALUE); // Fill with infinity
        for (int i = 0; i < m; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int time = sc.nextInt();
            adjMatrix[src][dest] = time; // Assuming directed roads (src to dest)
            adjMatrix[dest][src] = time; // If roads are bidirectional
        }

        System.out.print("Enter source city: ");
        int source = sc.nextInt();

        // Apply Dijkstra's algorithm
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        boolean[] visited = new boolean[n];
        
        for (int count = 0; count < n - 1; count++) {
            int u = -1;
            for (int i = 0; i < n; i++)
                if (!visited[i] && (u == -1 || dist[i] < dist[u]))
                    u = i;
            
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && adjMatrix[u][v] != Integer.MAX_VALUE && dist[u] + adjMatrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + adjMatrix[u][v];
                }
            }
        }

        // Output shortest travel times
        System.out.println("Shortest travel times from city " + source + ":");
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.println("City " + i + ": No path");
            else
                System.out.println("City " + i + ": " + dist[i] + " hours");
        }
        sc.close();
    }
}
