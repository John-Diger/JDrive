package src.student;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import src.Method;
import src.RequestForm;
import src.student.model.ReadFilesForm;

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

	public RequestForm readFileListInServer() {
		try {
			objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			// Client 로부터 객체를 읽어오는 역활을 하는 객체를 생성

			RequestForm requestForm = new RequestForm(Method.DOWNLOAD);
			objectOutputStream.writeObject(requestForm); // 데이터 직렬화
			objectOutputStream.flush(); // 직렬화된 데이터 전달

			objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
			RequestForm responseForm = (RequestForm)objectInputStream.readObject(); // readObject는 object 객체로 불러오기 때문에 형변화해야 합니다.
			System.out.println(responseForm.toString());

			printWriter.write("ok");
			printWriter.close(); // close() or flush()를 해줘야지 전해진다
			clientSocket.close(); // 여기서 socket 접속이 끊어져야 클라이언트가 종료가 됩니다.

			return responseForm;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
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
