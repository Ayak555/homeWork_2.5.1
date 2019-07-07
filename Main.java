import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {


    public static void main(String[] args) {
        final int SEATS_COUNT = 18;

        int gasolineQuantity = (int) (Math.random() * 100);
        int engine = (int) (Math.random() * 2);

        try {
            Semaphore semaphore = new Semaphore(2);
            CountDownLatch latch = new CountDownLatch(SEATS_COUNT + 2);
            if (gasolineQuantity > 20 && engine == 1) {
                for (int i = 1; i <= SEATS_COUNT; i++) {
                    new Passenger(semaphore, latch, i).start();
                }

                while (latch.getCount() > 4) {
                    Thread.sleep(1000);
                }
                Thread.sleep(1000);
                System.out.println("Объем бензина в бензобаке: " + gasolineQuantity + " литров");
                latch.countDown();
                Thread.sleep(1000);
                System.out.println("Двигатель исправен");
                latch.countDown();
                Thread.sleep(1000);
                System.out.println("Все пассажиры заняли свои места");
                latch.countDown();
                Thread.sleep(1000);
                System.out.println("Поехали!!!");
                latch.countDown();
            } else if (gasolineQuantity < 20) {
                System.out.println("Необходимо заехать на АЗС, так как объем бензина недостаточно, и составлчет "
                        + gasolineQuantity + " литров");
            } else {
                System.out.println("Двигатель неиспрвен");
            }
        } catch (InterruptedException ie) {
        }
    }
}
