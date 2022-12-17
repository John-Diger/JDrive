package src.professor;

import src.ioagent.InputAgent;
import src.ioagent.OutputAgent;

public class ProfessorServer {

    private final InputAgent inputAgent;
    private final OutputAgent outputAgent;
    private final ProfessorRepository professorRepository;

    public static void main(String[] args) {

    }

    public ProfessorServer(InputAgent inputAgent, OutputAgent outputAgent, ProfessorRepository professorRepository) {
        this.inputAgent = inputAgent;
        this.outputAgent = outputAgent;
        this.professorRepository = professorRepository;
    }
}
