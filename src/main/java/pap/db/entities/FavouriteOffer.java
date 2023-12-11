package pap.db.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "favourite_offers")
public class FavouriteOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favourite_offer_id", nullable = false)
    private int favouriteOfferId;

    @Basic
    @Column(name = "client_id", nullable = false)
    private int clientId;

    @Basic
    @Column(name = "offer_id", nullable = false)
    private int offerId;
}
