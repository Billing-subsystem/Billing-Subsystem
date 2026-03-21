class Receipt {
    private static int numOfReceipts = 0;
    private int receiptID;
    private double paymentAmount;
    private String paymentDate;
    private String paymentTime;

    // Default Constructor
    Receipt() {
        this.receiptID = numOfReceipts++;
        this.paymentAmount = 0;
    }

    // Parameterized Constructor
    Receipt(double paymentAmount, String paymentDate, String paymentTime) {
        this.receiptID = numOfReceipts++;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
    }

    // Accessors
    public int getReceiptID() { return receiptID; }
    public double getPaymentAmount() { return paymentAmount; }
    public String getPaymentDate() { return paymentDate; }
    public String getPaymentTime() { return paymentTime; }

    // Modifiers
    public void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
    public void setPaymentTime(String paymentTime) { this.paymentTime = paymentTime; }
}
