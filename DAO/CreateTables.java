package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateTables {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        AccountDao accountDao = new AccountDao(connection);
        AddressDao addressDao = new AddressDao(connection);
        CreditCardDao creditCardDao = new CreditCardDao(connection);
        SubscriptionDao subscriptionDao = new SubscriptionDao(connection);
        SubscriptionTypeDao subscriptionTypeDao = new SubscriptionTypeDao(connection);

        accountDao.createTable();
        addressDao.createTable();
        creditCardDao.createTable();
        subscriptionDao.createTable();
        subscriptionTypeDao.createTable();

        connection.close();
    }
}
