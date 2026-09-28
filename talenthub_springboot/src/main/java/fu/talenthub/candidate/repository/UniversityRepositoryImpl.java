package fu.talenthub.candidate.repository;

import fu.talenthub.candidate.entity.University;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UniversityRepositoryImpl implements UniversityRepository {
    private final EntityManager entityManager;

    @Override
    public List<University> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM University", University.class).getResultList();
    }
}
