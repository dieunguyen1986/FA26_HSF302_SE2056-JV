package fu.talenthub.candidate.repository;

import fu.talenthub.candidate.entity.Candidate;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // IoC Container quan ly
@RequiredArgsConstructor
public class CandidateRepositoryImpl implements CandidateRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Candidate save(Candidate candidate) {
        System.out.println(candidate.toString());
        Session session = entityManager.unwrap(Session.class);
        session.persist(candidate);

        return candidate;
    }

    @Override
    public List<Candidate> findAll() {
        Session session = entityManager.unwrap(Session.class);

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
