import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ShowPassedTime {

    public static void main(String[] args) throws RuntimeException {

        long startTime = System.currentTimeMillis();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.scheduleAtFixedRate(
                () -> System.out.println("Від старту минуло: " + (System.currentTimeMillis() - startTime) + " мілісекунд"),
                1000,
                1000,
                TimeUnit.MILLISECONDS
        );

        while (true) {

            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Минуло 5 секунд");

        }

    }

}
