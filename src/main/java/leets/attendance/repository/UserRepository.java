package leets.attendance.repository;

import leets.attendance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //userid로 회원이 있는지 T/F 반환
    Boolean existsByUserid(String userid);

    //userid로 회원 검색
    User findByUserid(String userid);
}
