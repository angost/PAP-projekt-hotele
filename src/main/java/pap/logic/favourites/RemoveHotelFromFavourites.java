package pap.logic.favourites;

import pap.db.dao.ClientDAO;
import pap.db.dao.FavouriteHotelDAO;
import pap.db.dao.HotelDAO;
import pap.db.entities.Client;
import pap.db.entities.FavouriteHotel;
import pap.db.entities.Hotel;

public class RemoveHotelFromFavourites {
    private FavouriteHotel fo = new FavouriteHotel();

    public RemoveHotelFromFavourites(Integer hotelId, Integer clientId) {
        this.fo.setHotel(new HotelDAO().findById(hotelId));
        this.fo.setClient(new ClientDAO().findById(clientId));
    }

    /**
     * method allowing removing hotel from favourites for user,
     * deletes row from favourite_hotels table
     * @usage: new RemoveHotelFromFavourites(hotelId, clientId)
     * @see Hotel
     * @see Client
     * @see FavouriteHotel
     * @see HotelDAO
     * @see ClientDAO
     * @see FavouriteHotelDAO
     */
    public void remove() {
        new FavouriteHotelDAO().delete(fo);
    }
}
