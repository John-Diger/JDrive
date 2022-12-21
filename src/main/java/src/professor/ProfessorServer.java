package src.professor;

import src.ExtractedContent;
import src.Method;
import src.RequestForm;
import src.ResponseAllListForm;
import src.ioagent.OutputAgent;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ProfessorServer {

    private final ProfessorRepository professorRepository = new ProfessorRepositoryImpl();
    private final OutputAgent outputAgent = new OutputAgent();

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream; // Class의 객체를 읽어올때 사용
    PrintWriter printWriter; // 값을 전달할때 사용
    ServerSocket serverSocket;

    Socket clientSocket;

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
                RequestForm requestForm = (RequestForm) objectInputStream.readObject();
                if (requestForm.getMethod().equals(Method.UPLOAD)) {
                    System.out.println("사용자 업로드 요청 처리 중" + requestForm.getMethod());
                    professorRepository.insert(requestForm.getData());
                    System.out.println("업로드 완료" + requestForm.getMethod());
                } else if (requestForm.getMethod().equals(Method.GET_LIST)) {
                    try {
                        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                        ResponseAllListForm all = professorRepository.findAll();
                        for (ExtractedContent extractedContent : all.getExtractedContents()) {
                            objectOutputStream.writeObject(extractedContent);
                        }
                        objectOutputStream.flush();
                        printWriter.write("ok");
                        printWriter.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

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
