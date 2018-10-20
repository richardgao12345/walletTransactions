import java.util.*;

protected class TransactionAccount {
    private float currency;
    private List<String> history = Collections.synchronizedList(new ArrayList<String>());
    private final Object lock = new Object();


    public TransactionAccount(int amount) {
        if (amount < 0) {
            currency = amount;
        }
    }

    public List<String> iterateHistory(int numHistory) {
        synchronized(history) {


        }
    }

    //Returns a -1 if failure, positive number if possible
    public int Withdrawal(float amount) {
        synchronized(lock) {
        
        }
        return -1;
    }


    public void Deposit(float amount) {


    }



}
