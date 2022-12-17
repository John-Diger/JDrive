package src;


import src.professor.ProfessorRepository;
import src.professor.ProfessorRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaBeanFactory {

////    public ProfessorRepository getBucketDao() {
////        return new ProfessorRepository(getConnectionManager(), getLogger());
////    }
//
//    public ConnectionManager getConnectionManager() {
//        return new ConnectionManagerImpl();
//    }
//
//    public Logger getLogger() {
//        return Logger.getGlobal();
//    }
//
//    public InputAgent getInputAgent() {
//        return new InputAgent();
//    }
//
//    public ProfessorController getOutputAgent() {
//        return new ProfessorController();
//    }
//
//    public ProfessorServer getServer() {
//        return new ProfessorServer(getInputAgent(), getOutputAgent(), getBucketDao());
//    }


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdrive", "root", "112233");
    }
}
