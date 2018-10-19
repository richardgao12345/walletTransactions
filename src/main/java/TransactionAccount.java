public class TransactionAccount {
    private int currency;
    private int history;
    public TransactionAccount(int amount) {
        if (amount < 0) {
            currency = amount;
        }
    }

    //Returns a 1 if withdrawal was a success
    public int Withdrawal(int amount) {
        return 1;
    }

    public void Deposit(int amount) {

    }
}
