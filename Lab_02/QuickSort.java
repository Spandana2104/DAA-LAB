import java.util.*;

public class QuickSort {

    // Partition method: rearranges elements around a pivot
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Choose the last element as pivot
        int i = low - 1;  // Index of the smaller element
        
        // Loop through the array and rearrange elements
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Swap the pivot with the element at i+1
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;  // Return the partition index
    }

    // QuickSort method: recursively sorts the elements
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);  // Partition the array
            
            // Recursively sort the two halves
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
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
            System.out.print("Enter size #" + (i + 1) + ": ");
            sizes[i] = sc.nextInt(); // User inputs array size
        }

        // Display header for output
        System.out.println("\nn\tTime(ms)");

        // Process each array size
        for (int n : sizes) {
            // Generate an array of size 'n' with random values
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = rand.nextInt(10000); // Fill array with random numbers
            }

            // Measure time for QuickSort
            long startTime = System.currentTimeMillis();
            quickSort(data, 0, n - 1); // Perform QuickSort
            long endTime = System.currentTimeMillis();

            // Output the results: array size and time taken
            System.out.println(n + "\t" + (endTime - startTime));
        }
        sc.close();
    }
}
