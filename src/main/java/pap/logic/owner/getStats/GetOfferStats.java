package pap.logic.owner.getStats;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.dao.RatingDAO;
import pap.db.entities.Offer;

public class GetOfferStats {
    private SessionFactory factory = SessionFactoryMaker.getFactory();
    private final Offer offer;
    public GetOfferStats(Offer offer) {
        this.offer = offer;
    }

    public Float getAverageRating() {
        return new RatingDAO().getAverageRatingForOffer(offer.getOfferId());
    }

    public Integer getNumberOfRatingsForOffer() {
        return new RatingDAO().getNumberOfRatingsForOffer(offer.getOfferId());
    }

    public static void main(String[] args) {
        Offer offer = new Offer();
        offer.setOfferId(3);
        Float value = new GetOfferStats(offer).getAverageRating();
        System.out.println(value);
    }
}