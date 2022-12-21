package src.professor;

import src.ioagent.InputAgent;
import src.ioagent.OutputAgent;

public class ProfessorServer {

    private final InputAgent inputAgent;
    private final OutputAgent outputAgent;
    private final ProfessorRepository professorRepository;

    public ProfessorServer(InputAgent inputAgent, OutputAgent outputAgent, ProfessorRepository professorRepository) {
        this.inputAgent = inputAgent;
        this.outputAgent = outputAgent;
        this.professorRepository = professorRepository;
    }

    // 교수님은 이 메서드를 실행시켜놓으면 학생들의 소켓접근을 허용하고
    // 학생들의 요청/응답에 따라 알맞는 처리를 해준다.
    public static void main(String[] args) {

    }


}
