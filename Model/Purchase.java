package Model;

public class Purchase {
    long purchaseID;
    String date;
    long subscriptionID;

    public Purchase(long purchaseID, String date, long subscriptionID) {
        this.purchaseID = purchaseID;
        this.date = date;
        this.subscriptionID = subscriptionID;
    }

    public long getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(long purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(long subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseID=" + purchaseID +
                ", date='" + date + '\'' +
                ", subscriptionID=" + subscriptionID +
                '}';
    }
}
