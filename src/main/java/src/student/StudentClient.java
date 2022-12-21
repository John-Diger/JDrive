package src.student;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class StudentClient {
    // 학생들은 이 메서드를 실행시켜 교수님과 소켓 연결을 하고
    // 취하고 싶은 행위를 선택한다.

    Socket clientSocket;

    public static void main(String[] args) {
        StudentClient studentClient = new StudentClient();
        studentClient.connectServerSocket();
    }

    public void connectServerSocket() {
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
}
