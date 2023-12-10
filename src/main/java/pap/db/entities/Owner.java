package pap.db.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id", nullable = false)
    private int ownerId;

    @Basic
    @Column(name = "username", nullable = false)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

    @Basic
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Basic
    @Column(name = "address_id", nullable = false)
    private int addressId;

    @Basic
    @Column(name = "nip", nullable = false)
    private String nip;

    @Basic
    @Column(name = "is_verified", nullable = false)
    private int isVerified;

    @Basic
    @Column(name = "is_active", nullable = false)
    private int isActive;
}
