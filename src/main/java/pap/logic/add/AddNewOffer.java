package pap.logic.add;

import pap.db.dao.OfferDAO;
import pap.db.entities.Hotel;
import pap.db.entities.Offer;

import java.time.LocalDate;

public class AddNewOffer {
    private final Offer offer;
    public AddNewOffer(String roomType, String name, LocalDate addDate, String description,
                       Integer bathroomNo, Integer roomNo, Integer bedNo, boolean hasKitchen, boolean petFriendly,
                       boolean hasWifi, boolean smokingAllowed, boolean hasParking,
                       Float price, boolean isActive, Hotel hotel) {
        this.offer = new Offer();
        this.offer.setRoomType(roomType);
        this.offer.setName(name);
        this.offer.setAddDate(addDate);
        this.offer.setDescription(description);
        this.offer.setBathroomNumber(bathroomNo);
        this.offer.setRoomNumber(roomNo);
        this.offer.setBedNumber(bedNo);
        this.offer.setHasKitchen(hasKitchen);
        this.offer.setPetFriendly(petFriendly);
        this.offer.setHasWifi(hasWifi);
        this.offer.setSmokingAllowed(smokingAllowed);
        this.offer.setHasParking(hasParking);
        this.offer.setPrice(price);
        this.offer.setActive(isActive);
        this.offer.setHotel(hotel);
        this.offer.setImageData(null);
    }

    /**
     * method inserts new offer into database,
     * creates new row in offers table
     * @usage: new AddNewOffer(...new offer params...).insertIntoDatabase()
     * @see pap.logic.validators.OfferValidator
     * @see Offer
     * @see OfferDAO
     * @info (recommended validating data before inserting into database)
     */
    public void insertIntoDatabase() {
        new OfferDAO().create(offer);
    }
}
