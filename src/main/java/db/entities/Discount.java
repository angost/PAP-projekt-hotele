package db.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id", nullable = false)
    private int discountId;

    @Basic
    @Column(name = "code", nullable = false)
    private String code;

    @Basic
    @Column(name = "value_type", nullable = false)
    private int valueType;

    @Basic
    @Column(name = "type", nullable = false)
    private int type;

    @Basic
    @Column(name = "description", nullable = false)
    private String description;

    @Basic
    @Column(name = "value", nullable = false)
    private float value;

    @Basic
    @Column(name = "hotel_id", nullable = false)
    private int hotelId;

    @Basic
    @Column(name = "is_active", nullable = false)
    private int isActive;
}