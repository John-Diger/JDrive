package src;


import src.dao.BucketDao;
import src.dao.ConnectionManager;
import src.dao.ConnectionManagerImpl;
import src.ioagent.InputAgent;
import src.ioagent.OutputAgent;
import src.server.Server;

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
