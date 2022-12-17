//package src;
//
//import src.dao.ProfessorRepository;
//import src.professor.ProfessorServer;
//
//import java.sql.SQLException;
//
//public class JDriveApplication {
//
//    private static final JavaBeanFactory javaBeanFactory = new JavaBeanFactory();
//
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ProfessorRepository bucketDao = javaBeanFactory.getBucketDao();
//        bucketDao.insertFile();
//        ProfessorServer professorServer = javaBeanFactory.getServer();
//        professorServer.download();
//    }
//}
