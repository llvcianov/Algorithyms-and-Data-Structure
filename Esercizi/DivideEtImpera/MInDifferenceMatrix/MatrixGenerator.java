import java.util.Random;
import java.util.Arrays;

public class MatrixGenerator {

    public static void main(String[] args) {
        int[][] matrix = generateRandomMatrix(4, 4, 1, 100);
        System.out.println("Original Matrix:");
        printMatrix(matrix);

        sortMatrixRows(matrix);
        System.out.println("Matrix with Sorted Rows:");
        printMatrix(matrix);

        int minDifference = findMinDifference(matrix);
        System.out.println("Minimum Difference: " + minDifference);
    }

    public static int[][] generateRandomMatrix(int rows, int cols, int minVal, int maxVal) {
        Random random = new Random();
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(maxVal - minVal + 1) + minVal;
            }
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void sortMatrixRows(int[][] matrix) {
        for (int[] row : matrix) {
            Arrays.sort(row);
        }
    }

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            }

            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Element not found
    }

    public static int findMinDifference(int[][] matrix) {
        int minDifference = Integer.MAX_VALUE;

        for (int row = 0; row < matrix.length - 1; row++) {
            for (int i = 0; i < matrix[row].length; i++) {
                int currentElement = matrix[row][i];
                int currentDifference = Integer.MAX_VALUE;

                int offset = 0;
                int foundIndex = -1;

                // Alternating search for targets
                while (foundIndex == -1 && offset < minDifference) {
                    int target = currentElement + offset;
                    foundIndex = binarySearch(matrix[row + 1], target);
                    if (foundIndex != -1) {
                        currentDifference = Math.abs(matrix[row + 1][foundIndex] - currentElement);
                        break;
                    }

                    target = currentElement - offset;
                    foundIndex = binarySearch(matrix[row + 1], target);
                    if (foundIndex != -1) {
                        currentDifference = Math.abs(matrix[row + 1][foundIndex] - currentElement);
                        break;
                    }

                    offset++;
                }

                minDifference = Math.min(minDifference, currentDifference);
            }
        }

        return minDifference;
    }
}