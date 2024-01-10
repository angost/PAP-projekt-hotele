package pap.logic.archiving;

import pap.db.dao.OfferDAO;
import pap.db.entities.Offer;

public class ArchiveOffer {
    private final Offer offer;
    public ArchiveOffer(Offer offer) {
        this.offer = offer;
    }

    public boolean archive() {
        // checking
        offer.setActive(false);
        new OfferDAO().update(offer);
        return true;
    }
}
