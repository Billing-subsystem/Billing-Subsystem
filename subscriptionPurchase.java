class SubscriptionPurchase {
    private int subscriptionID;

    // Default Constructor
    SubscriptionPurchase() {}

    // Parameterized Constructor
    SubscriptionPurchase(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    // Accessor
    public int getSubscriptionID() { return subscriptionID; }

    // Modifier
    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }
}
