import java.util.UUID;

public class TransactionID {

    //returns a String with a unique ID along with the time of the transaction
    public static synchronized String getTransactionInfo(String information) {
        String time = Long.toString(System.currentTimeMillis());
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID + "-" + time + "-" + information;
    }

    public static synchronized String getTransactionAccount() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }


}
