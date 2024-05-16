package leets.attendance.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Part {
    FE("FE"),
    BE("BE"),
    DESIGN("DE");
    private final String gender;
}
