package leets.attendance.dto;

import leets.attendance.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String username;
    private String name;
    private String partName;

    public UserResponseDTO(Long id, String username, String name, String partName) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.partName = partName;
    }

    public static UserResponseDTO of(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getName(), user.getPartName());
    }
}