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

    public AccountDao() throws SQLException {
        this.connection = DBUtil.getConnection();
    }

    public String login(String email, String password) {
        String sql = "SELECT accountID, username, balance FROM Account WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.getLong("accountID"));
            if (rs.next()) {
                String date = rs.getString("balance");
                return String.format(
                    "{\"accountId\":%d,\"username\":\"%s\",\"balance\":%.2f,\"lastPaymentDate\":\"%s\"}",
                    rs.getLong("accountID"),
                    rs.getString("username"),
                    rs.getDouble("balance"),
                    date != null ? date : ""
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return "{\"accountId\":null}";
    }

    public String getAccountJson(long id) {
        String sql = "SELECT accountID, username, email, balance, last_payment_date FROM Account WHERE accountID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String date = rs.getString("last_payment_date");
                return String.format(
                    "{\"accountId\":%d,\"username\":\"%s\",\"email\":\"%s\",\"balance\":%.2f,\"lastPaymentDate\":\"%s\"}",
                    rs.getLong("accountID"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getDouble("balance"),
                    date != null ? date : ""
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return "{}";
    }

    public void updateBalance(long id, double newBalance) {
        String date = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        String sql = "UPDATE Account SET balance = ?, last_payment_date = ? WHERE accountID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setString(2, date);
            stmt.setLong(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Optional<Account> get(Account account) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT accountID, username, email, password, balance FROM Account WHERE accountID = ?");
        statement.setLong(1, account.getAccountID());
        ResultSet searchResult = statement.executeQuery();

        long accountID = searchResult.getLong(1);
        String username = searchResult.getString(2);
        String email = searchResult.getString(3);
        String password = searchResult.getString(4);
        double balance = searchResult.getDouble(5);

        return Optional.of(new Account(accountID, username, email, password, balance));
    }

    @Override
    public List<Account> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT accountID, username, email, password, balance FROM Account");
        ResultSet results = statement.executeQuery();
        List<Account> resultList = new ArrayList<>();
        while (results.next()) {
            resultList.add(new Account(results.getLong(1), results.getString(2), results.getString(3), results.getString(4), results.getDouble(5)));
        }
        return resultList;
    }

    @Override
    public String save(Account account) throws SQLException {
        try (            PreparedStatement statement = connection.prepareStatement("INSERT INTO Account VALUES (NULL, ?, ?, ?, ?, NULL)");) {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getPassword());
            statement.setDouble(4, account.getBalance());
            statement.executeUpdate();
            return "{\"success\":true}";
        } catch (SQLException e) { e.printStackTrace(); }
        return "{\"success\":false}";
    }

    @Override
    public void update(Account account, String[] params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Account SET " +
                "username = ?," +
                "email = ?," +
                "password = ?," +
                "balance = ? " +
                "WHERE accountID = ?");

        statement.setString(1, params[1]);
        statement.setString(2, params[2]);
        statement.setString(3, params[3]);
        statement.setDouble(4, Double.parseDouble(params[4]));
        statement.setLong(5, Long.parseLong(params[0]));

        statement.executeUpdate();
    }

    @Override
    public void delete(Account account) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Account WHERE accountID = ?");
        statement.setLong(1, account.getAccountID());
        statement.execute();
    }

    @Override
    public void createTable() throws SQLException {
        String instruction = "CREATE TABLE IF NOT EXISTS Account (" +
                "accountID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username VARCHAR (15) UNIQUE," +
                "email VARCHAR (30) UNIQUE," +
                "password VARCHAR (15)," +
                "balance DOUBLE," +
                "last_payment_date VARCHAR (15)" +
                ");";

        Statement statement = connection.createStatement();
        statement.execute(instruction);
    }

    public Optional<Account> getUsername(String searchUserName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT accountID, username, email, password, balance FROM Account WHERE username = ?");
        statement.setString(1, searchUserName);
        ResultSet searchResult = statement.executeQuery();

        long accountID = searchResult.getLong(1);
        String username = searchResult.getString(2);
        String email = searchResult.getString(3);
        String password = searchResult.getString(4);
        double balance = searchResult.getDouble(5);

        return Optional.of(new Account(accountID, username, email, password, balance));
    }
}
