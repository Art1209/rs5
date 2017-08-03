package rs5.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs5.persistence.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

