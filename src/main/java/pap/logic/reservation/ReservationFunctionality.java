package pap.logic.reservation;
import pap.db.entities.*;
import pap.db.dao.*;

import java.time.LocalDate;

public class ReservationFunctionality {

    private final Reservation reservation;

    public ReservationFunctionality(Integer resId){
        reservation = new ReservationDAO().findById(resId);
    }
    public void deleteReservation(){
        new ReservationDAO().delete(reservation);
    }

    public void changeStartDate(LocalDate startDate){
        if (startDate.isBefore(reservation.getEndDate())){
            reservation.setStartDate(startDate);
            new ReservationDAO().update(reservation);
        }
        else {
            //TODO
        }
    }

    public void changeEndDate(LocalDate endDate){
        if (endDate.isAfter(reservation.getStartDate())){
            reservation.setEndDate(endDate);
            new ReservationDAO().update(reservation);
        }
        else {
            //TODO
        }
    }

    public void changeDescription(String description){
        reservation.setDescription(description);
        new ReservationDAO().update(reservation);
    }

    public void changePaidAmount(float paidAmount){
        reservation.setPaidAmount(paidAmount);
        new ReservationDAO().update(reservation);
    }

    public void changeStatus(String status){
        reservation.setStatus(status);
        new ReservationDAO().update(reservation);
    }

    public void changeIsPaid(boolean isPaid){
        reservation.setPaid(isPaid);
        new ReservationDAO().update(reservation);
    }
}
