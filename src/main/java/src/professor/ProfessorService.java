package src.professor;

import src.ioagent.InputAgent;
import src.ioagent.OutputAgent;

import java.io.File;

public class ProfessorService {

    private final InputAgent inputAgent;
    private final OutputAgent outputAgent;
    private final ProfessorRepository professorRepository;

    public ProfessorService(InputAgent inputAgent, OutputAgent outputAgent, ProfessorRepository professorRepository) {
        this.inputAgent = inputAgent;
        this.outputAgent = outputAgent;
        this.professorRepository = professorRepository;
    }
//
//    public
//
//    public File executeDownload() {
//
//    }
}
