package leets.attendance.repository;

import leets.attendance.domain.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email); // 네임드 쿼리

    @Override
    ArrayList<User> findAll();

}