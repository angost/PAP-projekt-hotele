package pap.logic.guiAction;

import pap.db.dao.OfferDAO;
import pap.db.entities.Offer;
import pap.db.entities.Rating;
import pap.logic.RatingMean;

import java.util.HashMap;
import java.util.List;

public class OfferDetails {
    private String getImgPath(Integer offerId) {
        HashMap<Integer, String> imgPathMap = new HashMap<Integer, String>();
        imgPathMap.put(1, "/room1.jpg"); imgPathMap.put(2, "/room2.jpg");
        imgPathMap.put(3, "/room3.jpg"); imgPathMap.put(4, "/room4.jpg");
        imgPathMap.put(5, "/room5.jpg"); imgPathMap.put(6, "/room6.jpeg");
        imgPathMap.put(7, "/room7.jpg"); imgPathMap.put(8, "/room8.jpg");
        imgPathMap.put(9, "/room9.jpeg"); imgPathMap.put(10, "/room10.jpg");
        return imgPathMap.get(offerId);
    }
    public HashMap<String, String> getOfferInfo(Integer offerId) {
        HashMap<String, String> offerInfo = new HashMap<String, String>();
        OfferDAO od = new OfferDAO();
        Offer offer = od.findById(offerId);

        offerInfo.put("name", offer.getName());
        offerInfo.put("hotel", offer.getHotel().getName());
        offerInfo.put("price", String.format("%.2f", offer.getPrice()) + " PLN");
        offerInfo.put("description", offer.getDescription());
        offerInfo.put("img_path", getImgPath(offerId));
        offerInfo.put("street", offer.getHotel().getAddress().getStreet());
        offerInfo.put("street_nr", offer.getHotel().getAddress().getStreetNumber());
        offerInfo.put("city", offer.getHotel().getAddress().getCity());
        offerInfo.put("country", offer.getHotel().getAddress().getCountry());
        offerInfo.put("room_type", offer.getRoomType());
        offerInfo.put("rooms_nr", String.valueOf(offer.getRoomNumber()));
        offerInfo.put("bathrooms_nr", String.valueOf(offer.getBathroomNumber()));
        List<Rating> ratings = offer.getRatings(); //TODO
        offerInfo.put("review_score", "4.12"/*String.valueOf(RatingMean.calculateMean(ratings))*/);
        offerInfo.put("reviews_nr", "22" /*String.valueOf(ratings.size())*/);
        offerInfo.put("facilities_yes", "Free Wi-Fi:  ✔    Air conditioning:  ✔    TV in room:  ✔    Breakfast included:  ✔    24/7 reception:  ✔    Laundry services:  ✔   Airport transfer:  ✔    Room service:  ✔    Meeting/conference rooms:  ✔    Family-friendly:  ✔    Close to city center:  ✔");
        offerInfo.put("facilities_no", "Parking availability:  ✘    Disability access:  ✘    Balcony:  ✘    Pet-friendly:  ✘    Gym access:  ✘    Security features:  ✘        Kitchen:  ✘    Private bathroom:  ✘    Smoking allowed:  ✘    Pool access:  ✘");
        //✘✖
        return offerInfo;
    }
}
