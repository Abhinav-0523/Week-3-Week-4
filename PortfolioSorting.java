import java.util.*;
class Asset {
    String name;
    double returnRate;
    double volatility;
    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ": return=" + returnRate + "%, vol=" + volatility;
    }
}
public class PortfolioSorting {
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    public static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionMedianOf3(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    public static int partitionMedianOf3(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;
        if (compare(arr[low], arr[mid]) > 0) swap(arr, low, mid);
        if (compare(arr[low], arr[high]) > 0) swap(arr, low, high);
        if (compare(arr[mid], arr[high]) > 0) swap(arr, mid, high);

        swap(arr, mid, high);
        return partition(arr, low, high);
    }

    public static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Comparator: DESC returnRate, ASC volatility
    public static int compare(Asset a1, Asset a2) {
        if (a1.returnRate != a2.returnRate) {
            return Double.compare(a2.returnRate, a1.returnRate);
        }
        return Double.compare(a1.volatility, a2.volatility);
    }

    public static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(Asset[] arr) {
        for (Asset a : arr) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {

        Asset[] assets = {
            new Asset("AAPL", 12, 5),
            new Asset("TSLA", 8, 9),
            new Asset("GOOG", 15, 4)
        };

        Asset[] mergeArr = Arrays.copyOf(assets, assets.length);
        mergeSort(mergeArr, 0, mergeArr.length - 1);

        System.out.println("Merge Sort (Ascending returnRate):");
        printArray(mergeArr);

        Asset[] quickArr = Arrays.copyOf(assets, assets.length);
        quickSort(quickArr, 0, quickArr.length - 1);

        System.out.println("\nQuick Sort (DESC returnRate + ASC volatility):");
        printArray(quickArr);
    }
}