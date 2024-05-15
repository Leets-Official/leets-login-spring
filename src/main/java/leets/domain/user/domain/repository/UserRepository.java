package leets.domain.user.domain.repository;

import leets.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByJoinId(String joinId);

    Optional<User> findByJoinId(String joinId);
}
