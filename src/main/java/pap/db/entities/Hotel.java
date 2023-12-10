package pap.db.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id", nullable = false)
    private int hotelId;

    @Basic
    @Column(name = "owner_id", nullable = false)
    private int ownerId;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "add_date", nullable = false)
    private LocalDate addDate;

    @Basic
    @Column(name = "description", nullable = false)
    private String description;

    @Basic
    @Column(name = "address_id", nullable = false)
    private int addressId;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

    @Basic
    @Column(name = "website", nullable = false)
    private String website;

    @Basic
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Basic
    @Column(name = "bank_account_number", nullable = false)
    private String bankAccountNumber;

    @Basic
    @Column(name = "is_active", nullable = false)
    private int isActive;
}
