public class BinaryInsertionSort {
    public static void binaryInsertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int insertPosition = binarySearch(arr, key, 0, i - 1);
            int j = i - 1;
            while (j >= insertPosition) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[insertPosition] = key;
        }
    }
    private static int binarySearch(int[] arr, int key, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (key < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;  
    }
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {37, 23, 0, 17, 12, 72, 31};
        System.out.println("Original array:");
        printArray(arr);
        binaryInsertionSort(arr);
        System.out.println("Sorted array:");
        printArray(arr);
    }
}
