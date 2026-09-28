package fu.talenthub.candidate.repository;


import fu.talenthub.candidate.entity.University;

import java.util.List;

public interface UniversityRepository {
    List<University> findAll();
}
