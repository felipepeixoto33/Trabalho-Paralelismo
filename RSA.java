import java.math.BigInteger;
import java.util.Random;

public class RSA {
    public static void main(String[] args) throws InterruptedException {
        RSA r = new RSA();
        r.solve();
    }

    public String solve() throws InterruptedException {
        double initTime = System.currentTimeMillis();

        final long[] numbers = new long[2];

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            Thread thread = new Thread() {
              @Override
              public void run() {
                  long primo = getPrimo();
                  numbers[finalI] = primo;
              }
            };

            thread.start();
            thread.join();
        }

        BigInteger num = new BigInteger(String.valueOf(numbers[0]));
        BigInteger num2 = new BigInteger(String.valueOf(numbers[1]));

        double endTime = System.currentTimeMillis();

        String result = num.multiply(num2).toString();

        System.out.println("Primo 1 = " + numbers[0]);
        System.out.println("Primo 2 = " + numbers[1]);

        System.out.println("Key = " + result);
        System.out.println("Tempo total: " + (endTime - initTime) / 1000 + " segundos");

        return result;
    }

    private long getPrimo() {
        boolean primo = false;
        long number = 0;
        long min = (long) Math.pow(10, 5);
        long max = (long) Math.pow(10, 9);


        while(!primo) {
            boolean div = false;

            number = min + (long) (Math.random() * ((max - min) + 1));
            for (long i = 2; i < number; i = i + 1) {
                if(number % i == 0) {
                    div = true;
                    break;
                }
            }
            if(!div) {
                primo = true;
            }

        }

        return number;
    }
}
