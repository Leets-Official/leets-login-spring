package domain.User;

import domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepo<User, Long> {

    Boolean existsById(String Id);

    Optional<User> findById(String Id);
}
