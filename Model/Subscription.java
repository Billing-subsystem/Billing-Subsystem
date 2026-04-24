package Model;

public class Subscription {
    private enum Status {ACTIVE, INACTIVE}

    private long subscriptionID;
    private int subscriptionType;
    private String initialDate;
    private long accountID;

    public Subscription(long subscriptionID, int subscriptionType, String initialDate, long accountID) {
        this.subscriptionID = subscriptionID;
        this.subscriptionType = subscriptionType;
        this.initialDate = initialDate;
        this.accountID = accountID;
    }

    public long getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(long subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public int getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(int subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionID=" + subscriptionID +
                ", subscriptionType=" + subscriptionType +
                ", initialDate='" + initialDate + '\'' +
                ", accountID=" + accountID +
                '}';
    }
}
