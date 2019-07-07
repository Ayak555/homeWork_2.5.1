import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Passenger extends Thread {

    Semaphore semaphore;
    CountDownLatch latch;
    int passengerNumber;

    public Passenger(Semaphore semaphore, CountDownLatch latch, int passengerNumber) {
        this.semaphore = semaphore;
        this.latch = latch;
        this.passengerNumber = passengerNumber;
    }

    public void run() {
        try {
            semaphore.acquire();
            System.out.println("Пассажир " + passengerNumber + " занял свое место");
            sleep(1000);
            semaphore.release();
            latch.countDown();
        } catch (InterruptedException e) {
        }
    }

}
