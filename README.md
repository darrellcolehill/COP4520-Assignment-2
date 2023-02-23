# COP4520-Assignment-2

## Problem 1: Minotaur’s Birthday Party
The Minotaur invited N guests to his birthday party. When the guests arrived, he made 
the following announcement. 
The guests may enter his labyrinth, one at a time and only when he invites them to do 
so. At the end of the labyrinth, the Minotaur placed a birthday cupcake on a plate. When 
a guest finds a way out of the labyrinth, he or she may decide to eat the birthday 
cupcake or leave it. If the cupcake is eaten by the previous guest, the next guest will find 
the cupcake plate empty and may request another cupcake by asking the Minotaur’s 
servants. When the servants bring a new cupcake the guest may decide to eat it or leave 
it on the plate. 
The Minotaur’s only request for each guest is to not talk to the other guests about her or 
his visit to the labyrinth after the game has started. The guests are allowed to come up 
with a strategy prior to the beginning of the game. There are many birthday cupcakes, so 
the Minotaur may pick the same guests multiple times and ask them to enter the 
labyrinth. Before the party is over, the Minotaur wants to know if all of his guests have 
had the chance to enter his labyrinth. To do so, the guests must announce that they have 
all visited the labyrinth at least once. 


Now the guests must come up with a strategy to let the Minotaur know that every guest 
entered the Minotaur’s labyrinth. It is known that there is already a birthday cupcake left 
at the labyrinth’s exit at the start of the game. How would the guests do this and not 
disappoint his generous and a bit temperamental host? 
Create a program to simulate the winning strategy (protocol) where each guest is 
represented by one running thread. In your program you can choose a concrete number 
for N or ask the user to specify N at the start.

### How to run the solution program

#### Compile
``` javac Problem1.java ```
#### Run
``` java Problem1 ```

### Correctness
The key to solving this problem is by selecting a representative/leader guest (thread) that keeps track of a counter variable, while imposing rules on other non-leader guests (threads) as to how they interact with the cupcake. It is assumed that it takes some (random) amount of time to solve the maze, that the Minotaur takes some (random) amount of time to select the next guest to enter the maze, and that only one guest can interact with the cupcake at once. 

Prior to starting the game the guests (threads) select one guest (thread) to be the leader and agree to only eat a cupcake once, and do the following when they reach the end of the maze. 

1) If they are not the leader, then they will check if a cupcake is present and do the following:

    a) If there is a cupcake present and they have not eaten a cupcake before, then they will eat the cupcake, decide to not add another cupcake to the plate, and then leave the maze. 


    b) If there is a cupcake present, but they have already eaten a cupcake, then they will directly leave the maze. 


    c) If there is not a cupcake present, then the guest will simply leave the maze and not interact with the cupcake at all. 


2) If they are the leader, then they will check if a cupcake is present and do the following:


    a) If there is a cupcake present, then they will do nothing and simply leave the maze. 


    b) If there is not a cupcake present, then they will keep track of the times that they have encountered an empty plate. If the number of times that they        have encountered an empty plate is less than or equal to the (number guest - 1), then the leader will announce that all people have visited the maze.
     Otherwise, they will simply continue to keep track of the times that they have encountered the empty plate, add another cupcake to the plate, and leave      the maze. 
     
This approach ensures correctness because it does not allow guests (threads) to communicate amongst eachother, while still providing a way for the leader to correctly state that each guest has visited the maze, by simply counting the times that a cupcake is not present when the leader enters the end of the maze.
  


### Efficiency

While this is the most efficient protocol, it will in many cases, results in guests entering the maze (and solving it) multiple times. The run time is greatly dependent on 1) how long it takes the minotaur to choose the next guest to enter the maze, and 2) how long it takes each guest to solve the maze. In an ideal case, each guest only enters once (with the leader entering last). However, with the random nature of the problem, and enphasis on correctness, efficiency is mostly in the hands of the guests ability to solve the maze and the order in which the guests are choosen to enter the maze.

### Experimental evaluation

Evaluation was conducted by varying the number of guest(s) and by analyzing the console output and final report generated at the end of the programs execution. As expected, the program stopped after each non-leader solved the maze, and each user typically solved the maze multiple times. 

Other factors examined where the time taken by the minotaur to select another guest, and the time taken for the guest to sovle the maze, howver, varying these values did not effect the expected output or produce any outliers. 


## Problem 2: Minotaur’s Crystal Vase
The Minotaur decided to show his favorite crystal vase to his guests in a dedicated 
showroom with a single door. He did not want many guests to gather around the vase 
and accidentally break it. For this reason, he would allow only one guest at a time into 
the showroom. He asked his guests to choose from one of three possible strategies for 
viewing the Minotaur’s favorite crystal vase: 
1) Any guest could stop by and check whether the showroom’s door is open at any time 
and try to enter the room. While this would allow the guests to roam around the castle 
and enjoy the party, this strategy may also cause large crowds of eager guests to gather 
around the door. A particular guest wanting to see the vase would also have no 
guarantee that she or he will be able to do so and when. 
2) The Minotaur’s second strategy allowed the guests to place a sign on the door 
indicating when the showroom is available. The sign would read “AVAILABLE” or 
“BUSY.” Every guest is responsible to set the sign to “BUSY” when entering the 
showroom and back to “AVAILABLE” upon exit. That way guests would not bother trying 
to go to the showroom if it is not available. 
3) The third strategy would allow the quests to line in a queue. Every guest exiting the 
room was responsible to notify the guest standing in front of the queue that the 
showroom is available. Guests were allowed to queue multiple times. 
Which of these three strategies should the guests choose? Please discuss the advantages 
and disadvantages. 


Implement the strategy/protocol of your choice where each guest is represented by 1 
running thread. You can choose a concrete number for the number of guests or ask the 
user to specify it at the start. 

### How to run the solution program

#### Compile
``` javac Problem2.java ```
#### Run
``` java Problem2 ```

### Correctness

### Efficiency

### Experimental evaluation
