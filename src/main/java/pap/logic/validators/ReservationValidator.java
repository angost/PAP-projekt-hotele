package pap.logic.validators;

import java.time.LocalDate;
import java.util.*;
import pap.db.dao.*;
import pap.db.entities.*;

public class ReservationValidator {

    private final LocalDate dateStart;
    private final LocalDate dateEnd;
    private final Integer offerId;

    public ReservationValidator(LocalDate dateStart, LocalDate dateEnd, Integer offerId){
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.offerId = offerId;
    }

    public List <Integer> validate(){
        List <Integer> codes = new ArrayList<>();
        checkOfferActive(offerId, codes);
        checkDateAvailable(offerId, dateStart, dateEnd, codes);
        return codes;
    }

    private static void checkOfferActive(Integer offerId, List <Integer> codes){
        try {
            Offer offer = new OfferDAO().findById(offerId);
            if (!offer.isActive()){
                codes.add(1);
            }
        } catch (Exception ignored) {}
    }

    private static void checkDateAvailable(Integer offerId, LocalDate dateStart, LocalDate dateEnd, List<Integer> codes){
        try {
            List<Reservation> reservations = new ReservationDAO().findByOfferId(offerId);
            for (Reservation reservation: reservations){
                if (reservation.getEndDate().isBefore(dateEnd) && reservation.getStartDate().isAfter(dateStart)){
                    codes.add(2);
                    return;
                }
            }
        } catch (Exception ignored) {}
    }
}