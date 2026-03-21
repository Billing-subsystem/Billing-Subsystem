
class Account{
    //instance variables
    private int accountID;
    private string username;
    private string password;
    private string email;
    private string address;
    private string creditCardNumber;
    private string expDate;
    private string cvv;
    private double balance;
    
    // Default Constructor
    Account(){
        //total number of accounts + 1 to ensure no repeat numbers
        //numOfAccounts is a global variable
        this.accountID = numOfAccounts++;
        this.balance = 0;
    }
    
    //Parameterized Constructor
    Account(string username, string password, string email, string address, 
            string creditCardNumber, string expDate, string cvv){
                
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
    public string getUsername(){return username;}
    public string getPassword(){return password;}
    public string getEmail(){return email;}
    public string getAddress(){return address;}
    public string getCreditCardNumber(){return creditCardNumber;}
    public string getExpDate(){return expDate;}
    public string getCVV(){return cvv;}
    public string getBalance(){return balance;}
    
    //modifier methods
    public void setUsername(string newUsername){this.username = newUsername;}
    public void setPassword(string newPassword){this.password = newPassword;}
    public void setEmail(string newEmail){this.email = newEmail;}
    public void setAddress(string newAddress){this.address = newAddress;}
    public void setCreditCardNumber(string newCreditCardNumber){this.creditCardNumber = newCreditCardNumber;}
    public void setExpDate(string newExpDate){this.expDate = newExpDate}
    public void setCVV(string newCVV){this.cvv = newCVV;}
    public void setBalance(int newBalance){this.balance = newBalance;}
    
}
