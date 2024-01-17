package pap.logic.ratings;

import pap.db.dao.RatingDAO;
import pap.db.entities.Rating;


public class HideRating {
    private final Rating rating;

    public HideRating(Rating rating) {
        this.rating = rating;
    }

    /**
     * method allowing to hide rating
     * sets is_hidden = true for given rating in ratings table in database
     * @usage: new HideRating(Rating).hide()
     * @see Rating
     * @see RatingDAO
     * @info useful in owner panel
     */
    public void hide() {
        rating.setHidden(true);
        new RatingDAO().update(rating);
    }
}
