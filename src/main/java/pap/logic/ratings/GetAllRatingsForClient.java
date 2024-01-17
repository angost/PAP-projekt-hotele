package pap.logic.ratings;

import pap.db.dao.HotelDAO;
import pap.db.dao.RatingDAO;
import pap.db.entities.Client;
import pap.db.entities.Hotel;
import pap.db.entities.Rating;

import java.util.List;

public class GetAllRatingsForClient {
    private Client client;
    public GetAllRatingsForClient(Client client) {
        this.client = client;
    }

    /**
     * method getting all ratings for given client,
     * @usage: new GetAllRatingsForClient(Client).getAllRatings()
     * @see Rating
     * @see RatingDAO
     * @see Client
     * @see pap.db.dao.ClientDAO
     * @return returns list of ratings
     */
    public List<Rating> getAllRatings() {
        return new RatingDAO().getRatingsForClient(client.getClientId());
    }
}
