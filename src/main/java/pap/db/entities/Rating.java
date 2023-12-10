package pap.db.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", nullable = false)
    private int ratingId;

    @Basic
    @Column(name = "offer_id", nullable = false)
    private int reservationId;

    @Basic
    @Column(name = "client_id", nullable = false)
    private int clientId;

    @Basic
    @Column(name = "rating", nullable = false)
    private int rating;

    @Basic
    @Column(name = "comment", nullable = false)
    private String comment;

    @Basic
    @Column(name = "add_date", nullable = false)
    private LocalDate addDate;

    @Basic
    @Column(name = "is_hidden", nullable = false)
    private boolean isHidden;
}
