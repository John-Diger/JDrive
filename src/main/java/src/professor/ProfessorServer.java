package src.professor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import src.RequestForm;
import src.ioagent.InputAgent;
import src.ioagent.InputValidator;
import src.ioagent.OutputAgent;

public class ProfessorServer {

    // private final InputAgent inputAgent;
    // private final OutputAgent outputAgent;
    // private final ProfessorRepository professorRepository;

    ObjectInputStream objectInputStream; // Class의 객체를 읽어올때 사용
    PrintWriter printWriter; // 값을 전달할때 사용

    ServerSocket serverSocket;

    Socket clientSocket;

    public ProfessorServer() {
        // this.inputAgent = new InputAgent(new InputValidator());
        // this.outputAgent = new OutputAgent();
        // this.professorRepository = new ProfessorRepositoryImpl();
    }

    // 교수님은 이 메서드를 실행시켜놓으면 학생들의 소켓접근을 허용하고
    // 학생들의 요청/응답에 따라 알맞는 처리를 해준다.
    public static void main(String[] args) {
        ProfessorServer professorServer = new ProfessorServer();
        professorServer.openServerSocket();
        professorServer.acceptClientSocket();
        professorServer.stopServer();
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

                System.out.println("after socket connect");
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));

                // Client 로부터 객체를 읽어오는 역활을 하는 객체를 생성
                objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                RequestForm requestForm = (RequestForm)objectInputStream.readObject();
                System.out.println(requestForm.getMethod());

                printWriter.write("ok");
                printWriter.close(); // close() or flush()를 해줘야지 전해진다
                clientSocket.close(); // 여기서 socket 접속이 끊어져야 클라이언트가 종료가 됩니다.
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
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
