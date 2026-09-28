package fu.talenthub.candidate.services;

import fu.talenthub.candidate.entity.University;
import fu.talenthub.candidate.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
}
