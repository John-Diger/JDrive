package src.professor;

import src.professor.entity.Bucket;

import java.util.List;

public interface ProfessorRepository {
    void insert(Bucket bucket);

    List<Bucket> findAll();

    Bucket findById(long id);
}
