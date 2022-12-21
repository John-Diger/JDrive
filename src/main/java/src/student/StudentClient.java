package src.student;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Optional;

import src.Method;
import src.ioagent.InputAgent;
import src.ioagent.InputValidator;
import src.ioagent.OutputAgent;

public class StudentClient {
    // 학생들은 이 메서드를 실행시켜 교수님과 소켓 연결을 하고
    // 취하고 싶은 행위를 선택한다.

    InputAgent inputAgent;
    InputValidator inputValidator;
    OutputAgent outputAgent;
    StudentService studentService;

    public StudentClient() {
        this.inputValidator = new InputValidator();
        this.inputAgent = new InputAgent(this.inputValidator);
        this.outputAgent = new OutputAgent();
        this.studentService = new StudentService();
    }

    public static void main(String[] args) {
        StudentClient studentClient = new StudentClient();
        studentClient.outputAgent.printInputGuideDownloadOrUpload();
        String command = studentClient.inputAgent.execute();
        studentClient.inputValidator.userCommand(command);

        Method method = studentClient.getMethod(command);


        if (method.equals(Method.UPLOAD)) {
            studentClient.upload();
            System.out.println("success");
        } else if (method.equals(Method.DOWNLOAD)) {
            studentClient.download();
        }

    }

    private Method getMethod(String command) {
        Optional<Method> optionalMethod = Arrays.stream(Method.values())
            .filter(method -> method.getCommand().equals(command))
            .findFirst();
        if (optionalMethod.isPresent()) {
            return optionalMethod.get();
        }
        throw new IllegalArgumentException();
    }

    private void upload() {
        outputAgent.printUploadGuide();
        String absolutePath = inputAgent.execute();
        studentService.uploadProcess(absolutePath);
    }

    private void download() {
        // outputAgent.printSharedFolder();
        outputAgent.printInputGuideRequestFile();
        String absolutePath = inputAgent.execute();
        studentService.uploadProcess(absolutePath);
    }

}
