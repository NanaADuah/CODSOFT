public class ATM {

    protected boolean isCardEntered = false;
    protected boolean pinEntered = false;
    private float overdraft;
    private float minAmount = 10;

    private final UserAccount user;
    public ATM()
    {
        user = new UserAccount();
    }

    public boolean isPinEntered(){
        return pinEntered;
    }

    public String getUserAccountType(){
        return user.getAccountType();
    }

    public String getUserAccountNumber(){
        return user.getAccountNumber();
    }

    protected float getUserBalance(){
        return user.getBalance();
    }

    public boolean isCardEntered(){
        return isCardEntered;
    }

    public void setPinEntered(boolean value){
        this.pinEntered = value;
    }

    public ATM(UserAccount user)
    {
        this.user = user;
    }
    public boolean CanWithdraw(double amount){
       return (user.getBalance() - amount >= minAmount) && isCardEntered;
    }

    /**
    @implNote Use with a try-catch
     **/
    public String withdraw(float amount){
        String output = "Cannot withdraw. Try again later.";
        float value = user.getBalance() - amount;
        if(CanWithdraw(amount))
        {
            if (amount > minAmount){
                user.withdraw(amount);
                output = String.format("Success. Amount withdrawn: R%.2f.", amount);
            }
            else
                output = "Minimum to withdraw is R10,00.";
        }
        else
        if(value <= minAmount){
            output = "Insufficient balance.";
        }

        return output;
    }

    public void enterCard(){
        isCardEntered = true;
    }

    public void removeCard(){
        isCardEntered = false;
        pinEntered = false;
    }

    public boolean CanDeposit(float amount){
        if(user.getAccountType().equals("Current"))
        {
           if (amount % 10 == 0 ) return amount <= 50000;        //cannot deposit values ending in 1 -> 9 (coins etc)
        }
        return false;
    }

    /**
     @implNote Use with a try-catch
     **/
    public String deposit(float amount){
        String output = "Cannot deposit this amount.";
        if(CanDeposit(amount))
        {
            user.deposit(amount);
            output = String.format("Success. Your new balance is R%.2f.", user.getBalance());
        }

        return output;
    }
}
