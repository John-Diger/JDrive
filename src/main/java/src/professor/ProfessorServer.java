package src.professor;

import src.Method;
import src.RequestForm;
import src.ResponseAllListForm;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ProfessorServer {

    private final ProfessorRepository professorRepository = new ProfessorRepositoryImpl();
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream; // Class의 객체를 읽어올때 사용
    PrintWriter printWriter; // 값을 전달할때 사용
    ServerSocket serverSocket;
    Socket clientSocket;
    ProfessorService professorService;

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
                System.out.println("신규 사용자와 연결되었습니다.");
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
                        // 파일 목록 내려주기
                        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                        ResponseAllListForm all = professorRepository.findAll();
                        objectOutputStream.writeObject(all);

//                    BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//                    objectOutputStream.writeObject("파일을 저장할 절대 경로를 입력해주세요 : ");
//                    objectOutputStream.flush();
//
//                    String path;
//                    while (bufferReader.readLine() == null) {
//                        path = bufferReader.readLine();
//                        System.out.println("path : " + path);
//                    }
//
//                    objectOutputStream.writeObject("다운로드 받을 파일의 인덱스를 입력해주세요 : ");
//                    objectOutputStream.flush();
//                    // 파일 인덱스 입력받기
//                    long index = Long.parseLong(bufferReader.readLine());
//                    System.out.println("indxe : " + index);

                        objectOutputStream.flush();
                        printWriter.write("ok");
                        printWriter.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
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
