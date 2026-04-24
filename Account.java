
class Account{
    //instance variables
    private static int numOfAccounts = 0;
    private int accountID;
    private String username;
    private String password;
    private String email;
    private String address;
    private String creditCardNumber;
    private String expDate;
    private String cvv;
    private double balance;
    
    
    // Default Constructor
    Account(){
        //total number of accounts + 1 to ensure no repeat numbers
        //numOfAccounts is a global variable
        this.accountID = numOfAccounts++;
        this.balance = 0;
    }
    
    //Parameterized Constructor
    Account(String username, String password, String email, String address, 
            String creditCardNumber, String expDate, String cvv){
                
        this.accountID = numOfAccounts++;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.expDate = expDate;
        this.cvv = cvv;
        this.balance = 0;
    }
    
    //accessor methods
    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public String getEmail(){return email;}
    public String getAddress(){return address;}
    public String getCreditCardNumber(){return creditCardNumber;}
    public String getExpDate(){return expDate;}
    public String getCVV(){return cvv;}
    public double getBalance(){return balance;}
    
    //modifier methods
    public void setUsername(String newUsername){this.username = newUsername;}
    public void setPassword(String newPassword){this.password = newPassword;}
    public void setEmail(String newEmail){this.email = newEmail;}
    public void setAddress(String newAddress){this.address = newAddress;}
    public void setCreditCardNumber(String newCreditCardNumber){this.creditCardNumber = newCreditCardNumber;}
    public void setExpDate(String newExpDate){this.expDate = newExpDate;}
    public void setCVV(String newCVV){this.cvv = newCVV;}
    public void setBalance(double newBalance){this.balance = newBalance;}

    //Use Case: Log in
    //Logs in an existing user
    public boolean logIn(String email, String password) {
        return false;
    }

    //Use Case: Create Account
    //Creates the account for the user
    public static Account signUp(String username, String password, String email) {
        return null;
    }

    //Use case: Update Payment Method
    //Updates the payment method
    public boolean updatePaymentMethod(String creditCardNumber, String expDate, String cvv) {
        return false;
    }

    
}
