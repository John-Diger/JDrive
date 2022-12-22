package src.student;

import src.Method;
import src.RequestForm;
import src.ResponseAllListForm;
import src.ResponseSpecificContentForm;
import src.professor.entity.Bucket;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StudentService {

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    Socket clientSocket;

    ByteArrayOutputStream byteArrayOutputStream;

    public void connect() {
        connectServerSocket();
    }

    public void downloadProcess(String path, Long index) {
        connect();
        ResponseSpecificContentForm responseSpecificContentForm = downloadByIndex(index);
        Bucket bucket = responseSpecificContentForm.getBucket();
        Path savePath = Paths.get(path + bucket.getName());

        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(bucket.getSharedFile());
            byteArrayOutputStream.flush();
            Files.write(savePath, bucket.getSharedFile());
            byteArrayOutputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseAllListForm readFileListInServer() {
        try {
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            // Client 로부터 객체를 읽어오는 역할을 하는 객체를 생성

            RequestForm requestForm = new RequestForm(Method.GET_LIST);
            objectOutputStream.writeObject(requestForm); // 데이터 직렬화
            objectOutputStream.flush(); // 직렬화된 데이터 전달

            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            // printWriter.write("ok");
            // printWriter.close(); // close() or flush()를 해줘야지 전해진다

            ResponseAllListForm form = (ResponseAllListForm)objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();
            clientSocket.close();
            return form;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public ResponseSpecificContentForm downloadByIndex(Long index) {
        try {
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            // Client 로부터 객체를 읽어오는 역할을 하는 객체를 생성

            RequestForm requestForm = new RequestForm(Method.DOWNLOAD, index);
            objectOutputStream.writeObject(requestForm); // 데이터 직렬화
            objectOutputStream.flush(); // 직렬화된 데이터 전달

            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            ResponseSpecificContentForm form = (ResponseSpecificContentForm)objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();
            clientSocket.close();
            return form;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
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
