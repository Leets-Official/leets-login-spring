package leets.attendance.repository;

import leets.attendance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
}