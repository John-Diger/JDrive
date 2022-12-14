import java.util.logging.Logger;

import dao.ConnectionManager;
import dao.ConnectionManagerImpl;
import dao.JDBCRepository;

public class JavaBeanFactory {

    public JDBCRepository getJdbcRepository() {
        return new JDBCRepository(getConnectionManager(), getLogger());
    }

    private ConnectionManager getConnectionManager() {
        return new ConnectionManagerImpl();
    }

    private Logger getLogger() {
        return Logger.getGlobal();
    }

}
