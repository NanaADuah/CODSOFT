import java.util.Objects;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args)
    {
        GameClass game = new GameClass();
        Scanner scanner = new Scanner(System.in);
        String answer = "N";
        System.out.println("The Number Game");
        System.out.println("===============\n");

        do {
            boolean isGuessed = false;
            game.ResetAttempts();
            int randomNum = game.GetRandomValue();
            System.out.printf("%s", "Enter your guessed value: ");
            int guess = scanner.nextInt();
            game.AnotherAttempt();

            while (guess != randomNum && game.getCurrentAttempt() >= 0) { //change hardcoded value later
                String statement = "";
                if (guess > randomNum)
                    statement = "your guess was too high";
                else
                    statement = "your guess was too low";

                System.out.printf("Attempt #%d\n", Math.abs(game.getCurrentAttempt()-5));
                System.out.printf("Oops, almost there %s.\n", statement);
                System.out.printf("%s", "Try Again. Enter your guessed value: ");
                guess = scanner.nextInt();
                game.AnotherAttempt();
            }

            if(guess == randomNum) {
                isGuessed = true;
                game.IncrementScore();
            }

            game.DisplayScore();

            if(isGuessed){
                System.out.println("\nCongratulations, you correctly guessed the number!");
            }else {
                System.out.println("\nOh no, seems like you're out of attempts :(");
            }

            System.out.print("\nDo you want to play another match? (Y/N): ");
            answer = scanner.next();
        }
        while(MustProceed(answer));

        if(answer.equals("N"))
        {
            System.out.printf("\nThanks for playing :)\nYour final score is %d\nGG", game.getCurrentScore());
        }

    }

    public static boolean MustProceed(String answer){
        if(Objects.equals(answer, ""))
            return false;

        return Objects.equals(answer, "Y") || Objects.equals(answer, "Yes");
    }
}
