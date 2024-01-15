package pap.logic.favourites;

import pap.db.dao.ClientDAO;
import pap.db.dao.FavouriteOfferDAO;
import pap.db.dao.OfferDAO;
import pap.db.entities.FavouriteOffer;

import java.util.List;

public class RemoveOfferFromFavourites {
    private FavouriteOffer fo = new FavouriteOffer();

    public RemoveOfferFromFavourites(Integer offerId, Integer clientId) {
        List<FavouriteOffer> favourites = new FavouriteOfferDAO().findAllClientFavourite(clientId);
        for (FavouriteOffer fav : favourites) {
            if (fav.getOffer().getOfferId() == offerId) {
                this.fo = fav;
                break;
            }
        }

        this.fo.setClient(new ClientDAO().findById(clientId));
        this.fo.setOffer(new OfferDAO().findById(offerId));
    }

    public void remove() {
        new FavouriteOfferDAO().delete(fo);
    }
}
