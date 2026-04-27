package Model;

public class Account {
    private long accountID;
    private static long accountIDTracker;
    private String username;
    private String email;
    private String password;
    private double balance;

    public Account(long accountID, String username, String email, String password, double balance) {
        this.accountID = accountID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public Account(String username, String email, String password, double balance) {
        this(++accountIDTracker, username, email, password, balance);
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
