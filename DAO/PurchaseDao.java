package DAO;

import Model.Purchase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class PurchaseDao implements Dao<Purchase> {
    private Connection connection;

    public PurchaseDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Purchase> get(Purchase purchase) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Purchase> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String save(Purchase purchase) throws SQLException {

        return null;
    }

    @Override
    public void update(Purchase purchase, String[] params) throws SQLException {
    }

    @Override
    public void delete(Purchase purchase) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {
        String table = "CREATE TABLE IF NOT EXISTS Purchase (" +
                "purchaseID INTEGER," +
                "subscriptionID INTEGER," +
                "date VARCHAR (5)," +
                "FOREIGN KEY (subscriptionID) REFERENCES Subscription(subscriptionID)," +
                "PRIMARY KEY (purchaseID, subscriptionID)" +
                ");";

        Statement statement = connection.createStatement();
        statement.execute(table);
    }
}
