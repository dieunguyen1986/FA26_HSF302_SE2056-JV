package candidate.repository;

import candidate.entity.University;

import java.util.List;

public interface UniversityRepository {
    List<University> findAll();
}
