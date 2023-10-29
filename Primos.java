import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Primos {
    public List<Long> solve(long n) throws InterruptedException {
        List<Long> primes = Collections.synchronizedList(
                new ArrayList<>(
                        List.of(2L)
                )
        );

        for (long i = 3; i < n; i += 2) {
            long finalI = i;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    boolean primo = true;
                    for (long j = 2; j < finalI; j++) {
                        if(finalI % j == 0) {
                            primo = false;
                            break;
                        }
                    }
                    if(primo)
                        primes.add(finalI);
                }
            };

            thread.start();
            thread.join();
        }

        return primes;
    }
}
