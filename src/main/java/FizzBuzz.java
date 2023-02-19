import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {
    private final AtomicInteger i = new AtomicInteger(1);
    private final int n;

    private ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void printNumbersToConsole() {

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(this::fizz);
        executorService.submit(this::buzz);
        executorService.submit(this::fizzBuzz);
        executorService.submit(this::number);
        executorService.shutdown();

    }

    // Stream A
    public synchronized void fizz() {
        while (i.get() <= n) {
            if (i.get() % 3 == 0 && i.get() % 5 != 0) {
                queue.add("fizz");
                i.incrementAndGet();
                notifyAll();
            } else {

                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Stream B
    public synchronized void buzz() {
        while (i.get() <= n) {
            if (i.get() % 3 != 0 && i.get() % 5 == 0) {
                queue.add("buzz");
                i.incrementAndGet();
                notifyAll();
            } else {

                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Stream C
    public synchronized void fizzBuzz() {
        while (i.get() <= n) {
            if (i.get() % 3 == 0 && i.get() % 5 == 0) {
                queue.add("fizzbuzz");
                i.incrementAndGet();
                notifyAll();
            } else {

                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Stream D
    public synchronized void number() {
        while (i.get() <= n) {
            if (i.get() % 3 != 0 && i.get() % 5 != 0) {
                queue.add(i + "");
                i.incrementAndGet();
                notifyAll();
            } else {

                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
        }
    }

}

