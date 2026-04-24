package Model;

public class SubscriptionType {
    int type;
    double amount;

    public SubscriptionType(int type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SubscriptionType{" +
                "type=" + type +
                ", amount=" + amount +
                '}';
    }
}
