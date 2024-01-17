package pap.logic.get;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.Hotel;
import pap.db.entities.Owner;

public class GetHotelStats {
    private final SessionFactory factory = SessionFactoryMaker.getFactory();
    private final Hotel hotel;

    public GetHotelStats(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * method gets average rating for given hotel,
     * @usage: new GetHotelStats(Hotel).getAverageHotelRating()
     * @see Hotel
     * @see pap.db.dao.HotelDAO
     * @see pap.db.entities.Rating
     * @see pap.db.dao.RatingDAO
     * @return returns float value of average hotel rating
     */
    public Float getAverageHotelRating() {
        String query = "SELECT AVG(r.rating) FROM ratings r JOIN offers o ON r.offer_id = o.offer_id JOIN hotels h ON o.hotel_id = h.hotel_id WHERE h.hotel_id = :hotelId";
        Float value = null;
        try (Session session = factory.openSession()) {
            Object result = session.createNativeQuery(query)
                    .setParameter("hotelId", hotel.getHotelId())
                    .getSingleResult();
            if (result != null) {
                value = ((Number) result).floatValue();
            }
        } catch (NoResultException e) {
            value = null;
        }
        return value;
    }

    /**
     * method gets number of ratings for given hotel,
     * @usage: new GetHotelStats(Hotel).getNumberOfRatingsForHotel()
     * @see Hotel
     * @see pap.db.dao.HotelDAO
     * @see pap.db.entities.Rating
     * @see pap.db.dao.RatingDAO
     * @return returns integer value of number of hotel's ratings
     */
    public Integer getNumberOfRatingsForHotel() {
        String query = "SELECT COUNT(r.rating) FROM ratings r JOIN offers o ON r.offer_id = o.offer_id JOIN hotels h ON o.hotel_id = h.hotel_id WHERE h.hotel_id = :hotelId";
        Integer value = null;
        try (Session session = factory.openSession()) {
            Object result = session.createNativeQuery(query)
                    .setParameter("hotelId", hotel.getHotelId())
                    .getSingleResult();
            if (result != null) {
                value = ((Number) result).intValue();
            }
        } catch (NoResultException e) {
            value = null;
        }
        return value;
    }
}
