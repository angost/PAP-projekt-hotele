package pap.logic.reservation;
import pap.db.entities.*;
import pap.db.dao.*;

import java.time.LocalDate;
import java.util.List;

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
        }
        else {
            //TODO
        }
    }

    public void changeEndDate(LocalDate endDate){
        if (endDate.isAfter(reservation.getStartDate())){
            reservation.setEndDate(endDate);
        }
        else {
            //TODO
        }
    }

    public void changeDescription(String description){
        reservation.setDescription(description);
    }

    public void changePaidAmount(float paidAmount){
        reservation.setPaidAmount(paidAmount);
    }

    public void changeStatus(String status){
        reservation.setStatus(status);
    }

    public void changeIsPaid(boolean isPaid){
        reservation.setPaid(isPaid);
    }
}
