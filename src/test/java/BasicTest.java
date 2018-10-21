import org.junit.Assert;

import java.util.ArrayList;


public class BasicTest {
    public static void main(String[] args) {
        Wallet newWallet = new Wallet();
        String transactionAccount = newWallet.createTransactionAccount();
        String transactionAccount1 = newWallet.createTransactionAccount();
        String transactionAccount2 = newWallet.createTransactionAccount();
        String transactionAccount3 = newWallet.createTransactionAccount();

        try {
            newWallet.depositMoney(transactionAccount, 100.00);
            newWallet.depositMoney(transactionAccount1,  200.00);
            newWallet.depositMoney(transactionAccount2, 300.00);
            newWallet.depositMoney(transactionAccount3, 400.00);
            for(int i = 0; i < 20; i++) {
                newWallet.depositMoney(transactionAccount, i);
            }
            System.out.print(newWallet.getMoney(transactionAccount));
            for(int i = 0; i < 20; i++) {
                newWallet.withdrawMoney(transactionAccount, i);
            }
            System.out.print(newWallet.getMoney(transactionAccount));


        }
        catch(Exception e) {
            System.out.print(e.getMessage());
        }


    }
}
