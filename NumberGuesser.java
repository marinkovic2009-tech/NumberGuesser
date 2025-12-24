import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    private static int targetNumber;
    private static int attempts;
    private static int highscore = Integer.MAX_VALUE;
    private static boolean gameRunning;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        WELCOME TO THE GUESSING GAME    ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        while (playAgain) {
            playGame(scanner);

            System.out.print("\nDo you want to play again? (yes/no): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            playAgain = answer.equals("yes") || answer.equals("y");
        }

        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.println("│     Thanks for playing! Goodbye!       │");
        if (highscore != Integer.MAX_VALUE) {
            System.out.println("│  Best Score: " + highscore + " attempts            │");
        }
        System.out.println("└────────────────────────────────────────┘");
        scanner.close();
    }

    public static void playGame(Scanner scanner) {
        targetNumber = generateNumber();
        attempts = 0;
        gameRunning = true;

        System.out.println("I have chosen a number between 1 and 100.");
        System.out.println("Try to guess it!\n");

        while (gameRunning) {
            System.out.print("Your guess: ");

            try {
                if (!scanner.hasNextInt()) {
                    System.out.println("❌ Please enter a whole number!");
                    scanner.nextLine();
                    continue;
                }

                int guess = scanner.nextInt();
                scanner.nextLine();

                if (guess < 1 || guess > 100) {
                    System.out.println("❌ The number must be between 1 and 100!");
                    continue;
                }

                checkGuess(guess, targetNumber);
            } catch (Exception e) {
                System.out.println("❌ An error occurred. Try again!");
                scanner.nextLine();
            }
        }
    }

    public static int generateNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public static void checkGuess(int guess, int target) {
        attempts++;

        if (guess < target) {
            System.out.println("↑ The number is HIGHER!");
        } else if (guess > target) {
            System.out.println("↓ The number is LOWER!");
        } else {
            System.out.println("\n🎉 CORRECT! You guessed the number " + target + "!");
            System.out.println("📊 You needed " + attempts + " attempts.\n");

            if (attempts < highscore) {
                highscore = attempts;
                System.out.println("🏆 NEW HIGHSCORE! 🏆\n");
            }

            gameRunning = false;
        }
    }
}