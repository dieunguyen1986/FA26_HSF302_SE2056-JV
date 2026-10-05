package fu.talenthub.job.services;


import fu.talenthub.job.dto.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> findAll();
}
