package pap.logic.inserts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.*;

import java.time.LocalDate;

public class InsertUserCredentials {
    private final Client user;
    private final Address userAddress;

    public InsertUserCredentials(String username, String password, String name, String surname, String email, String phoneNumber,
                                 String country, String city, String street, String postalCode, String streetNo,
                                 LocalDate birthDate, String nationality, String gender, boolean activeStatus) {
        userAddress = new Address();
        userAddress.setCountry(country);
        userAddress.setCity(city);
        userAddress.setStreet(street);
        userAddress.setStreetNumber(streetNo);
        userAddress.setPostalCode(postalCode);
        user = new Client();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setBirthDate(birthDate);
        user.setNationality(nationality);
        user.setGender(gender);
        user.setAddress(userAddress);
        user.setActive(activeStatus);
    }

    public boolean insertIntoDatabase() {
        try (SessionFactory factory = SessionFactoryMaker.getFactory(); Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(userAddress);
            session.persist(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
