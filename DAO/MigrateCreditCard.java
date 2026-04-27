package DAO;

import java.sql.*;

public class MigrateCreditCard {
    public static void main(String[] args) throws SQLException {
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();

        // Ensure CreditCard table exists
        stmt.execute("CREATE TABLE IF NOT EXISTS CreditCard (" +
            "creditCardNumber INTEGER," +
            "expDate VARCHAR(5)," +
            "CVV VARCHAR(4)," +
            "accountID INTEGER," +
            "FOREIGN KEY (accountID) REFERENCES Account(accountID)," +
            "PRIMARY KEY (creditCardNumber, accountID))");

        // Check if creditCardNumber column exists in Account
        ResultSet cols = conn.getMetaData().getColumns(null, null, "Account", "creditCardNumber");
        if (!cols.next()) {
            System.out.println("creditCardNumber column not found in Account — nothing to migrate.");
            conn.close();
            return;
        }

        // Move any non-null card numbers into CreditCard table
        ResultSet rs = stmt.executeQuery("SELECT accountID, creditCardNumber FROM Account WHERE creditCardNumber IS NOT NULL AND creditCardNumber != ''");
        PreparedStatement insert = conn.prepareStatement(
            "INSERT OR IGNORE INTO CreditCard (creditCardNumber, expDate, CVV, accountID) VALUES (?, '', '', ?)");
        int moved = 0;
        while (rs.next()) {
            String raw = rs.getString("creditCardNumber").replaceAll("[^0-9]", "");
            if (!raw.isEmpty()) {
                insert.setLong(1, Long.parseLong(raw));
                insert.setLong(2, rs.getLong("accountID"));
                insert.executeUpdate();
                moved++;
            }
        }
        System.out.println("Moved " + moved + " card record(s) to CreditCard table.");

        // Recreate Account table without creditCardNumber column
        stmt.execute("CREATE TABLE Account_new (" +
            "accountID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username VARCHAR(15)," +
            "email VARCHAR(30)," +
            "password VARCHAR(15)," +
            "balance DOUBLE," +
            "last_payment_date TEXT)");

        stmt.execute("INSERT INTO Account_new (accountID, username, email, password, balance, last_payment_date) " +
            "SELECT accountID, username, email, password, balance, last_payment_date FROM Account");

        stmt.execute("DROP TABLE Account");
        stmt.execute("ALTER TABLE Account_new RENAME TO Account");

        conn.commit();
        System.out.println("Dropped creditCardNumber column from Account.");
        conn.close();
    }
}
