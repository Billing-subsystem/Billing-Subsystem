package DAO;

import Model.Purchase;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class PurchaseDao implements Dao<Purchase> {
    private Connection connection;

    public PurchaseDao(Connection connection) throws SQLException {
        this.connection = connection;
    }


    @Override
    public Optional<Purchase> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Purchase> getAll() {
        return List.of();
    }

    @Override
    public void save(Purchase purchase) {

    }

    @Override
    public void update(Purchase purchase, String[] params) {

    }

    @Override
    public void delete(Purchase purchase) {

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
