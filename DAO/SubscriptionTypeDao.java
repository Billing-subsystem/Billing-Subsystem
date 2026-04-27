package DAO;

import Model.SubscriptionType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class SubscriptionTypeDao implements Dao<SubscriptionType> {
    private Connection connection;

    public SubscriptionTypeDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public Optional<SubscriptionType> get(SubscriptionType subscriptionType) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<SubscriptionType> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String save(SubscriptionType subscriptionType) throws SQLException {

        return null;
    }

    @Override
    public void update(SubscriptionType subscriptionType, String[] params) throws SQLException {
    }

    @Override
    public void delete(SubscriptionType subscriptionType) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {
        String table = "CREATE TABLE IF NOT EXISTS SubscriptionType (" +
                "subscriptionType INTEGER PRIMARY KEY," +
                "amount DOUBLE" +
                ");";

        Statement statement = connection.createStatement();
        statement.execute(table);
    }
}
