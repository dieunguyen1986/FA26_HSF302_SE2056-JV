package candidate.repository;

import candidate.entity.Candidate;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository // IoC Container quan ly
@RequiredArgsConstructor
public class CandidateRepositoryImpl implements CandidateRepository {
    private final SessionFactory sessionFactory;

    @Override
    public Candidate save(Candidate candidate) {
        Session session = sessionFactory.getCurrentSession();
        return null;
    }
}
