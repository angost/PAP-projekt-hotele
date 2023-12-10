package pap.db.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false)
    private int reservationId;

    @Basic
    @Column(name = "offer_id", nullable = false)
    private int offerId;

    @Basic
    @Column(name = "client_id", nullable = false)
    private int clientId;

    @Basic
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Basic
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Basic
    @Column(name = "description", nullable = false)
    private String description;

    @Basic
    @Column(name = "paid_amount", nullable = false)
    private float paidAmount;

    @Basic
    @Column(name = "status", nullable = false)
    private String status;

    @Basic
    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;
}
