
class Subscription {
    
    private static int numOfSubscriptions = 0;
    private int subscriptionID;
    private double amount;
    private String status;
    private String frequency;
    private String initialDate;

    // Default Constructor
    Subscription() {
        this.subscriptionID = numOfSubscriptions++;
        this.amount = 0;
    }

    // Parameterized Constructor
    Subscription( double amount, String status, String frequency, String initialDate) {
        this.subscriptionID = numOfSubscriptions++;
        this.amount = amount;
        this.status = status;
        this.frequency = frequency;
        this.initialDate = initialDate;
    }

    // Accessors
    public int getSubscriptionID() { return subscriptionID; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public String getFrequency() { return frequency; }
    public String getInitialDate() { return initialDate; }

    // Modifiers
    public void setAmount(double amount) { this.amount = amount; }
    public void setStatus(String status) { this.status = status; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setInitialDate(String initialDate) { this.initialDate = initialDate; }


    // Use case: Cancel subscription
    // Cancels subscription
    public boolean cancelSubscription() {
        return false;
    }
}
