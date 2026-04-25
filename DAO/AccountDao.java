package DAO;

import Model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDao implements Dao<Account> {
    private Connection connection;

    public AccountDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public Optional<Account> get(Account account) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Account WHERE accountID = ?");
        statement.setLong(1, account.getAccountID());
        ResultSet searchResult = statement.executeQuery();

        long accountID = searchResult.getLong(1);
        String username = searchResult.getString(2);
        String email = searchResult.getString(3);
        String password = searchResult.getString(4);
        double balance = searchResult.getDouble(5);
        String creditCardNumber = searchResult.getString(6);


        Account resultAccount = new Account(accountID, username, email, password, balance, creditCardNumber);

        return Optional.of(resultAccount);
    }

    @Override
    public List<Account> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Account");

        ResultSet results = statement.executeQuery();

        List<Account> resultList = new ArrayList<>();

        while (results.next()) {
            resultList.add(new Account(results.getLong(1), , results.getString(2), results.getString(3), results.getString(4), results.getDouble(5), results.getString(6)));
        }

        return resultList;
    }

    @Override
    public void save(Account account) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Account VALUES (?, ?, ?, ?, ?, ?)");
        statement.setLong(1, account.getAccountID());
        statement.setString(2, account.getUsername());
        statement.setString(3, account.getEmail());
        statement.setString(4, account.getPassword());
        statement.setDouble(5, account.getBalance());
        statement.setString(6, account.getCreditCardNumber());

        statement.executeUpdate();
    }

    @Override
    public void update(Account account, String[] params) throws SQLException {

    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public void createTable() throws SQLException {
        String instruction = "CREATE TABLE IF NOT EXISTS Account (" +
                "accountID INTEGER PRIMARY KEY," +
                "username VARCHAR (15)," +
                "email VARCHAR (30)," +
                "password VARCHAR (15)," +
                "balance DOUBLE," +
                "creditCardNumber VARCHAR (19)" +
                ");";

        Statement statement = connection.createStatement();
        statement.execute(instruction);
    }

}
