package Model.DAO;

import Model.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDao implements Dao<Account>{
    private List<Account> accounts = new ArrayList<>();
    private static final String URL = "jdbc:sqlite:database.sqlite";

    @Override
    public Optional<Account> get(long id) {
        return Optional.ofNullable(accounts.get((int) id));
    }

    @Override
    public List<Account> getAll() {
        return accounts;
    }

    @Override
    public void save(Account account) {
        accounts.add(account);
    }

    @Override
    public void update(Account account, String[] params) {
        account.setUsername(params[0]);
        account.setPassword(params[1]);
        account.setEmail(params[2]);
        account.setAddress(params[3]);
        account.setBalance(Double.parseDouble(params[4]));
    }

    @Override
    public void delete(Account account) {
        accounts.remove(account);
    }

//Methods for server
      public String login(String email, String password) {
        String sql = "SELECT account_ID, username, balance, last_payment_date FROM Account WHERE email = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String date = rs.getString("last_payment_date");
                return String.format(
                    "{\"accountId\":%d,\"username\":\"%s\",\"balance\":%.2f,\"lastPaymentDate\":\"%s\"}",
                    rs.getLong("account_ID"),
                    rs.getString("username"),
                    rs.getDouble("balance"),
                    date != null ? date : ""
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return "{\"accountId\":null}";
    }

    public String register(String username, String email, String password) {
        String sql = "INSERT INTO Account (username, email, password, balance) VALUES (?, ?, ?, 0)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();
            return "{\"success\":true}";
        } catch (SQLException e) { e.printStackTrace(); }
        return "{\"success\":false}";
    }

    public String getAccountJson(long id) {
        String sql = "SELECT * FROM Account WHERE account_ID = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String date = rs.getString("last_payment_date");
                return String.format(
                    "{\"accountId\":%d,\"username\":\"%s\",\"email\":\"%s\",\"balance\":%.2f,\"lastPaymentDate\":\"%s\"}",
                    rs.getLong("account_ID"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getDouble("balance"),
                    date != null ? date : ""
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return "{}";
    }

    public String getSubscriptionJson(long id) {
        String sql = "SELECT balance FROM Account WHERE account_ID = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return String.format("{\"price\":%.2f}", rs.getDouble("balance"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return "{}";
    }

    public void updateBalance(long id, double newBalance) {
        String sql = "UPDATE Account SET balance = ? WHERE account_ID = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
