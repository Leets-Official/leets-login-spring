package leets.attendance.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "firstWeek")
    private boolean firstWeek;

    @Column(name = "secondWeek")
    private boolean secondWeek;

    @Column(name = "thirdWeek")
    private boolean thirdWeek;

    @Column(name = "fourthWeek")
    private boolean fourthWeek;

    @Column(name = "fifthWeek")
    private boolean fifthWeek;

    @Column(name = "sixthWeek")
    private boolean sixthWeek;

    @Column(name = "seventhWeek")
    private boolean seventhWeek;

    @Column(name = "eighthWeek")
    private boolean eighthWeek;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFirstWeek() {
        return firstWeek;
    }

    public void setFirstWeek(boolean firstWeek) {
        this.firstWeek = firstWeek;
    }

    public boolean isSecondWeek() {
        return secondWeek;
    }

    public void setSecondWeek(boolean secondWeek) {
        this.secondWeek = secondWeek;
    }

    public boolean isThirdWeek() {
        return thirdWeek;
    }

    public void setThirdWeek(boolean thirdWeek) {
        this.thirdWeek = thirdWeek;
    }

    public boolean isFourthWeek() {
        return fourthWeek;
    }

    public void setFourthWeek(boolean fourthWeek) {
        this.fourthWeek = fourthWeek;
    }

    public boolean isFifthWeek() {
        return fifthWeek;
    }

    public void setFifthWeek(boolean fifthWeek) {
        this.fifthWeek = fifthWeek;
    }

    public boolean isSixthWeek() {
        return sixthWeek;
    }

    public void setSixthWeek(boolean sixthWeek) {
        this.sixthWeek = sixthWeek;
    }

    public boolean isSeventhWeek() {
        return seventhWeek;
    }

    public void setSeventhWeek(boolean seventhWeek) {
        this.seventhWeek = seventhWeek;
    }

    public boolean isEighthWeek() {
        return eighthWeek;
    }

    public void setEighthWeek(boolean eighthWeek) {
        this.eighthWeek = eighthWeek;
    }
}
