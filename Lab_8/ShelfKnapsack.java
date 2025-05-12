import java.util.Scanner;

public class ShelfKnapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: Number of products
        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] profits = new int[n];

        // Input: weights and profits of each product
        for (int i = 0; i < n; i++) {
            System.out.println("Enter weight and profit for product " + (i + 1) + ": ");
            weights[i] = sc.nextInt();
            profits[i] = sc.nextInt();
        }

        // Input: Maximum shelf capacity
        System.out.print("Enter maximum shelf capacity: ");
        int capacity = sc.nextInt();

        // V[i][j] = max profit using first i items and capacity j
        int[][] V = new int[n + 1][capacity + 1];

        // Build the V table using i for items and j for capacity
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    V[i][j] = Math.max(profits[i - 1] + V[i - 1][j - weights[i - 1]], V[i - 1][j]);
                } else {
                    V[i][j] = V[i - 1][j];
                }
            }
        }

        // Output: Maximum profit achievable
        System.out.println("\nMaximum Profit: " + V[n][capacity]);

        // Trace back selected products
        System.out.println("Selected products (0-based indices):");
        int j = capacity;
        for (int i = n; i > 0 && j > 0; i--) {
            if (V[i][j] != V[i - 1][j]) {
                System.out.println("Product " + (i - 1) + " (Weight: " + weights[i - 1] + ", Profit: " + profits[i - 1] + ")");
                j -= weights[i - 1];
            }
        }

        sc.close();
    }
}