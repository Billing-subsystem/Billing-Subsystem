package Model;

public class Purchase {
    
    private static int numOfPurchases = 0;
    private int purchaseID;
    private String description;
    private String date;
    private double amount;
    

    // Default Constructor
    Purchase() {
        this.purchaseID = numOfPurchases++;
    }

    // Parameterized Constructor
    Purchase(String description, String date, double amount) {
        this.purchaseID = numOfPurchases++;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    // Accessors
    public int getPurchaseID() { return purchaseID; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public double getAmount() { return amount; }
    
    // Modifiers
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setAmount(double amount) { this.amount = amount; }

    //Use case: deny purchase
    //Denies the user's purchase 
    public boolean denyPurchase() {
        return false;
    }

    // Use case: Pay Bill
    // Makes payment using stored billing information
    public boolean payBill(String creditCardNumber, String expDate, String cvv) {
        return false;
    }
    
}
