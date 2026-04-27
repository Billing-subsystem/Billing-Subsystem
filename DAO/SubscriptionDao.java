package DAO;

import Model.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class SubscriptionDao implements Dao<Subscription> {
    private Connection connection;

    public SubscriptionDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public SubscriptionDao() throws SQLException {
        this.connection = DBUtil.getConnection();
    }

    @Override
    public Optional<Subscription> get(Subscription subscription) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Subscription> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public void save(Subscription subscription) throws SQLException {
        String sql = "INSERT INTO Subscription (accountID, subscriptionType, status, initialDate) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, subscription.getAccountID());
        statement.setInt(2, subscription.getSubscriptionType());
        statement.setString(3, "ACTIVE");
        statement.setString(4, subscription.getInitialDate());
        statement.executeUpdate();
    }

    @Override
    public void update(Subscription subscription, String[] params) throws SQLException {
    }

    @Override
    public void delete(Subscription subscription) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {
        String table = "CREATE TABLE IF NOT EXISTS Subscription (" +
                "subscriptionID INTEGER," +
                "accountID INTEGER," +
                "subscriptionType INTEGER," +
                "status VARCHAR (10)," +
                "initialDate VARCHAR (5)," +
                "FOREIGN KEY (accountID) REFERENCES Account(accountID)," +
                "FOREIGN KEY (subscriptionType) REFERENCES SubscriptionType(subscriptionType)" +
                "PRIMARY KEY (subscriptionID, accountID)" +
                ");";

        Statement statement = connection.createStatement();
        statement.execute(table);
    }
}
