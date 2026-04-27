import Control.Server;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        // Start the web server
        try {
            Server server = new Server(5000);
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }
}