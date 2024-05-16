package leets.attendance.domain.user.domain.repository;

import leets.attendance.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(Long userId);
}
