package DAO;

import Model.Subscription;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class SubscriptionDao implements Dao<Subscription> {
    private Connection connection;

    public SubscriptionDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public Optional<Subscription> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Subscription> getAll() {
        return List.of();
    }

    @Override
    public void save(Subscription subscription) {

    }

    @Override
    public void update(Subscription subscription, String[] params) {

    }

    @Override
    public void delete(Subscription subscription) {

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
