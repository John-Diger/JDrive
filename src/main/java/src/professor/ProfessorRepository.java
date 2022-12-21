package src.professor;

import src.ResponseForm;
import src.professor.entity.Bucket;

import java.io.IOException;

public interface ProfessorRepository {
    void insert(Object file) throws IOException;

    ResponseForm findAll();

    Bucket findById(long id);
}
