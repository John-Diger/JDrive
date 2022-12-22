package src.student;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import src.ExtractedContent;
import src.Method;
import src.RequestForm;
import src.ResponseAllListForm;
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
            studentClient.readFilesInformation();

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
        studentService.connect();
        studentService.uploadProcess(absolutePath);
    }

    private void readFilesInformation() {
        studentService.connect();
        ResponseAllListForm responseAllListForm = studentService.readFileListInServer();
        outputAgent.printSharedFolder(responseAllListForm);
    }

    private void download() {
        outputAgent.printDownloadGuide();
        String absolutePath = inputAgent.execute();
        outputAgent.printInputGuideRequestFile();
        String input = inputAgent.execute();
        Long index = inputValidator.convertInputToLong(input);
        studentService.downloadProcess(absolutePath, index);

    }
}
