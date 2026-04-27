import Control.Server;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Start the web server
        try {
            Server server = new Server(3000);
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}