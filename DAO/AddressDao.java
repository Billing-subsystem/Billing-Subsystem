package DAO;

import Model.Address;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class AddressDao implements Dao<Address> {
    private Connection connection;

    public AddressDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Address> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Address> getAll() {
        return List.of();
    }

    @Override
    public void save(Address address) {

    }

    @Override
    public void update(Address address, String[] params) {

    }

    @Override
    public void delete(Address address) {

    }

    @Override
    public void createTable() throws SQLException {
        String table = "CREATE TABLE IF NOT EXISTS Address (" +
                "accountID INTEGER PRIMARY KEY," +
                "buildingNumber INTEGER," +
                "streetAddress VARCHAR (45)," +
                "city VARCHAR (15)," +
                "state VARCHAR (2)," +
                "zipCode INTEGER," +
                "FOREIGN KEY (accountID) REFERENCES Account(accountID)" +
                ");";

        Statement statement = connection.createStatement();
        statement.execute(table);
    }
}
