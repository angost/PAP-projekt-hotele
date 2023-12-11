package pap.logic.inserts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.*;
import java.util.*;


public class InsertOwnerCredentials {
    private final Owner owner;
    private final Address ownerAddress;

    public InsertOwnerCredentials(String username, String password, String companyName, String email, String phoneNumber,
                                  String country, String city, String street, String postalCode, String streetNo,
                                  String nip, boolean isVerified, boolean activeStatus, List <Hotel> hotels) {
        ownerAddress = new Address();
        ownerAddress.setCountry(country);
        ownerAddress.setCity(city);
        ownerAddress.setStreet(street);
        ownerAddress.setStreetNumber(streetNo);
        ownerAddress.setPostalCode(postalCode);
        owner = new Owner();
        owner.setUsername(username);
        owner.setPassword(password);
        owner.setCompanyName(companyName);
        owner.setEmail(email);
        owner.setPhoneNumber(phoneNumber);
        owner.setNip(nip);
        owner.setVerified(isVerified);
        owner.setActive(activeStatus);
        owner.setAddress(ownerAddress);
        owner.setHotels(hotels);
    }

    public boolean insertIntoDatabase() {
        try (SessionFactory factory = SessionFactoryMaker.getFactory(); Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(ownerAddress);
            session.persist(owner);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
