import java.util.*;

public class SearchSort {

    // Sequential Search: searches for a 'key' in the array
    public static int sequentialSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key)
                return i; // Return index if key is found
        }
        return -1; // Return -1 if key is not found
    }

    // Selection Sort: sorts the array in ascending order
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx])
                    minIdx = j; // Find index of minimum element
            }
            // Swap the minimum element with the current element
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // Get the number of different sizes to test
        System.out.print("Enter number of different sizes to test: ");
        int count = sc.nextInt();

        // Array to store the sizes entered by the user
        int[] sizes = new int[count];
        for (int i = 0; i < count; i++) {
            System.out.print("Enter size " + (i + 1) + " : ");
            sizes[i] = sc.nextInt(); // User inputs array size
        }

        // Display header
        System.out.println("\nn\tSearch(ms)\tSort(ms)");

        // Process each array size
        for (int n : sizes) {
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = rand.nextInt(10000); // Fill array with random numbers
            }

            int key = data[rand.nextInt(n)]; // Select a random key for searching

            // Measure time for sequential search
            long startSearch = System.currentTimeMillis();
            sequentialSearch(data, key);
            long endSearch = System.currentTimeMillis();

            // Copy array for sorting
            int[] copy = Arrays.copyOf(data, n);

            // Measure time for selection sort
            long startSort = System.currentTimeMillis();
            selectionSort(copy);
            long endSort = System.currentTimeMillis();

            // Output the results for the current size
            System.out.println(n + "\t" + (endSearch - startSearch) + "\t\t" + (endSort - startSort));
        }
        sc.close();
    }
}
