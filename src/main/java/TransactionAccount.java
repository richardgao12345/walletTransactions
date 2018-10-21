import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

public class TransactionAccount {
    private BigDecimal accountMoney = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_DOWN);
    private List<String> history = Collections.synchronizedList(new ArrayList<String>());
    private final Object lock = new Object();


    /**
     * Constructor creates a new transaction account with an initial money of value 0
     */
    public TransactionAccount() {
    }

    /**
     * This method gives a list of recent transactions (based on what you specify) from the transaction account
     * @param numHistory is the number of most recent transactions you want to see
     * @return ArrayList<String> contains the transactions. It is empty upon failure
     */
    public ArrayList<String> getHistory(int numHistory) {
        synchronized(history) {
            ArrayList<String> returnList = new ArrayList<String>();
            if(history.size() < numHistory || numHistory <= 0) {
                return returnList;
            }
            Iterator it = history.listIterator(history.size() - numHistory);
            while(it.hasNext()) {
                returnList.add((String)it.next());
            }
            return returnList;

        }
    }
    /**
     * This method withdraws money from the transaction account
     * @param amount is the amount you want to take away
     * @return int, -1 on failure and 0 if success
     */
    //Returns a -1 if failure, positive number if possible
    public int withdraw(double amount) {
        synchronized(lock) {
            if(accountMoney.doubleValue() - amount < 0.00) {
                return -1;
            }
            accountMoney = accountMoney.subtract(new BigDecimal(amount)).setScale(2, RoundingMode.HALF_DOWN);
            String message = "Withdrawal-" + Double.toString(amount);
            history.add(TransactionID.getTransactionInfo(message));
            return 0;
        }
    }

    /**
     * This method deposits money into the transaction account
     * @param amount is the amount you want to deposit
     * @return void
     */
    public void deposit(double amount) {
        synchronized(lock) {
            accountMoney = accountMoney.add(new BigDecimal(amount)).setScale(2, RoundingMode.HALF_DOWN);
            String message = "Deposit-" + Double.toString(amount);
            history.add(TransactionID.getTransactionInfo(message));
        }
    }

    /**
     * This method adds a history to the account
     * @param message is the amount you want to deposit
     * @return void
     */
    public void addHistory(String message) {
        synchronized(history) {
            history.add(message);
        }
    }

    /**
     * This method adds a history to the account
     * @return double
     */
    public double getAmount() {
       return accountMoney.doubleValue();
    }


    public int transfer(TransactionAccount account, double amount) {
        synchronized(lock) {
            if(accountMoney.doubleValue() - amount < 0.00) {
                return -1;
            }
            accountMoney = accountMoney.subtract(new BigDecimal(amount)).setScale(2, RoundingMode.HALF_DOWN);
            account.deposit(amount);
            String message = "Transfer" + Double.toString(amount);
            history.add(TransactionID.getTransactionInfo(message));

            return 0;
        }
    }

}
