package DAO;

import Model.Address;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AddressDao implements Dao<Address> {
    private Connection connection;

    AddressDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Address> get(Address address) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Address> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public void save(Address address) throws SQLException {

    }

    @Override
    public void update(Address address, String[] params) throws SQLException {
    }

    @Override
    public void delete(Address address) throws SQLException {

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
