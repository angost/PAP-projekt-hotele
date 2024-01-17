package pap.logic.archiving;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.dao.OfferDAO;
import pap.db.entities.Offer;
import pap.db.entities.Reservation;

import java.util.List;

public class ArchiveOffer {
    SessionFactory factory = SessionFactoryMaker.getFactory();
    private final Offer offer;
    public ArchiveOffer(Offer offer) {
        this.offer = offer;
    }

    /**
     * method archives given offer,
     * changes offer status -> is_active = false
     * @usage: new ArchiveOffer(Offer).archive()
     * @see Offer
     * @see OfferDAO
     * @return bool representing success of the operation
     * @info (if there are some reservations in given offer, it can't be archived
     */
    public boolean archive() {
        // checking
        try (Session session = factory.openSession()) {
            List<Reservation> reservationList = session.createNativeQuery("select * from reservations where LOWER(status) = 'active' and offer_id = " + offer.getOfferId(), Reservation.class).list();
            if (!reservationList.isEmpty()) {
                return false;
            }
        }
        offer.setActive(false);
        new OfferDAO().update(offer);
        return true;
    }
}
