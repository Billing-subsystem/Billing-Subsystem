package DAO;

import Model.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class AccountDao implements Dao<Account> {
    private Connection connection;

    public AccountDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public Optional<Account> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Account> getAll() {
        return List.of();
    }

    @Override
    public void save(Account account) {

    }

    @Override
    public void update(Account account, String[] params) {

    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public void createTable() throws SQLException {
        String table = "CREATE TABLE IF NOT EXISTS Account (" +
                "accountID INTEGER PRIMARY KEY," +
                "username VARCHAR (15)," +
                "email VARCHAR (30)," +
                "password VARCHAR (15)," +
                "balance DOUBLE," +
                "creditCardNumber INTEGER" +
                ");";

        Statement statement = connection.createStatement();
        statement.execute(table);
    }

}
