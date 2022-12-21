package src.professor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import src.ioagent.InputAgent;
import src.ioagent.OutputAgent;

public class ProfessorServer {

    private final InputAgent inputAgent;
    private final OutputAgent outputAgent;
    private final ProfessorRepository professorRepository;

    ServerSocket serverSocket;

    Socket clientSocket;

    public ProfessorServer(InputAgent inputAgent, OutputAgent outputAgent, ProfessorRepository professorRepository) {
        this.inputAgent = inputAgent;
        this.outputAgent = outputAgent;
        this.professorRepository = professorRepository;
    }

    // 교수님은 이 메서드를 실행시켜놓으면 학생들의 소켓접근을 허용하고
    // 학생들의 요청/응답에 따라 알맞는 처리를 해준다.
    public static void main(String[] args) {
    }


    public void stopServer() {
        try {
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptClientSocket() {
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accept...");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void openServerSocket() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(Inet4Address.getByName("127.0.0.1"), 8080));
            System.out.println("Listen...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
