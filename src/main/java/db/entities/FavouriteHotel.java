package db.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "favourite_hotels")
public class FavouriteHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favourite_hotel_id", nullable = false)
    private int favouriteHotelId;

    @Basic
    @Column(name = "client_id", nullable = false)
    private int clientId;

    @Basic
    @Column(name = "hotel_id", nullable = false)
    private int hotelId;
}
