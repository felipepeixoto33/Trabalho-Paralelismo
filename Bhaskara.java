import java.util.Arrays;
import java.util.List;

public class Bhaskara {
    public static void main(String[] args) throws InterruptedException {
        Bhaskara b = new Bhaskara();
        b.parallel_solve(1, 4, 3);
    }

    public List<Thread> parallel_solve(int a, int b, int c) throws InterruptedException {
        double delta = Math.pow(b, 2) - 4*a*c;
        final double[] roots = new double[2];
        System.out.println("Delta = " + delta);

        if(delta < 0)
            throw new InterruptedException("Delta menor que zero!");

        Thread thread = new Thread() {
            @Override
            public void run() {
                double root = (-b + Math.sqrt(delta)) / 2*a;
                roots[0] = root;
                System.out.println("X1 = " + root);
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                double root = (-b - Math.sqrt(delta)) / 2*a;
                roots[1] = root;
                System.out.println("X2 = " + root);
            }
        };
        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        return Arrays.asList(thread, thread2);
    }
}
