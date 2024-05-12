package leets.attendance.domain.attendance.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import leets.attendance.domain.member.entity.Member;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Attendance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "member_id")
  private Member member;

  private Integer week;

  private LocalDate date;

  @Enumerated(EnumType.STRING)
  private AttendanceStatus attendanceStatus = AttendanceStatus.NONE;

  public Attendance(Member member, Integer week, LocalDate date) {
    this.member = member;
    this.week = week;
    this.date = date;
  }
}