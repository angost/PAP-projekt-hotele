package pap.logic.get;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.Hotel;
import pap.db.entities.Offer;
import pap.db.entities.Owner;

import java.util.List;

public class GetAllOwnerOffers {
    private SessionFactory factory = SessionFactoryMaker.getFactory();
    private Owner owner;

    public GetAllOwnerOffers(Owner owner) {
        this.owner = owner;
    }

    /**
     * method gets all owner offers from database,
     * @usage: new GetAllOwnerOffers(Owner).get()
     * @see Offer
     * @see Owner
     * @see pap.db.dao.OfferDAO
     * @see pap.db.dao.OwnerDAO
     * @return returns list with owner's hotels
     */
    public List<Offer> get() {
        List<Offer> offers;
        String query = "select offers.* from hotels join offers on (hotels.hotel_id = offers.hotel_id) where owner_id = '" + owner.getOwnerId() + "' and offers.is_active = true";
        try (Session session = factory.openSession()) {
            offers = session.createNativeQuery(query, Offer.class).list();
        }
        return offers;
    }
}
