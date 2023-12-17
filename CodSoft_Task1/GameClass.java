import java.util.Random;

public class GameClass {

    private int CurrentScore;
    private String playerName = "User";
    private int CurrentAttempt = 0;

    public GameClass(){
        ResetAttempts();
        CurrentScore = 0;
    }

    public int GetRandomValue(){
        Random rand = new Random();
        return rand.nextInt(1,100);
    }

    public void ResetScore(){
        CurrentScore = 5;
    }

    public GameClass(String playerName){
        this.playerName = playerName;
    }
    public int getCurrentScore() {
        return CurrentScore;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void IncrementScore(){
        CurrentScore += 5;
    }

    public void IncrementScore(int add){
        CurrentScore += add;
    }
    public void AnotherAttempt(){
        CurrentAttempt--;
    }

    public void setCurrentScore(int currentScore) {
        CurrentScore = currentScore;
    }

    public int getCurrentAttempt() {
        return CurrentAttempt;
    }

    public void setCurrentAttempt(int currentAttempt) {
        CurrentAttempt = currentAttempt;
    }


    public void ResetAttempts() {
        CurrentAttempt = 5;
    }

    public void DisplayScore(){
        System.out.printf("Score: %d\n",getCurrentScore());

    }
}
