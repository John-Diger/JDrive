package src.professor;

import src.ResponseAllListForm;
import src.ResponseSpecificContentForm;

import java.io.IOException;

public interface ProfessorRepository {
    void insert(Object file) throws IOException;

    ResponseAllListForm findAll();

    ResponseSpecificContentForm findById(long id) throws Exception;
}
