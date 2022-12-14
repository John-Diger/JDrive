package src;

import dao.BucketDao;
import server.Server;

public class JDriveApplication {

    private static final JavaBeanFactory javaBeanFactory = new JavaBeanFactory();

    public static void main(String[] args) {
        BucketDao bucketDao = javaBeanFactory.getBucketDao();
        Server server = javaBeanFactory.getServer();
        server.download();
    }
}
