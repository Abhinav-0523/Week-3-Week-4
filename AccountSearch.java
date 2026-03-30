import java.util.*;

public class AccountSearch {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    public static int linearFirst(String[] arr, String target) {
        linearComparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) return i;
        }
        return -1;
    }

    public static int linearLast(String[] arr, String target) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) index = i;
        }
        return index;
    }

    public static int binaryFirst(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;
        binaryComparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                result = mid;
                high = mid - 1;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int binaryLast(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            binaryComparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                result = mid;
                low = mid + 1;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int countOccurrences(String[] arr, String target) {
        int first = binaryFirst(arr, target);
        int last = binaryLast(arr, target);
        if (first == -1) return 0;
        return last - first + 1;
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        int firstLinear = linearFirst(logs, "accB");
        int lastLinear = linearLast(logs, "accB");

        System.out.println("Linear First accB: index " + firstLinear +
                " (" + linearComparisons + " comparisons)");
        System.out.println("Linear Last accB: index " + lastLinear);

        Arrays.sort(logs);

        System.out.println("\nSorted Logs: " + Arrays.toString(logs));

        int firstBinary = binaryFirst(logs, "accB");
        int lastBinary = binaryLast(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("Binary First accB: index " + firstBinary +
                " (" + binaryComparisons + " comparisons)");
        System.out.println("Binary Last accB: index " + lastBinary);
        System.out.println("Count of accB: " + count);
    }
}