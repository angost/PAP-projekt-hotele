package pap.logic.favourites;

import pap.db.entities.Client;
import pap.db.entities.FavouriteOffer;
import pap.db.entities.Offer;

public class AddOfferToFavourites {
    private FavouriteOffer fo = new FavouriteOffer();

    public AddOfferToFavourites(Offer offer, Client client) {
        this.fo.setClient(client);
        this.fo.setOffer(offer);
    }

    public static void insert() {

    }
}
