package src.student;

import src.ExtractedContent;
import src.Method;
import src.RequestForm;
import src.ResponseAllListForm;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    Socket clientSocket;

    PrintWriter printWriter; // 값을 전달할때 사용

    public void connect() {
        connectServerSocket();
    }

    public void downloadProcess() {

    }

    public ResponseAllListForm readFileListInServer() {
        try {
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            // Client 로부터 객체를 읽어오는 역할을 하는 객체를 생성

            RequestForm requestForm = new RequestForm(Method.GET_LIST);
            objectOutputStream.writeObject(requestForm); // 데이터 직렬화
            objectOutputStream.flush(); // 직렬화된 데이터 전달

            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            List<ExtractedContent> list = new ArrayList<>();
            while (true) {
                try {
                    ExtractedContent content = (ExtractedContent)objectInputStream.readObject();
                    list.add(content);
                } catch (EOFException e) {
                    break;
                }
            }
            // printWriter.write("ok");
            // printWriter.close(); // close() or flush()를 해줘야지 전해진다
           // 여기서 socket 접속이 끊어져야 클라이언트가 종료가 됩니다.

            ResponseAllListForm responseAllListForm = new ResponseAllListForm();
            responseAllListForm.setExtractedContents(list);
            clientSocket.close();
            objectInputStream.close();
            objectOutputStream.close();
            return responseAllListForm;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void uploadProcess(String path) {
        File file = readFile(path);
        sendFileToServer(file);
    }

    private void connectServerSocket() {
        InetAddress inetAddress = null;
        try {
            inetAddress = Inet4Address.getByName("127.0.0.1");
            clientSocket = new Socket(inetAddress, 8080);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendFileToServer(File file) {
        try {
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            RequestForm requestForm = new RequestForm(Method.UPLOAD, file);
            objectOutputStream.writeObject(requestForm); // 데이터 직렬화
            objectOutputStream.flush(); // 직렬화된 데이터 전달

            // 서버로부터 메시지 받기 위한 부분
            String returnMessage = bufferReader.readLine();
            System.out.println("returnMessage : " + returnMessage);

            objectOutputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File readFile(String path) {
        return new File(path);
    }

}
