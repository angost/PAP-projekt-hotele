package pap.logic;

import pap.db.entities.*;
import pap.db.dao.*;
import java.util.*;

public class DeactivateAccount {
    public static boolean deactivateUserAccount(int id) {
        Client user = new ClientDAO().findById(id);
        List<Reservation> reservations = new ReservationDAO().findByClientId(id);
        if (!reservations.isEmpty()) {
            for (var reservation : reservations) {
                if (reservation.getStatus().equals("active")) {
                    return false;
                }
            }
        }
        user.setActive(false);
        new ClientDAO().update(user);
        return true;
    }

    public static boolean deactivateOwnerAccount(int id) {
        Owner owner = new OwnerDAO().findById(id);
        List <Hotel> hotels = new HotelDAO().findByOwnerId(id);
        if (!hotels.isEmpty()) {
            for (var hotel : hotels) {
                if (hotel.isActive()) {
                    return false;
                }
            }
        }
        owner.setActive(false);
        new OwnerDAO().update(owner);
        return true;
    }
}
