class SubscriptionPurchase extends Purchase {

    private int subscriptionID;

    // Default Constructor
    SubscriptionPurchase() {
        super(); // calls Purchase default constructor
    }

    // Parameterized Constructor
    SubscriptionPurchase(String description, double amount, String date, int subscriptionID) {
        super(description, amount, date);
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
