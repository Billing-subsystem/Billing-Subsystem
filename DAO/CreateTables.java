package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateTables {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        AccountDao accountDao = new AccountDao(connection);

        accountDao.createTable();
    }
}
