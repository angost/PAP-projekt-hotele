package pap.logic.favourites;

import pap.db.dao.*;
import pap.db.entities.*;

public class AddOfferToFavourites {
    private FavouriteOffer fo = new FavouriteOffer();

    public AddOfferToFavourites(Integer offerId, Integer clientId) {
        this.fo.setClient(new ClientDAO().findById(clientId));
        this.fo.setOffer(new OfferDAO().findById(offerId));
    }

    /**
     * method allowing adding offer to favourites for user,
     * inserts row into favourite_offers table
     * @usage: new AddOfferToFavourites(offerId, clientId)
     * @see Offer
     * @see Client
     * @see FavouriteOffer
     * @see OfferDAO
     * @see ClientDAO
     * @see FavouriteOfferDAO
     */
    public void insert() {
        new FavouriteOfferDAO().create(fo);
    }
}
