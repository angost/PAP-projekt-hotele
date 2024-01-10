package pap.logic.favourites;

import pap.db.dao.FavouriteHotelDAO;
import pap.db.entities.Client;
import pap.db.entities.FavouriteHotel;
import pap.db.entities.Hotel;

public class RemoveHotelFromFavourites {
    private FavouriteHotel fo = new FavouriteHotel();

    public RemoveHotelFromFavourites(Hotel hotel, Client client) {
        this.fo.setHotel(hotel);
        this.fo.setClient(client);
    }

    public void remove() {
        new FavouriteHotelDAO().delete(fo);
    }
}
