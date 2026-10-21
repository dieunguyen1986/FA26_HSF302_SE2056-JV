package fu.talenthub.job.repository;

import fu.talenthub.job.entity.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @EntityGraph(attributePaths = "jobs")
    @Query("SELECT d FROM Department d")
    List<Department> findAllWithJobs();
}
