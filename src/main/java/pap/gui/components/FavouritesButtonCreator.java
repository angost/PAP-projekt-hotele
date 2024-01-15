package pap.gui.components;

import pap.db.dao.FavouriteOfferDAO;
import pap.logic.favourites.AddOfferToFavourites;
import pap.logic.favourites.RemoveOfferFromFavourites;

public class FavouritesButtonCreator {

    public static FavouritesButton createFavouritesButton(int buttonSize, int imgSize, int elementId, int userId) {
        FavouritesButton favouritesButton = new FavouritesButton(buttonSize, imgSize, elementId);
        if (new FavouriteOfferDAO().isOfferInClientFavourites(userId, elementId)) {
            favouritesButton.changeState();
        }
        return favouritesButton;
    }

    public static void favouritesBtnClicked(FavouritesButton favouritesButton, int userId) {
        if (favouritesButton.state.equals("base_state")) {
            new AddOfferToFavourites(favouritesButton.elementId, userId).insert();
        } else {
            new RemoveOfferFromFavourites(favouritesButton.elementId, userId).remove();
        }
        favouritesButton.changeState();
    }
}
