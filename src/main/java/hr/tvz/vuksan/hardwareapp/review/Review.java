package hr.tvz.vuksan.hardwareapp.review;

import hr.tvz.vuksan.hardwareapp.hardware.Hardware;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="REVIEW")
public class Review implements Serializable {

    @Id
    @Column(name="CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="RATING")
    private int rating;

    @ManyToOne
    @JoinColumn(name="HARDWAREID")
    private Hardware hardware;

    public Review() {
    }

    public Review(long id, String title, String description, int rating, Hardware hardware) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.hardware = hardware;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public Hardware getHardware() {
        return hardware;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return getId() == review.getId() && getRating() == review.getRating() &&
                Objects.equals(getTitle(), review.getTitle()) && Objects.equals(getDescription(), review.getDescription())
                && Objects.equals(getHardware(), review.getHardware());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getRating(), getHardware());
    }
}
