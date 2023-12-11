package pap.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.*;


public class InsertOwnerCredentials {
    private final Owner owner;
    private final Address ownerAddress;

    public InsertOwnerCredentials(String username, String password, String companyName, String email, String phoneNumber,
                                  String country, String city, String street, String postalCode, String streetNo,
                                  String nip, boolean isVerified, boolean activeStatus) {
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
        owner.setIsVerified(isVerified);
        owner.setIsActive(activeStatus);
        owner.setAddress(ownerAddress);
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
