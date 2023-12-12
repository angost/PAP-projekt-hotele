package pap.logic.validators;

import java.util.*;
import pap.db.dao.*;
import pap.db.entities.Offer;

public class ReservationValidator {

    private final String dateStart;
    private final String dateEnd;
    private final Integer offerId;
    private final Integer userId;

    public ReservationValidator(String dateStart, String dateEnd, Integer offerId, Integer userId){
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.offerId = offerId;
        this.userId = userId;
    }

    public List <Integer> validate(){
        List <Integer> codes = new ArrayList<>();
        checkOfferActive(offerId, codes);
    }

    private static void checkOfferActive(Integer offerId, List <Integer> codes){
        try {
            Offer offer = new OfferDAO().findById(offerId);
            if (!offer.isActive()){
                codes.add(1);
            }
        } catch (Exception ignored) {}
    }

}
