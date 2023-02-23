import java.util.Random;
import java.util.concurrent.Semaphore;

public class Problem2 {

    public static int numberOfGuest = 8;
    public static int maxTimeToLookAtVase = 1000; // milliseconds
    //NOTE: Sets the fairness flag to true, so it implemements a queue for threads trying to access it
    public static Semaphore showRoomSemaphore = new Semaphore(1, true); 
    public static boolean endSimulation = false;
    public static long simulationTimeInMilliSeconds = 5000;

    
    static class Guest extends Thread {
        public int threadID;
        public Random random = new Random();

        public Guest(int threadID) {
            this.threadID = threadID;
        }

        public void baskInTheGloryOfTheVase() {
            int timeSpentLookingAtVase = random.nextInt(maxTimeToLookAtVase);
            // System.out.printf("\nGuest %d is admiring the vase%n", threadID);
            try {
                Thread.sleep(timeSpentLookingAtVase);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void doSomthingRandom() {
            Random rand = new Random(); 
    
            int timeSpentGoofingAround = rand.nextInt(3) * 1000;
    
            try{
                Thread.sleep(timeSpentGoofingAround);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            
            while(endSimulation == false) {
                doSomthingRandom();
                try {
                    System.out.printf("\nGuest %d entered the queue --->%n", threadID);
                    showRoomSemaphore.acquire();
                    System.out.printf("\nGuest %d entered the showroom%n", threadID);
                    baskInTheGloryOfTheVase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    showRoomSemaphore.release();
                    System.out.printf("\nGuest %d is leaving the showroom <---%n", threadID);
                }
            }

        }
    }


    public static void main(String[] args) {
        Guest guests[] = new Guest[numberOfGuest];

        for (int i = 0; i < numberOfGuest; i++) {
            guests[i] = new Guest(i);
        }

        for (int i = 0; i < numberOfGuest; i++) {
            guests[i].start();
        }

        try {
            Thread.sleep(simulationTimeInMilliSeconds);
            endSimulation = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < numberOfGuest; i++) {
            try {
                guests[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
