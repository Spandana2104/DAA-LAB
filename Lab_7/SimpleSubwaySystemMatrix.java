import java.util.Scanner;

public class SimpleSubwaySystemMatrix {
    static final int INF = Integer.MAX_VALUE; // No direct path indicator

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Ask user for the number of stations
        System.out.print("Enter the number of stations: ");
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Step 2: Input station names
        String[] stations = new String[n];
        System.out.println("Enter station names:");
        for (int i = 0; i < n; i++) {
            System.out.print("Station " + (i + 1) + ": ");
            stations[i] = sc.nextLine();
        }

        // Step 3: Input travel times
        int[][] time = new int[n][n];
        System.out.println("\nEnter travel times (enter -1 if no direct path):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    time[i][j] = 0;
                } else {
                    System.out.print("From " + stations[i] + " to " + stations[j] + ": ");
                    int input = sc.nextInt();
                    time[i][j] = (input == -1) ? INF : input;
                }
            }
        }

        // Step 4: Apply Floyd-Warshall Algorithm with overflow protection
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (time[i][k] != INF && time[k][j] != INF && time[i][k] + time[k][j] < time[i][j]) {
                        time[i][j] = time[i][k] + time[k][j];
                    }
                }
            }
        }

        // Step 5: Display result
        System.out.println("\nShortest Travel Time Matrix (in minutes):");
        System.out.printf("%15s", "");
        for (String s : stations) 
            System.out.printf("%15s", s);
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("%15s", stations[i]);
            for (int j = 0; j < n; j++) {
                System.out.printf("%15s", (time[i][j] == INF ? "INF" : time[i][j]));
            }
            System.out.println();
        }

        sc.close();
    }
}
