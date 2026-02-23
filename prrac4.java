
// File: timeMethods.java

import java.util.*;
import java.text.DecimalFormat;

public class timeMethods {

    public static int N = 32654;

    static class Node {
        int key;
        String data;

        Node(int k, String d) {
            key = k;
            data = d;
        }
    }

    public static void main(String[] args) {

        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        int repetitions = 30;
        Random rand = new Random();

        Node[] array = new Node[N];
        for (int i = 0; i < N; i++) {
            array[i] = new Node(i + 1, "Data" + (i + 1));
        }

        double linearTime = 0, linearTime2 = 0;
        double binaryTime = 0, binaryTime2 = 0;

        for (int r = 0; r < repetitions; r++) {
            int key = rand.nextInt(N) + 1;
            long start = System.currentTimeMillis();
            linearSearch(array, key);
            long finish = System.currentTimeMillis();
            double time = (finish - start);
            linearTime += time;
            linearTime2 += time * time;
        }

        for (int r = 0; r < repetitions; r++) {
            int key = rand.nextInt(N) + 1;
            long start = System.currentTimeMillis();
            binarySearch(array, key);
            long finish = System.currentTimeMillis();
            double time = (finish - start);
            binaryTime += time;
            binaryTime2 += time * time;
        }

        double linearAvg = linearTime / repetitions;
        double linearStd =
                Math.sqrt(linearTime2 - repetitions * linearAvg * linearAvg)
                        / (repetitions - 1);

        double binaryAvg = binaryTime / repetitions;
        double binaryStd =
                Math.sqrt(binaryTime2 - repetitions * binaryAvg * binaryAvg)
                        / (repetitions - 1);

        System.out.println("\n\nStatistics");
        System.out.println("________________________________________________");

        System.out.println("Linear Search Average = "
                + fiveD.format(linearAvg / 1000) + " s ± "
                + fourD.format(linearStd) + " ms");

        System.out.println("Binary Search Average = "
                + fiveD.format(binaryAvg / 1000) + " s ± "
                + fourD.format(binaryStd) + " ms");

        System.out.println("________________________________________________");
    }

    static int linearSearch(Node[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].key == key)
                return i;
        }
        return -1;
    }

    static int binarySearch(Node[] arr, int key) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].key == key)
                return mid;
            else if (arr[mid].key < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}
