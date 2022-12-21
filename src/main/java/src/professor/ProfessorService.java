package src.professor;

import src.ioagent.InputAgent;
import src.ioagent.OutputAgent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfessorService {

    private final InputAgent inputAgent;
    private final OutputAgent outputAgent;
    private final ProfessorRepository professorRepository;

    public ProfessorService(InputAgent inputAgent, OutputAgent outputAgent, ProfessorRepository professorRepository) {
        this.inputAgent = inputAgent;
        this.outputAgent = outputAgent;
        this.professorRepository = professorRepository;
    }

//    public File executeDownload(long id) {
//        FileOutputStream fos = new FileOutputStream("pathname")
//        try (FileOutputStream fos = new FileOutputStream("pathname")) {
//            fos.write(professorRepository.findById(id).getSharedFile());
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return fos;
//    }
}
