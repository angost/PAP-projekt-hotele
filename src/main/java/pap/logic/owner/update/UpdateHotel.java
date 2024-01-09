package pap.logic.owner.update;

import pap.db.dao.HotelDAO;
import pap.db.entities.Hotel;

public class UpdateHotel {
    private final Hotel hotel;

    public UpdateHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void update() {
        new HotelDAO().update(hotel);
    }
}
