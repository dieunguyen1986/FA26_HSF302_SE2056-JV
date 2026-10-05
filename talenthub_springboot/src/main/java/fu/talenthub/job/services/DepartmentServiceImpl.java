package fu.talenthub.job.services;

import fu.talenthub.job.dto.DepartmentResponse;
import fu.talenthub.job.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll().stream().map((dept) -> {
            return new DepartmentResponse(dept.getId(), dept.getDepartmentName());
        }).toList();
    }
}
