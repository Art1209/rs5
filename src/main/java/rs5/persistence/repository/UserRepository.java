package rs5.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs5.persistence.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select b from User b where b.login = :login")
    User findByLogin(@Param("login") String login);
}
