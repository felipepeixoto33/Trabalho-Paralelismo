import java.util.Random;

public class Essentials {

    public static int[] generateRandomIntArray(int size, int min, int max) {
        int[] randomArray = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            randomArray[i] = random.nextInt(max - min + 1) + min;
        }

        return randomArray;
    }
}
