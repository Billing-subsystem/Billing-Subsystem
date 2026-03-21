class Invoice {
    private static int numOfInvoices = 0;
    private int invoiceID;
    private double amount;

    // Default Constructor
    Invoice() {
        this.invoiceID = numOfInvoices++;
    }

    // Parameterized Constructor
    Invoice(double amount) {
        this.invoiceID = numOfInvoices++;
        this.amount = amount;
    }

    // Accessors
    public int getInvoiceID() { return invoiceID; }
    public double getAmount() { return amount; }

    // Modifiers
    public void setAmount(double amount) { this.amount = amount; }
}
