import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + ": risk=" + riskScore + ", balance=" + accountBalance;
    }
}

public class ClientRiskRanking {

    public static void bubbleSortAscending(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("Bubble Sort (Ascending):");
        printArray(arr);
        System.out.println("Swaps: " + swaps);
    }

    public static void insertionSortDescending(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compareDesc(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.println("\nInsertion Sort (Descending risk + balance):");
        printArray(arr);
    }

    public static int compareDesc(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c2.riskScore, c1.riskScore);
        }
        return Double.compare(c2.accountBalance, c1.accountBalance);
    }

    public static void printTopClients(Client[] arr, int k) {
        System.out.println("\nTop " + k + " High Risk Clients:");
        for (int i = 0; i < Math.min(k, arr.length); i++) {
            System.out.println(arr[i]);
        }
    }

    public static void printArray(Client[] arr) {
        for (Client c : arr) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {

        Client[] clients = {
            new Client("clientC", 80, 5000),
            new Client("clientA", 20, 2000),
            new Client("clientB", 50, 3000)
        };

        Client[] bubbleArray = Arrays.copyOf(clients, clients.length);
        Client[] insertionArray = Arrays.copyOf(clients, clients.length);

        bubbleSortAscending(bubbleArray);
        insertionSortDescending(insertionArray);
        printTopClients(insertionArray, 10);
    }
}