class Refund {
    private int purchaseID;

    // Default Constructor
    Refund() {}

    // Parameterized Constructor
    Refund(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    // Accessor
    public int getPurchaseID() { return purchaseID; }

    // Modifier
    public void setPurchaseID(int purchaseID) { this.purchaseID = purchaseID; }

    //Use case: Process refund
    //Processes the refund
    public boolean processRefund(Account accountID, double amount) {
        return false;
    }
 
}
