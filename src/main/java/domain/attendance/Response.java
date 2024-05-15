package domain.attendance;
import java.lang.reflect.Member;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class Response {
    private String name;
    private Part part;
    private LocalDate date;
    public static Response of(Member member, LocalDate date) {
        return Response.builder()
                .name(member.getName())
                .part(member.getPart())
                .date(date)
                .build();
    }
}