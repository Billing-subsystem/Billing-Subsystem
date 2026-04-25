package DAO;

import Model.CreditCard;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class CreditCardDao implements Dao<CreditCard> {
    private Connection connection;

    public CreditCardDao(Connection connection) throws SQLException {
        this.connection = connection;
    }


    @Override
    public Optional<CreditCard> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<CreditCard> getAll() {
        return List.of();
    }

    @Override
    public void save(CreditCard creditCard) {

    }

    @Override
    public void update(CreditCard creditCard, String[] params) {

    }

    @Override
    public void delete(CreditCard creditCard) {

    }

    @Override
    public void createTable() throws SQLException {
        String table = "CREATE TABLE IF NOT EXISTS CreditCard (" +
                "creditCardNumber INTEGER (16)," +
                "expDate VARCHAR (5)," +
                "CVV INTEGER," +
                "accountID INTEGER," +
                "FOREIGN KEY (accountID) REFERENCES Account(accountID)," +
                "PRIMARY KEY (creditCardNumber, accountID)" +
                ");";

        Statement statement = connection.createStatement();
        statement.execute(table);
    }
}
