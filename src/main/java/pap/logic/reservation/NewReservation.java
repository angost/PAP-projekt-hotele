package pap.logic.reservation;

import pap.db.entities.*;
import pap.db.dao.*;

import java.time.LocalDate;
import java.util.List;

public class NewReservation {
    private final Reservation reservation;

    public NewReservation(LocalDate startDate, LocalDate endDate, String description, Float paidAmount,
                          boolean isPaid, Client client, Offer offer, List<Penalty> penalties){
        reservation = new Reservation();
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setDescription(description);
        reservation.setPaidAmount(paidAmount);
        reservation.setStatus("active");
        reservation.setPaid(isPaid);
        reservation.setClient(client);
        reservation.setOffer(offer);
        reservation.setPenalties(penalties);
    }

    public void insertIntoDatabase() {
        new ReservationDAO().create(reservation);
    }
}
