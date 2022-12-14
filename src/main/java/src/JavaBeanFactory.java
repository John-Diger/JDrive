package src;

import dao.ConnectionManager;
import dao.ConnectionManagerImpl;
import dao.BucketDao;
import ioagent.InputAgent;
import ioagent.OutputAgent;
import server.Server;

import java.util.logging.Logger;

public class JavaBeanFactory {

    public BucketDao getBucketDao() {
        return new BucketDao(getConnectionManager(), getLogger());
    }

    public ConnectionManager getConnectionManager() {
        return new ConnectionManagerImpl();
    }

    public Logger getLogger() {
        return Logger.getGlobal();
    }

    public InputAgent getInputAgent() {
        return new InputAgent();
    }

    public OutputAgent getOutputAgent() {
        return new OutputAgent();
    }

    public Server getServer() {
        return new Server(getInputAgent(), getOutputAgent(), getBucketDao());
    }
}
