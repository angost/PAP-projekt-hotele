package pap.logic.get;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.dao.ClientDAO;
import pap.db.dao.FavouriteOfferDAO;
import pap.db.dao.OfferDAO;
import pap.db.entities.*;

import java.util.List;

public class GetAllOwnerHotels {
    private SessionFactory factory = SessionFactoryMaker.getFactory();
    private Owner owner;

    public GetAllOwnerHotels(Owner owner) {
        this.owner = owner;
    }

    /**
     * method gets all owner hotels from database,
     * @usage: new GetAllOwnerHotels(Owner).get()
     * @see Hotel
     * @see Owner
     * @see pap.db.dao.HotelDAO
     * @see pap.db.dao.OwnerDAO
     * @return returns list with owner's hotels
     */
    public List<Hotel> get() {
        List <Hotel> hotels;
        String query = "select * from hotels where owner_id = '" + owner.getOwnerId() + "' and is_active = true";
        try (Session session = factory.openSession()) {
            hotels = session.createNativeQuery(query, Hotel.class).list();
        }
        return hotels;
    }
}
