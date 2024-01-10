package pap.logic.favourites;

import pap.db.dao.FavouriteOfferDAO;
import pap.db.entities.Client;
import pap.db.entities.FavouriteOffer;
import pap.db.entities.Offer;

public class RemoveOfferFromFavourites {
    private FavouriteOffer fo = new FavouriteOffer();

    public RemoveOfferFromFavourites(Offer offer, Client client) {
        this.fo.setClient(client);
        this.fo.setOffer(offer);
    }

    public void remove() {
        new FavouriteOfferDAO().delete(fo);
    }
}
