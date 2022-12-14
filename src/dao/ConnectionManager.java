package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    Connection makeConnection() throws ClassNotFoundException, SQLException;
}

