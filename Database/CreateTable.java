import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateTable {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:database.sqlite";
        String account_table = "CREATE TABLE IF NOT EXISTS Account (" +
                "account_ID INTEGER PRIMARY KEY," +
                "username VARCHAR (20)," +
                "email VARCHAR (20)," +
                "balance INTEGER," +
                "address VARCHAR (50)," +
                "password VARCHAR (15)" +
                ");";

        try (Connection conn = DriverManager.getConnection(url)) {
            Statement statement = conn.createStatement();

            statement.execute(account_table);
            System.out.println("Table Created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
