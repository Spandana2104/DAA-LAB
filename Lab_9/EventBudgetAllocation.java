import java.util.Scanner;

public class EventBudgetAllocation {
    static String[] items;
    static int[] costs;
    static int budget, maxItems = 0;
    static boolean[] best, temp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt(); 
        sc.nextLine();

        items = new String[n];
        costs = new int[n];
        best = new boolean[n];
        temp = new boolean[n];

        System.out.print("Enter item names and costs: ");
        for (int i = 0; i < n; i++) {
            System.out.print("\nItem " + (i + 1) + ": ");
            items[i] = sc.nextLine();
            System.out.print("Cost: ");
            costs[i] = sc.nextInt(); 
            sc.nextLine();
        }

        System.out.print("\nEnter total budget: ");
        budget = sc.nextInt();

        allocate(0, 0, 0);

        System.out.println("\nBest Event Plan Within Budget:");
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (best[i]) {
                System.out.println("- " + items[i] + " (Cost: " + costs[i] + ")");
                total += costs[i];
            }
        }

        System.out.println("Total Cost: " + total);
        System.out.println("Total Items Selected: " + maxItems);
        sc.close();
    }

    static void allocate(int i, int currCost, int count) {
        if (currCost > budget) return;
        if (currCost == budget && count > maxItems) {
            maxItems = count;
            System.arraycopy(temp, 0, best, 0, temp.length);
        }
        if (i == items.length) return;

        temp[i] = true;
        allocate(i + 1, currCost + costs[i], count + 1);
        temp[i] = false;
        allocate(i + 1, currCost, count);
    }
}