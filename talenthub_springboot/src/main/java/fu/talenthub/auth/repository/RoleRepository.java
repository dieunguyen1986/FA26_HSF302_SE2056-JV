package fu.talenthub.auth.repository;

import fu.talenthub.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>

{
    @Query("FROM Role r JOIN FETCH r.users u WHERE u.id = :userId")
    List<Role> findAll(@Param("userId") Long userId);
}
