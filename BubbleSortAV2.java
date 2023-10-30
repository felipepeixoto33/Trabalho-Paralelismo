import java.util.Arrays;
import java.util.Random;

public class BubbleSortAV2 {
    public static void main(String[] args) {
        int[] arr = Essentials.generateRandomIntArray(1000, 0, 1000);
        int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);

        int numThreads = Thread.activeCount();


        BubbleSortAV2.bubbleSortParalelo(arr, numThreads);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSortParalelo(int[] arr, int numeroThreads) {
        double initTime = System.currentTimeMillis();

        int subArrayLength = arr.length / numeroThreads;
        Thread[] threads = new Thread[numeroThreads];

        for (int i = 0; i < numeroThreads; i++) {

            final int inicio = i * subArrayLength;
            final int fim = (i + 1) == numeroThreads ? arr.length : (i + 1) * subArrayLength;

            final int[] subArray = Arrays.copyOfRange(arr, inicio, fim);

            threads[i] = new Thread(() -> {
                sortSubArray(subArray);
                System.arraycopy(subArray, 0, arr, inicio, subArray.length);
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sortSubArray(arr);
        double endTime = System.currentTimeMillis();
        System.out.println("Parallel Time: " + (endTime-initTime)/1000);
    }

    public static void sortSubArray(int[] subArray) {
        int n = subArray.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (subArray[j] > subArray[j + 1]) {
                    // Swap subArray[j] and subArray[j + 1]
                    int temp = subArray[j];
                    subArray[j] = subArray[j + 1];
                    subArray[j + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        double initTime = System.currentTimeMillis();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        double endTime = System.currentTimeMillis();
        System.out.println("Serial Time: " + (endTime-initTime)/1000);
    }
}

class Essentials {

    public static int[] generateRandomIntArray(int size, int min, int max) {
        int[] randomArray = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            randomArray[i] = random.nextInt(max - min + 1) + min;
        }

        return randomArray;
    }
}
