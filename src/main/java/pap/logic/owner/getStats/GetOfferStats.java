package pap.logic.owner.getStats;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.Offer;

public class GetOfferStats {
    private SessionFactory factory = SessionFactoryMaker.getFactory();
    private final Offer offer;
    public GetOfferStats(Offer offer) {
        this.offer = offer;
    }

    public Float getAverageRating() {
        String query = "SELECT AVG(rating) FROM ratings WHERE offer_id = :offerId";
        Float value = null;
        try (Session session = factory.openSession()) {
            Object result = session.createNativeQuery(query)
                    .setParameter("offerId", offer.getOfferId())
                    .getSingleResult();
            if (result != null) {
                value = ((Number) result).floatValue();
            }
        } catch (NoResultException e) {
            value = null;
        }
        return value;
    }
}
