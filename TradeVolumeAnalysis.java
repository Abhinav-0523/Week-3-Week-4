import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String toString() {
        return id + ":" + volume;
    }
}

public class TradeVolumeAnalysis {

    // Merge Sort (Ascending, Stable)
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }

    // Quick Sort (Descending)
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Merge two sorted arrays (Ascending)
    public static Trade[] mergeTwoSortedArrays(Trade[] a, Trade[] b) {
        int i = 0, j = 0, k = 0;
        Trade[] result = new Trade[a.length + b.length];

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length)
            result[k++] = a[i++];

        while (j < b.length)
            result[k++] = b[j++];

        return result;
    }

    // Total volume
    public static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) {
            sum += t.volume;
        }
        return sum;
    }

    public static void printArray(Trade[] arr) {
        for (Trade t : arr) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {

        Trade[] trades = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };

        Trade[] mergeArray = Arrays.copyOf(trades, trades.length);
        mergeSort(mergeArray, 0, mergeArray.length - 1);

        System.out.println("Merge Sort (Ascending):");
        printArray(mergeArray);

        Trade[] quickArray = Arrays.copyOf(trades, trades.length);
        quickSort(quickArray, 0, quickArray.length - 1);

        System.out.println("\nQuick Sort (Descending):");
        printArray(quickArray);

        Trade[] morning = {
            new Trade("t1", 100),
            new Trade("t2", 300)
        };

        Trade[] afternoon = {
            new Trade("t3", 200),
            new Trade("t4", 400)
        };

        Trade[] merged = mergeTwoSortedArrays(morning, afternoon);

        System.out.println("\nMerged Sorted Trades:");
        printArray(merged);

        System.out.println("\nTotal Volume: " + totalVolume(merged));
    }
}