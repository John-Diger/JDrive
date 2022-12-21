package src.professor;

import src.professor.entity.Bucket;

import java.io.IOException;
import java.util.List;

public interface ProfessorRepository {
    void insert(Object file) throws IOException;

    List<Bucket> findAll();

    Bucket findById(long id);
}
