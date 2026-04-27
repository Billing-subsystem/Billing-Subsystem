package Control;

import DAO.*;
import Model.Account;
import Model.CreditCard;
import Model.Subscription;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class Server {
    private HttpServer SERVER_INSTANCE;
    public Server(int port) throws IOException, SQLException {
        SERVER_INSTANCE = HttpServer.create(new InetSocketAddress(port), 0);
        Connection connection = DBUtil.getConnection();
        AccountDao accountDao = new AccountDao(connection);
        CreditCardDao creditCardDao = new CreditCardDao(connection);

        // Serve a default html for root path /
        SERVER_INSTANCE.createContext("/", (exchange) -> {
            Responses.sendFile("./View/login.html", exchange);
        });

        // Serve all files in View under /
        staticServe("./View", "/");
        staticServe("./javascript", "/");

        // API endpoints
        SERVER_INSTANCE.createContext("/api/login", (exchange) -> {
            addCorsHeaders(exchange);
            String query = exchange.getRequestURI().getQuery();
            String email = query.split("&")[0].split("=")[1];
            String password = query.split("&")[1].split("=")[1];
            try {
                AccountDao dao = new AccountDao();
                Responses.sendString(dao.login(email, password), exchange);
            } catch (Exception e) {
                Responses.sendString("{\"accountId\":null}", exchange);
            }
        });

        SERVER_INSTANCE.createContext("/api/register", (exchange) -> {
            addCorsHeaders(exchange);
            String query = exchange.getRequestURI().getQuery();
            String username = query.split("&")[0].split("=")[1];
            String email = query.split("&")[1].split("=")[1];
            String password = query.split("&")[2].split("=")[1];
            try {
                Account account = new Account(username, email, password, 0.0);

                Responses.sendString(accountDao.save(account), exchange);
            } catch (Exception e) {
                Responses.sendString("{\"success\":false}", exchange);
            }
        });

        SERVER_INSTANCE.createContext("/api/account", (exchange) -> {
            addCorsHeaders(exchange);
            String query = exchange.getRequestURI().getQuery();
            long id = Long.parseLong(query.split("=")[1]);
            try {
                Responses.sendString(accountDao.getAccountJson(id), exchange);
            } catch (Exception e) {
                Responses.sendString("{}", exchange);
            }
        });

        SERVER_INSTANCE.createContext("/api/updateSubscription", (exchange) -> {
            addCorsHeaders(exchange);
            String query = exchange.getRequestURI().getQuery();
            long id = Long.parseLong(query.split("&")[0].split("=")[1]);
            double price = Double.parseDouble(query.split("&")[1].split("=")[1]);
            try {
                accountDao.updateBalance(id, price);
                int subType = price == 29 ? 1 : price == 99 ? 2 : 3;
                SubscriptionDao subDao = new SubscriptionDao();
                subDao.save(new Subscription(0, subType, java.time.LocalDate.now().toString(), id));
                Responses.sendString("{\"success\":true}", exchange);
            } catch (Exception e) {
                Responses.sendString("{\"success\":false}", exchange);
            }
        });


        SERVER_INSTANCE.createContext("/api/saveCreditCard", (exchange) -> {
            addCorsHeaders(exchange);
            try {
                String query = exchange.getRequestURI().getQuery();
                java.util.Map<String, String> params = new java.util.HashMap<>();
                for (String pair : query.split("&")) {
                    String[] kv = pair.split("=", 2);
                    params.put(kv[0], kv.length > 1 ? java.net.URLDecoder.decode(kv[1], "UTF-8") : "");
                }
                long accountId = Long.parseLong(params.get("accountId"));
                long cardNumber = Long.parseLong(params.get("cardNumber"));
                String expDate = params.get("expDate");
                String cvv = params.get("cvv");
                CreditCard creditCard = new CreditCard(cardNumber, expDate, cvv, accountId);
                creditCardDao.save(creditCard);
                Responses.sendString("{\"success\":true}", exchange);
            } catch (Exception e) {
                e.printStackTrace();
                Responses.sendString("{\"success\":false}", exchange);
            }
        });

        SERVER_INSTANCE.setExecutor(null);
        SERVER_INSTANCE.start();
        System.out.println("Server listening on: http://localhost:" + port);
    }
    
    private void initDb() {
        try {
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS Account (" +
                "accountID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username VARCHAR(15)," +
                "email VARCHAR(30)," +
                "password VARCHAR(15)," +
                "balance DOUBLE," +
                "last_payment_date VARCHAR(20))");
            stmt.execute("CREATE TABLE IF NOT EXISTS CreditCard (" +
                "creditCardNumber INTEGER," +
                "expDate VARCHAR(5)," +
                "CVV INTEGER," +
                "accountID INTEGER," +
                "FOREIGN KEY (accountID) REFERENCES Account(accountID)," +
                "PRIMARY KEY (creditCardNumber, accountID))");
            stmt.execute("CREATE TABLE IF NOT EXISTS Subscription (" +
                "subscriptionID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "accountID INTEGER," +
                "subscriptionType INTEGER," +
                "status VARCHAR(10)," +
                "initialDate VARCHAR(20)," +
                "FOREIGN KEY (accountID) REFERENCES Account(accountID))");
            try { stmt.execute("ALTER TABLE Account DROP COLUMN creditCardNumber"); }
            catch (SQLException ignored) {}
        } catch (SQLException e) {
            System.out.println("DB init error: " + e.getMessage());
        }
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
