package fu.talenthub.auth.repository;

import fu.talenthub.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>

{
    Optional<Role> findByRoleName(String roleName);

    @Query("FROM Role r JOIN FETCH r.users u WHERE u.id = :userId")
    List<Role> findAll(@Param("userId") Long userId);
}
