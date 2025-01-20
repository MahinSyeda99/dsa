package binarySearch;

class Search2DMatrix {

    private static boolean searchMatrixNew(int[][] arr, int target) {
        int m = arr.length;
        int n = arr[0].length;
        //O(log(n*m)) - Optimized solution
        //Converting 2D array to 1D array visually
        //Condition -  Rows sorted, columns sorted and first element of next row > last element of prev row
        int low = 0;
        int high = m * n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int iR = mid / n;
            int iC = mid % n;
            if (arr[iR][iC] == target) {
                return true;
            } else if (arr[iR][iC] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        //O(m*logn)
        for (int i = 0; i < matrix.length; i++) {
            if (target >= matrix[i][0] && target <= matrix[i][matrix[0].length - 1]) {
                return binarySearch(matrix[i], target, 0, matrix[0].length - 1);
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] arr, int target, int low, int high) {
        //Condition -  Rows sorted, columns sorted and first element of next row > last element of prev row
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = {{1}, {3}};
        //O(m*logn)
        System.out.println(searchMatrix(arr, 3));
        //O(log(n*m))
        System.out.println(searchMatrixNew(arr, 3));
    }
}