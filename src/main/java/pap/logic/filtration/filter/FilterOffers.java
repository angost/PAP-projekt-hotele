package pap.logic.filtration.filter;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.dao.ClientDAO;
import pap.db.dao.FavouriteOfferDAO;
import pap.db.dao.OfferDAO;
import pap.db.entities.Client;
import pap.db.entities.FavouriteOffer;
import pap.logic.filtration.prepareStatements.*;
import pap.db.entities.Offer;
import pap.logic.filtration.properties.*;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class FilterOffers {
    private SessionFactory factory = SessionFactoryMaker.getFactory();
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String streetNo;
    private String hotelName;
    private String roomType;
    private String offerName;
    private Integer bathroomNoLowerBound;
    private Integer bathroomNoUpperBound;
    private Integer roomNoLowerBound;
    private Integer roomNoUpperBound;
    private Integer bedNoLowerBound;
    private Integer bedNoUpperBound;
    private Boolean hasKitchen;
    private Boolean petFriendly;
    private Boolean hasWifi;
    private Boolean smokingAllowed;
    private Boolean hasParking;
    private Integer priceLowerBound;
    private Integer priceUpperBound;
    private String ownerCompanyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean onlyFavouriteHotels;
    private boolean onlyFavouriteOffers;
    private boolean onlyPastReservations;
    private Integer clientId = -1;
    private Float minOfferRating = (float) -1;

    public FilterOffers() {}

    /**
     * method allowing filtering offers,
     * @usage: var fo = new FilterOffers()
     * @usage: then ex. fo.setCountry('Poland')
     * @usage: then fo.filter()
     * @see Offer
     * @see Client
     * @see FavouriteOffer
     * @see pap.db.entities.FavouriteHotel
     * @see OfferDAO
     * @see ClientDAO
     * @see FavouriteOfferDAO
     * @see pap.db.dao.FavouriteHotelDAO
     * @return returns list of matching offers
     */
    public List <Offer> filter() {
        List <Offer> offers;
        try (Session session = factory.openSession()) {
             offers = session.createNativeQuery(prepareQuery(), Offer.class).list();
        }
        if (startDate != null && endDate != null) {
            return CheckReservations.checkReservations(offers, startDate, endDate);
        }
        return offers;
    }

    private String prepareQuery() {
        String query = addressQuery() + " intersect " + offerQuery() + " intersect " + hotelQuery() + " intersect " + ownerQuery();
        if (onlyFavouriteHotels && clientId != -1) {
            query += " intersect " + "SELECT offers.* FROM favourite_hotels JOIN hotels ON (favourite_hotels.hotel_id = hotels.hotel_id) JOIN offers ON (hotels.hotel_id = offers.hotel_id) WHERE offers.is_active = true AND favourite_hotels.client_id = '" + clientId + "'";
        }
        if (onlyFavouriteOffers && clientId != -1) {
            query += " intersect " + "SELECT offers.* FROM favourite_offers JOIN offers ON (favourite_offers.offer_id = offers.offer_id) WHERE offers.is_active = true AND favourite_offers.client_id = '" + clientId + "'";
        }
        if (onlyPastReservations && clientId != -1) {
            query += " intersect " + "SELECT offers.* FROM reservations JOIN offers ON (reservations.offer_id = offers.offer_id) WHERE offers.is_active = true AND reservations.client_id = '" + clientId + "'";
        }
        if (minOfferRating != -1) {
            query += " intersect " + "SELECT * FROM offers WHERE offers.is_active = true AND offer_id IN (SELECT offer_id FROM ratings GROUP BY offer_id HAVING AVG(rating) >= '" + minOfferRating + "')";
        }
        return query;
    }

    private String addressQuery() {
        AddressProperties addressProperties = new AddressProperties(country, city, street, postalCode, streetNo);
        PrepareAddressFilter addressFilter = new PrepareAddressFilter(addressProperties);
        return addressFilter.prepareQuery();
    }

    private String hotelQuery() {
        HotelProperties hotelProperties = new HotelProperties(hotelName);
        PrepareHotelFilter hotelFilter = new PrepareHotelFilter(hotelProperties);
        return hotelFilter.prepareQuery();
    }

    private String offerQuery() {
        OfferProperties offerProperties = new OfferProperties(roomType, offerName, bathroomNoLowerBound, bathroomNoUpperBound,
                roomNoLowerBound, roomNoUpperBound, bedNoLowerBound, bedNoUpperBound, hasKitchen, petFriendly, hasWifi, smokingAllowed, hasParking, priceLowerBound, priceUpperBound);
        PrepareOfferFilter offerFilter = new PrepareOfferFilter(offerProperties);
        return offerFilter.prepareQuery();
    }

    private String ownerQuery() {
        OwnerProperties ownerProperties = new OwnerProperties(ownerCompanyName);
        PrepareOwnerFilter ownerFilter = new PrepareOwnerFilter(ownerProperties);
        return ownerFilter.prepareQuery();
    }
}
