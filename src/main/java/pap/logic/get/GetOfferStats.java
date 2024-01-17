package pap.logic.get;

import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.dao.RatingDAO;
import pap.db.entities.Hotel;
import pap.db.entities.Offer;

public class GetOfferStats {
    private SessionFactory factory = SessionFactoryMaker.getFactory();
    private final Offer offer;
    public GetOfferStats(Offer offer) {
        this.offer = offer;
    }

    /**
     * method gets average rating for given offer,
     * @usage: new GetOfferStats(Offer).getAverageHotelRating()
     * @see Offer
     * @see pap.db.dao.OfferDAO
     * @see pap.db.entities.Rating
     * @see pap.db.dao.RatingDAO
     * @return returns float value of average offer rating
     */
    public Float getAverageRating() {
        return new RatingDAO().getAverageRatingForOffer(offer.getOfferId());
    }

    /**
     * method gets number of ratings for given offer,
     * @usage: new GetOfferStats(Offer).getAverageHotelRating()
     * @see Offer
     * @see pap.db.dao.OfferDAO
     * @see pap.db.entities.Rating
     * @see pap.db.dao.RatingDAO
     * @return returns float value of average offer rating
     */
    public Integer getNumberOfRatingsForOffer() {
        return new RatingDAO().getNumberOfRatingsForOffer(offer.getOfferId());
    }
}
