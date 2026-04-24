package Model;

public class CreditCard {
    private int creditCardNumber;
    private String expDate;
    private String CVV;
    private long accountID;

    public CreditCard(int creditCardNumber, String expDate, String CVV, long accountID) {
        this.creditCardNumber = creditCardNumber;
        this.expDate = expDate;
        this.CVV = CVV;
        this.accountID = accountID;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", expDate='" + expDate + '\'' +
                ", CVV='" + CVV + '\'' +
                ", accountID='" + accountID + '\'' +
                '}';
    }
}
