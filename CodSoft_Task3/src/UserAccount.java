import java.util.Random;

public class UserAccount {

    String accountType = "Current";
    String accountNumber;


    public String getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    private float balance = 0;

    public UserAccount()
    {
        accountNumber = generateAccountNumber();
    }

    public void setBalance(float balance){
        this.balance = balance;
    }

    public float getBalance(){
        return balance;
    }

    public void withdraw(float amount){
        balance -= amount;
    }
    public void deposit(float amount){
        balance += Math.abs(amount);
    }

    public String generateAccountNumber()
    {
        String accNum = "";
        Random rand = new Random();
        int i = rand.nextInt(900000000,999999999);
        int r = rand.nextInt(0,9);
        accNum = String.format("%d%d",i,r);
        return accNum;
    }

    @Override
    public String toString(){
        return String.format("R %.2f",balance);
    }
}
