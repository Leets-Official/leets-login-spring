package leets.attendance.src.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import leets.attendance.src.dto.UserRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter
@RequiredArgsConstructor
public class User {

    @Id
    private String id;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 리턴 dto는 따로 두지 않을 생각이므로 객체를 리턴할 땐 보안상 이슈로 비밀번호는 리턴 X
    private String pwd;

    @NotNull
    private String name;

    @NotNull
    private String part;    // 파트가 정해져있다면 엔티티나 enum으로 빼고싶지만.. 입력받는거같아서 String으로 설정

    public User(UserRequestDto requestDto) {    // dto를 이용한 생성자
        this.id = requestDto.getId();
        this.pwd = requestDto.getPwd();
        this.name = requestDto.getName();
        this.part = requestDto.getPart();
    }
}
