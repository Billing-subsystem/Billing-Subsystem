package Model;

public class CreditCard {
    //Instance Variables
    private String creditCardNumber;
    private String expDate;
    private String cvv;

    public CreditCard (String creditCardNubmer, String expDate, String cvv) {
        this.creditCardNumber = creditCardNubmer;
        this.expDate = expDate;
        this.cvv = cvv;
    }

    //Accessor Methods
    public String getCreditCardNumber(){return creditCardNumber;}
    public String getExpDate(){return expDate;}
    public String getCVV(){return cvv;}

    //Modifier Methods
    public void setCreditCardNumber(String newCreditCardNumber){this.creditCardNumber = newCreditCardNumber;}
    public void setExpDate(String newExpDate){this.expDate = newExpDate;}
    public void setCVV(String newCVV){this.cvv = newCVV;}

}
