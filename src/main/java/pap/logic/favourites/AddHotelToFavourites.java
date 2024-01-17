package pap.logic.favourites;

import pap.db.dao.ClientDAO;
import pap.db.dao.FavouriteHotelDAO;
import pap.db.dao.HotelDAO;
import pap.db.entities.Client;
import pap.db.entities.FavouriteHotel;
import pap.db.entities.Hotel;

public class AddHotelToFavourites {
    private FavouriteHotel fo = new FavouriteHotel();

    public AddHotelToFavourites(Integer hotelId, Integer clientId) {
        this.fo.setHotel(new HotelDAO().findById(hotelId));
        this.fo.setClient(new ClientDAO().findById(clientId));
    }

    /**
     * method allowing adding hotel to favourites for user,
     * inserts row into favourite_hotels table
     * @usage: new AddHotelToFavourites(hotelId, clientId)
     * @see Hotel
     * @see Client
     * @see FavouriteHotel
     * @see HotelDAO
     * @see ClientDAO
     * @see FavouriteHotelDAO
     */
    public void insert() {
        new FavouriteHotelDAO().create(fo);
    }
}
