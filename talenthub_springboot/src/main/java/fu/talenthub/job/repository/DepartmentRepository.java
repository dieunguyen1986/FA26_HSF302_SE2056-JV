package fu.talenthub.job.repository;

import fu.talenthub.job.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // JPQL

    @Query("""
        FROM Department d WHERE LOWER(d.departmentName) 
        LIKE LOWER(:keyword) OR LOWER(d.description) LIKE LOWER(:keyword) 
       """)
    List<Department> searchDept(@Param("keyword") String keyword);


    // Query method

    List<Department> findByDepartmentNameContainingIgnoreCase(@Param("keyword") String keyword);
    // Firstname
}
