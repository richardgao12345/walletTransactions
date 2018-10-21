import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Exception;

public class Wallet {
    private ConcurrentHashMap<String, TransactionAccount> transactionAccounts = new ConcurrentHashMap<String, TransactionAccount>();

    public Wallet() {

    }

    private boolean invalidAmount(double amount) {
        return (amount > 0) ? true : false;
    }

    /**
     * This method creates a transaction account in the wallet
     * @return String, returns the ID of the transaction account you need to reference it with
     */
    public String createTransactionAccount() {
        String transactionAccount = TransactionID.getTransactionAccount();

        if (transactionAccounts.putIfAbsent(transactionAccount, new TransactionAccount()) == null) {
            return transactionAccount;
        }
        else {
            return null;
        }
    }

    /**
     * This method returns a list of all the transaction ID's in the wallet
     * @return ArrayList<String>, returns a list of all the transaction account ID's
     */
    public ArrayList<String> getAllAccounts() {
        synchronized (transactionAccounts) {
            ArrayList<String> accounts = new ArrayList<String>();
            Set<String> keys = transactionAccounts.keySet();
            for(String key: keys){
                accounts.add(key);
            }
            return accounts;
        }

    }

    /**
     * This method withdraws money from a transaction account
     * @param account is transaction account ID
     * @param amount is the amount to withdraw
     * @return int, returns 0 on success, throws an exception on a withdrawal issue or no account found
     */

    public int withdrawMoney(String account, double amount) throws InvalidAmount, NoAccount {
        if (amount < 0) {
            throw new InvalidAmount("You withdrew a negative amount");
        }
        synchronized (transactionAccounts) {
            if(transactionAccounts.containsKey(account)) {
                if(transactionAccounts.get(account).withdraw(amount) != 0) {
                    throw new InvalidAmount("Withdrawing issue");
                }
                return 0;
            }
            throw new NoAccount("No transaction account for given key");
        }
    }

    /**
     * This method deposits money into a transaction account
     * @param account is transaction account ID
     * @param amount is the amount to deposit
     * @return int, returns 0 on success, throws an exception on a deposit issue or no account found
     */
    public double depositMoney(String account, double amount) throws InvalidAmount, NoAccount {
        if (amount < 0) {
            throw new InvalidAmount("You deposited a negative amount");
        }
        synchronized (transactionAccounts) {
            if(transactionAccounts.containsKey(account)) {
                transactionAccounts.get(account).deposit(amount);
                return transactionAccounts.get(account).getAmount();
            }
            throw new NoAccount("No transaction account for given key");
        }
    }

    /**
     * This method deposits money into a transaction account
     * @param accountWithdraw is transaction account ID to withdraw from
     * @param accountDeposit is transaction account ID to deposit in
     * @param amount is how much you're withdrawing/depositing
     * @return int, returns 0 on success, throws an exception on a withdrawal amount issue or no account found
     */
    public int transferMoney(String accountWithdraw, String accountDeposit, double amount) throws InvalidAmount, NoAccount{
        if (amount < 0) {
            throw new InvalidAmount("You deposited a negative amount");
        }
        synchronized (transactionAccounts) {
            if(transactionAccounts.containsKey(accountWithdraw) && transactionAccounts.containsKey(accountDeposit)) {
                if(transactionAccounts.get(accountWithdraw).withdraw(amount) != 0) {
                    throw new InvalidAmount("Withdrawal issue");
                }
                transactionAccounts.get(accountDeposit).deposit(amount);
                return 0;
            }
            throw new NoAccount("No transaction account for given key");

        }
    }

    /**
     * This method deposits money into a transaction account
     * @param account is transaction account ID
     * @param recent is how many history entries you want to see
     * @return ArrayList<String>, returns a list of the history, null if key not found, history will be empty if account error
     */
    public ArrayList<String> getHistory(String account, int recent) throws InvalidAmount, NoAccount{
        if (recent < 0) {
            throw new InvalidAmount("You put in a negative number for recency");
        }
        synchronized (transactionAccounts) {
            if(transactionAccounts.containsKey(account)) {
                ArrayList<String> history = transactionAccounts.get(account).getHistory(recent);
                return history;
            }
            throw new NoAccount("No transaction account for given key");
        }
    }

    /**
     * This method gets the current amount of money in the transaction account
     * @param account is transaction account ID
     * @return double amount of money, throws exception if not found
     */
    public double getMoney(String account) throws NoAccount{

        synchronized (transactionAccounts) {
            if(transactionAccounts.containsKey(account)) {
                return transactionAccounts.get(account).getAmount();
            }
            throw new NoAccount("No transaction account for given key");

        }
    }
}
