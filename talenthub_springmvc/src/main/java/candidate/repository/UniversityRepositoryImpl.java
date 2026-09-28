package candidate.repository;

import candidate.entity.University;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UniversityRepositoryImpl implements UniversityRepository {
    private final SessionFactory sessionFactory;

    @Override
    public List<University> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM University", University.class).getResultList();
    }
}
