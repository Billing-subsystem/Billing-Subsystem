package DAO;

import Model.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class CreditCardDao implements Dao<CreditCard> {
    private Connection connection;

    public CreditCardDao(Connection connection) {
        this.connection = connection;
    }

    public CreditCardDao() throws SQLException {
        this.connection = DBUtil.getConnection();
    }

    @Override
    public Optional<CreditCard> get(CreditCard creditCard) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<CreditCard> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String save(CreditCard creditCard) throws SQLException {
        String sql = "INSERT OR REPLACE INTO CreditCard (creditCardNumber, expDate, CVV, accountID) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, creditCard.getCreditCardNumber());
        statement.setString(2, creditCard.getExpDate());
        statement.setString(3, creditCard.getCVV());
        statement.setLong(4, creditCard.getAccountID());
        statement.executeUpdate();
        return sql;
    }

    @Override
    public void update(CreditCard creditCard, String[] params) throws SQLException {
    }

    @Override
    public void delete(CreditCard creditCard) throws SQLException {

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
