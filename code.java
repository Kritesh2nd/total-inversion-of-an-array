//Write a program to calculate the total inversion of an array. The inversion of an array is defined as a pair of indices for which i < j and array[i] > array[j].

public class Main {
    public static int countInversions(int[] arr) {
        int[] temp = new int[arr.length];
        return mergeSortAndCount(arr, temp, 0, arr.length - 1);
    }

    private static int mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = (left + right) / 2;
        int inversions = 0;
        
        inversions += mergeSortAndCount(arr, temp, left, mid);
        inversions += mergeSortAndCount(arr, temp, mid + 1, right);
        inversions += mergeAndCount(arr, temp, left, mid, right);

        return inversions;
    }

    private static int mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left, inversions = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                inversions += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        System.arraycopy(temp, left, arr, left, right - left + 1);
        return inversions;
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 2, 8, 1, 3, 6};
        System.out.println("Total Inversions: " + countInversions(array));
    }
}

// To count inversions in an array, I use a modified Merge Sort that counts how many times a larger number appears before a smaller one while sorting the array. 
// This approach runs in O(n log n) time, making it much faster than a brute-force method.
