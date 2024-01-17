package pap.logic.ratings;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.dao.RatingDAO;
import pap.db.entities.Client;
import pap.db.entities.Rating;
import pap.db.entities.Owner;

import java.util.List;

public class GetAllRatingsForOwner {
    private Owner owner;
    SessionFactory factory = SessionFactoryMaker.getFactory();
    public GetAllRatingsForOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * method getting all ratings for given owner's hotels,
     * @usage: new GetAllRatingsForOwner(Owner).getAllRatings()
     * @see Rating
     * @see RatingDAO
     * @see Owner
     * @see pap.db.dao.OwnerDAO
     * @return returns list of ratings
     */
    public List<Rating> getAllRatings() {
        String query = "select ratings.* from hotels join owners on (hotels.owner_id = owners.owner_id) join offers on hotels.hotel_id = offers.hotel_id join ratings on offers.offer_id = ratings.offer_id where hotels.owner_id = '" + owner.getOwnerId() + "'";
        try (Session session = factory.openSession()) {
            return session.createNativeQuery(query, Rating.class).list();
        }
    }
}
