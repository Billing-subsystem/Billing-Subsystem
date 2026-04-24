package Model;

public class SubscriptionPurchase extends Purchase {

    private int subscriptionID;

    // Default Constructor
    SubscriptionPurchase() {
        super(); // calls Model.Purchase default constructor
    }

    // Parameterized Constructor
    SubscriptionPurchase(String description, String date, double amount, int subscriptionID) {
        super(description, date, amount);
        this.subscriptionID = subscriptionID;
    }

    // Accessor
    public int getSubscriptionID() {
        return subscriptionID;
    }

    // Modifier
    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

}
