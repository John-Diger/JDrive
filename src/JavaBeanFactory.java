import dao.ConnectionManager;
import dao.ConnectionManagerImpl;
import dao.JDBCRepository;

public class JavaBeanFactory {

    public JDBCRepository getJdbcRepository() {
        return new JDBCRepository(getConnectionManager());
    }

    public ConnectionManager getConnectionManager() {
        return new ConnectionManagerImpl();
    }
}
