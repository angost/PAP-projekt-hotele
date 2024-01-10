package pap.logic.archiving;

import pap.db.dao.HotelDAO;
import pap.db.dao.OfferDAO;
import pap.db.entities.Hotel;
import pap.db.entities.Offer;

import java.util.List;

public class ArchiveHotel {
    private final Hotel hotel;
    public ArchiveHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean archive() {
        // checking
        hotel.setActive(false);
        new HotelDAO().update(hotel);
        List<Offer> offers = new OfferDAO().findByHotelId(hotel.getHotelId());
        for (var offer : offers) {
            offer.setActive(false);
            new OfferDAO().update(offer);
        }
        return true;
    }
}
