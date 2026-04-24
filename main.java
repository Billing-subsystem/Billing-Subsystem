import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:/Users/dylanroman/IdeaProjects/Billing-Subsystem/database.sqlite";

        try (Connection conn = DriverManager.getConnection(url)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
