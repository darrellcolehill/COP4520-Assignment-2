import java.util.Random;


public class Problem1 {

    public static boolean cupcakeIsPresent = true;
    public static int numberOfGuest = 8;
    public static boolean allGuesthaveVisited = false;

    public static synchronized void updateCupcakeStatus() {
        cupcakeIsPresent = !cupcakeIsPresent;
    }

    // Ensures that the next guest will be choosen between 0 and .3 seconds 
    public static void walkThroughTheMaze() {
        Random rand = new Random(); 

        int timeToWalkThroughTheMaze = rand.nextInt(4) * 100;

        try{
            Thread.sleep(timeToWalkThroughTheMaze);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Guest extends Thread {
        public boolean hasEatenCake = false;
        public boolean isLeader = false;
        public int visitCount = 0;
        public int threadID;
    
        public Guest(boolean isLeader, int threadID){
            this.isLeader = isLeader;
            this.threadID = threadID;
        }
    
        public void run() {

            // Spend some time walking through the maze. 
            walkThroughTheMaze(); 

            // Tracks the number of times the specific guest has entered the maze.
            this.visitCount += 1;

            if(isLeader == true){
                System.out.println("Leader has entered the end of the maze. visit count =  " + visitCount + " cupcake state = " + cupcakeIsPresent);
                if(visitCount >= numberOfGuest - 1) {
                    // Announces that all guest have reached the end of the maze here
                    System.out.println("announcing that all guest have entered the maze... ");
                    allGuesthaveVisited = true;
                }
                else if(cupcakeIsPresent == false) {
                    System.out.println("Leader is placeing another cupcake on the plate.");
                    updateCupcakeStatus(); // sets another cupcake down on the plate.
                }
                // else, do nothing because the leader or another guest has been choosen another time. 
            }
            else
            {
                if(cupcakeIsPresent == true && hasEatenCake == false) {
                    System.out.println("Guest "+ threadID + " is eating the cupcake");
                    updateCupcakeStatus(); // eats the cupcake
                    hasEatenCake = true;
                }
                // otherwise, non-leader guest that have already eaten cake do nothing. 
            }
        }
    
    }


    public static int chooseNextGuest() {
        Random rand = new Random(); 

        // NOTE: assumed that it takes some amount of time for the host to choose a guest. 
        // Ensures that the next guest will be choosen between 0 and .5 seconds 
        int timeToSelectNextGuest = rand.nextInt(6) * 100;
        try{
            Thread.sleep(timeToSelectNextGuest);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Randomly selects a thread (that is not currently in the maze/running) and lets them enter the maze
        int random_guest = rand.nextInt(numberOfGuest);

        return random_guest;
    }

    public static void main(String[] args) {

        Guest guests[] = new Guest[numberOfGuest];
        guests[0] = new Guest(true, 0);
        for(int i = 1; i < numberOfGuest; i++) {
            guests[i] = new Guest(false, i);
        }

        // Loops until a guest announces that all guest have visited the maze. 
        while(!allGuesthaveVisited)
        {
            int random_guest = chooseNextGuest();

            if(guests[random_guest].isAlive() == false) {

                if(guests[random_guest].isLeader) {
                    System.out.print("\nSelecting leader " + random_guest + "\n"); // NOTE: these prints are to hopefully help the TA while grading
                } 
                else 
                {
                    System.out.print("\nSelecting non-leader " +  random_guest + ".\n"); // NOTE: these prints are to hopefully help the TA while grading
                }

                if(guests[random_guest].getState().equals(Thread.State.TERMINATED) == false) 
                {
                    guests[random_guest].start();
                }
                else
                {
                    guests[random_guest].run();
                }
            }
        }

        // Joins all guest/thread after completion. 
        for(int i = 0; i < numberOfGuest; i++) {
            try{
                guests[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nAll guest have visited the maze!\n");

        for(int i = 0; i < numberOfGuest; i++) {
            System.out.println("Guest " + i + " finished the maze " + guests[i].visitCount + " times");
        }
            

    }
}