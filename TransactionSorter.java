import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public int getTimeInMinutes() {
        String[] parts = timestamp.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public String toString() {
        return id + ": fee=" + fee + ", ts=" + timestamp;
    }
}

public class TransactionSorter {

    public static void bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        for (Transaction t : list) System.out.println(t);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    public static void insertionSortByFeeAndTime(List<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        for (Transaction t : list) System.out.println(t);
    }

    public static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return Integer.compare(t1.getTimeInMinutes(), t2.getTimeInMinutes());
    }

    public static void detectOutliers(List<Transaction> list) {
        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("None");
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        int size = transactions.size();

        List<Transaction> bubbleList = new ArrayList<>(transactions);
        List<Transaction> insertionList = new ArrayList<>(transactions);

        if (size <= 100) {
            bubbleSortByFee(bubbleList);
        }

        if (size > 100 && size <= 1000) {
            insertionSortByFeeAndTime(insertionList);
        } else {
            insertionSortByFeeAndTime(insertionList);
        }

        detectOutliers(transactions);
    }
}