import java.math.BigInteger;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Primos p = new Primos();
        System.out.println(p.solve(100000));
    }
}