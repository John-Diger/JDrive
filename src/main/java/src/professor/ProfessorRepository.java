package src.professor;

import src.ResponseAllListForm;

import java.io.IOException;

public interface ProfessorRepository {
    void insert(Object file) throws IOException;

    ResponseAllListForm findAll();

    ResponseAllListForm findById(long id);
}
