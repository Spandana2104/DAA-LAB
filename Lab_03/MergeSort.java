import java.util.*;

public class MergeSort{

    // Merge method: merges two sorted sub-arrays into one
    public static void merge(int[] arr, int left, int mid, int right) {
        int[] leftArray = Arrays.copyOfRange(arr, left, mid + 1);  // Left sub-array
        int[] rightArray = Arrays.copyOfRange(arr, mid + 1, right + 1);  // Right sub-array

        int i = 0, j = 0, k = left;

        // Merge the two arrays into the original array
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        // Copy any remaining elements from the left sub-array
        while (i < leftArray.length) {
            arr[k++] = leftArray[i++];
        }

        // Copy any remaining elements from the right sub-array
        while (j < rightArray.length) {
            arr[k++] = rightArray[j++];
        }
    }

    // Merge Sort method: recursively divides the array and calls merge
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Recursively sort the two halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Main method to test MergeSort performance
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // Get the number of different sizes to test
        System.out.print("Enter number of different sizes to test: ");
        int count = sc.nextInt();

        // Array to store sizes
        int[] sizes = new int[count];
        for (int i = 0; i < count; i++) {
            System.out.print("Enter size #" + (i + 1) + ": ");
            sizes[i] = sc.nextInt(); // User inputs array size
        }

        // Display header for output
        System.out.println("\nn\tTime(ms)");

        // Process each array size
        for (int n : sizes) {
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = rand.nextInt(10000); // Fill array with random numbers
            }

            // Measure time for MergeSort
            long startTime = System.currentTimeMillis();
            mergeSort(data, 0, n - 1); // Perform MergeSort
            long endTime = System.currentTimeMillis();

            // Output the results: array size and time taken
            System.out.println(n + "\t" + (endTime - startTime));
        }
        sc.close();
    }
}
