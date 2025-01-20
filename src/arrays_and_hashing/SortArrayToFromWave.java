package arrays_and_hashing;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/sort-array-wave-form-2/
 */
public class SortArrayToFromWave {

    //O(n) TC
    public void sortInWave(int[] arr) {
        //Ex: {10, 5, 6, 3, 2, 20, 100, 80}
        //o/p: {10, 5, 6, 2, 20, 3, 100, 80}
        /*
         * We will visit even numbered indices and check if the element at that index > prev and > current. If yes, then just go to next even numbered index
         * If not, check if element at index < prev, if yes then swap them
         * Check if element at index < next, if yes then swap them
         * We basically want large small large small large ....
         */
        for (int i = 0; i < arr.length; i = i + 2) {
            if (i > 0 && arr[i] < arr[i - 1]) {
                //Swap them
                swap(arr, i, i - 1);
            }
            if (i < arr.length - 1 && arr[i] < arr[i + 1]) {
                //Swap them
                swap(arr, i, i + 1);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 6, 3, 2, 20, 100, 80};
        SortArrayToFromWave sortArrayToFromWave = new SortArrayToFromWave();
        sortArrayToFromWave.sortInWave(arr);
        System.out.println(Arrays.toString(arr));
    }
}
