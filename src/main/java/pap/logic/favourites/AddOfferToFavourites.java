package pap.logic.favourites;

import pap.db.dao.FavouriteOfferDAO;
import pap.db.entities.Client;
import pap.db.entities.FavouriteHotel;
import pap.db.entities.FavouriteOffer;
import pap.db.entities.Offer;

public class AddOfferToFavourites {
    private FavouriteOffer fo = new FavouriteOffer();

    public AddOfferToFavourites(Offer offer, Client client) {
        this.fo.setClient(client);
        this.fo.setOffer(offer);
    }

    public void insert() {
        new FavouriteOfferDAO().create(fo);
    }
}
