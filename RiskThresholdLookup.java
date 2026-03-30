import java.util.*;

public class RiskThresholdLookup {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    public static int linearSearch(int[] arr, int target) {
        linearComparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        binaryComparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int floorValue(int[] arr, int target) {
        int idx = lowerBound(arr, target);
        if (idx == 0) return -1;
        if (idx < arr.length && arr[idx] == target) return arr[idx];
        return arr[idx - 1];
    }

    public static int ceilingValue(int[] arr, int target) {
        int idx = lowerBound(arr, target);
        if (idx == arr.length) return -1;
        return arr[idx];
    }

    public static int insertionPoint(int[] arr, int target) {
        return lowerBound(arr, target);
    }

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};

        int target = 30;

        int linearResult = linearSearch(risks, target);
        System.out.println("Linear Search result: " + linearResult +
                " (" + linearComparisons + " comparisons)");

        int floor = floorValue(risks, target);
        int ceiling = ceilingValue(risks, target);
        int insertPos = insertionPoint(risks, target);

        System.out.println("Binary Floor(" + target + "): " + floor);
        System.out.println("Binary Ceiling(" + target + "): " + ceiling);
        System.out.println("Insertion Position: " + insertPos +
                " (" + binaryComparisons + " comparisons)");
    }
}