package dao;

public class JDBCRepository {

    private final ConnectionManager connectionManager;

    public JDBCRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}
