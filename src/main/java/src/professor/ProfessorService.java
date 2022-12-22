package src.professor;

import src.Method;
import src.RequestForm;
import src.ResponseSpecificContentForm;
import src.ioagent.InputAgent;
import src.ioagent.OutputAgent;
import src.professor.entity.Bucket;
import src.student.StudentClient;

import java.io.*;
import java.net.Socket;

public class ProfessorService {

    private final InputAgent inputAgent;
    private final OutputAgent outputAgent;
    private final ProfessorRepository professorRepository;
    private ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private Socket clientSocket;
    private StudentClient studentClient;

    public ProfessorService(InputAgent inputAgent, OutputAgent outputAgent, ProfessorRepository professorRepository) {
        this.inputAgent = inputAgent;
        this.outputAgent = outputAgent;
        this.professorRepository = professorRepository;
    }

    public void executeDownload(String requestPath, long id) throws Exception {
        ResponseSpecificContentForm byId = professorRepository.findById(id);
        Bucket bucket = byId.getBucket();
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(bucket.getSharedFile());
            byteArrayOutputStream.flush();

            byteArrayOutputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
