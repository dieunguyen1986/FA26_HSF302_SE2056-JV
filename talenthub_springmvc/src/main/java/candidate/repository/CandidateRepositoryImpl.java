package candidate.repository;

import candidate.entity.Candidate;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // IoC Container quan ly
@RequiredArgsConstructor
public class CandidateRepositoryImpl implements CandidateRepository {
    private final SessionFactory sessionFactory;

    @Override
    public Candidate save(Candidate candidate) {
        System.out.println(candidate.toString());
        Session session = sessionFactory.getCurrentSession();
        session.persist(candidate);

        return candidate;
    }

    @Override
    public List<Candidate> findAll(){
        Session session = sessionFactory.getCurrentSession();

        List<Candidate> candidates = session
                .createQuery("FROM Candidate c LEFT JOIN FETCH c.university", Candidate.class)
                .getResultList();

        System.out.println("=== DATA FROM DATABASE ===");
        System.out.println("SIZE = " + candidates.size());

        for (Candidate candidate : candidates) {
            System.out.println(
                    candidate.getId() + " - " +
                            candidate.getEmail() + " - " +
                            candidate.getFullName() + " - " +
                            candidate.getStatus() + " - " +
                            (candidate.getUniversity() != null ? candidate.getUniversity().getUnisName() : "No University")
            );
        }

        return candidates;
    }
}
