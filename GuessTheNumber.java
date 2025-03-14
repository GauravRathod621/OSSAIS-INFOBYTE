import java.util.Random;
import java.util.Scanner;

// Game class
class Game {
    int systemInput;
    int userInput;
    int noOfGuesses = 0;

    // Generating random number in constructor
    Game() {
        Random random = new Random();
        this.systemInput = random.nextInt(100) + 1;
    }

    // Method to take user guesses
    public boolean takeUserInput() {
        if (noOfGuesses < 10) {
            System.out.print("Guess the number: ");
            this.userInput = GuessTheNumber.takeIntegerInput(100);
            noOfGuesses++;
            return false;
        } else {
            System.out.println("Number of attempts finished...Better luck next time!\n");
            return true;
        }
    }

    // Method to check user guess status
    public boolean isCorrectGuess() {
        if (systemInput == userInput) {
            System.out.println("Congratulations! You guessed the number " + systemInput +
                    " in " + noOfGuesses + " guesses.");

            // Score Calculation
            int score = 100 - (noOfGuesses - 1) * 10;
            System.out.println("Your score is " + score);
            System.out.println();
            return true;
        } else if (noOfGuesses < 10 && userInput > systemInput) {
            if (userInput - systemInput > 10) {
                System.out.println("Too High!");
            } else {
                System.out.println("A Little High!");
            }
        } else if (noOfGuesses < 10 && userInput < systemInput) {
            if (systemInput - userInput > 10) {
                System.out.println("Too Low!");
            } else {
                System.out.println("A Little Low!");
            }
        }
        return false;
    }
}

// Main class
public class GuessTheNumber {

    // Scanner object (moved outside the loop to prevent resource leaks)
    static Scanner sc = new Scanner(System.in);

    // Static method to take integer input with exception handling
    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while (!flag) {
            try {
                input = sc.nextInt();
                flag = true;

                // Checking the input range
                if (flag && (input > limit || input < 1)) {
                    System.out.println("Choose a number between 1 and " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only an integer value!");
                sc.next(); // Consume the invalid input
                flag = false;
            }
        }
        return input;
    }

    // Main method
    public static void main(String[] args) {
        System.out.println("1. Start the Game \n2. Exit");
        System.out.print("Enter your choice: ");
        int choice = takeIntegerInput(2);
        int nextRound = 1;
        int noOfRound = 0;

        if (choice == 1) {
            // Loop for multiple rounds
            while (nextRound == 1) {
                // Creating an object of the Game class
                Game game = new Game();
                boolean isMatched = false;
                boolean isLimitCross = false;
                System.out.println("\nRound " + ++noOfRound + " starts...");

                // Loop for guessing the number
                while (!isMatched && !isLimitCross) {
                    isLimitCross = game.takeUserInput();
                    isMatched = game.isCorrectGuess();
                }

                // Asking for next round
                System.out.println("1. Next Round \n2. Exit");
                System.out.print("Enter your choice: ");
                nextRound = takeIntegerInput(2);
            }
        }
        System.out.println("Thank you for playing! Goodbye.");
        System.exit(0);
    }
}
