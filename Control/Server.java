package Control;

import DAO.AccountDao;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;


public class Server {
    private HttpServer SERVER_INSTANCE;
    public Server(int port) throws IOException {
        SERVER_INSTANCE = HttpServer.create(new InetSocketAddress(port), 0);

        // Serve a default html for root path /
        SERVER_INSTANCE.createContext("/", (exchange) -> {
            Responses.sendFile("./View/login.html", exchange);
        });

        // Serve all files in View under /
        staticServe("./View", "/");
        staticServe("./javascript", "/");

//        //api endpoints
//         SERVER_INSTANCE.createContext("/api/login", (exchange) -> {
//            addCorsHeaders(exchange);
//            String query = exchange.getRequestURI().getQuery();
//            String email = query.split("&")[0].split("=")[1];
//            String password = query.split("&")[1].split("=")[1];
//            AccountDao dao = new AccountDao();
//            Responses.sendString(dao.login(email, password), exchange);
//        });
//
//        SERVER_INSTANCE.createContext("/api/register", (exchange) -> {
//            addCorsHeaders(exchange);
//            String query = exchange.getRequestURI().getQuery();
//            String username = query.split("&")[0].split("=")[1];
//            String email = query.split("&")[1].split("=")[1];
//            String password = query.split("&")[2].split("=")[1];
//            AccountDao dao = new AccountDao();
//            Responses.sendString(dao.register(username, email, password), exchange);
//        });
//
//        SERVER_INSTANCE.createContext("/api/account", (exchange) -> {
//            addCorsHeaders(exchange);
//            String query = exchange.getRequestURI().getQuery();
//            long id = Long.parseLong(query.split("=")[1]);
//            AccountDao dao = new AccountDao();
//            Responses.sendString(dao.getAccountJson(id), exchange);
//        });
//
//        SERVER_INSTANCE.createContext("/api/subscription", (exchange) -> {
//            addCorsHeaders(exchange);
//            String query = exchange.getRequestURI().getQuery();
//            long id = Long.parseLong(query.split("=")[1]);
//            AccountDao dao = new AccountDao();
//            Responses.sendString(dao.getSubscriptionJson(id), exchange);
//        });
//
//        SERVER_INSTANCE.createContext("/api/updateSubscription", (exchange) -> {
//            addCorsHeaders(exchange);
//            String query = exchange.getRequestURI().getQuery();
//            long id = Long.parseLong(query.split("&")[0].split("=")[1]);
//            double price = Double.parseDouble(query.split("&")[1].split("=")[1]);
//            AccountDao dao = new AccountDao();
//            dao.updateBalance(id, price);
//            Responses.sendString("{\"success\":true}", exchange);
//        });


        SERVER_INSTANCE.setExecutor(null);
        SERVER_INSTANCE.start();
        System.out.println("Server listening on: http://localhost:" + port);
    }
    
    private void addCorsHeaders(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Content-Type", "application/json");
    }

    /**
     * Statically serve all files in a directory, at urlBase/(fileName)
     * Serves only at the specified directory path and does not serve subfolders
     * @param directoryPath The path to the directory
     * @param urlBase The base at which these files are located. For example, if urlBase is /foo/bar , the files will be served at /foo/bar/(fileName)
     */
    private void staticServe(String directoryPath, String urlBase) {
        File directory = new File(directoryPath);
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                SERVER_INSTANCE.createContext(urlBase + file.getName(), (exchange) -> {
                   Responses.sendFile(file.getPath(), exchange);
                });
            }
        }
    }

    /**
     * Responses for requests to the server
     */
    public static class Responses {
        /**
         * Send a file as a response
         * @param filePath Path of the file
         * @param exchange The HttpExchange object
         */
        public static void sendFile(String filePath, HttpExchange exchange) {
            File file = Path.of(filePath).toFile();
            try (FileInputStream fr = new FileInputStream(file)) {
                exchange.sendResponseHeaders(200, file.length());

                OutputStream responseBodyStream = exchange.getResponseBody();

                // Copy file input stream to response output stream
                byte[] buffer = new byte[1024];
                int len = fr.read(buffer);
                while (len != -1) {
                    responseBodyStream.write(buffer, 0, len);
                    len = fr.read(buffer);
                }

                responseBodyStream.close();

            } catch (FileNotFoundException e) {
                System.out.println("Error while reading file: " + e.getMessage());
                String notFoundStr = "File not found";

                sendString(notFoundStr, exchange, 403);
            } catch (IOException e) {
                System.out.println("Error while sending response: " + e.getMessage());
            }
        }

        /**
         * Sends a simple string as the response with response code 200
         * @param str The string to respond with
         * @param exchange The HttpExchange object
         */
        public static void sendString(String str, HttpExchange exchange) {
            sendString(str, exchange, 200);
        }

        /**
         * Sends a simple string as the response
         * @param str The string to respond with
         * @param exchange The HttpExchange object
         * @param responseCode The HTTP response code
         */
        public static void sendString(String str, HttpExchange exchange, int responseCode) {
            try {
                exchange.sendResponseHeaders(responseCode, str.getBytes().length);

                OutputStream responseBodyStream = exchange.getResponseBody();
                responseBodyStream.write(str.getBytes());

                responseBodyStream.close();
            } catch (IOException e) {
                System.out.println("Error while sending response: " + e.getMessage());
            }
        }
    }
}
