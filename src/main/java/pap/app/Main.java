package pap.app;

import pap.db.*;
import pap.db.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = SessionFactoryMaker.getFactory();

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            Address a = new Address();
            a.setCountry("Poland");
            a.setCity("Warszawa");
            a.setStreet("Nowogrodzka");
            a.setStreetNumber("20/3");
            a.setPostalCode("00-000");

            Client c = new Client();
            c.setUsername("johndoe123");
            c.setPassword("password");
            c.setName("John");
            c.setSurname("Doe");
            c.setEmail("johndoe@example.com");
            c.setPhoneNumber("123456789");
            c.setAddress(a);
            c.setBirthDate(java.time.LocalDate.of(1990, 1, 1));
            c.setNationality("American");
            c.setGender("male");
            c.setActive(true);

            session.persist(a);
            session.persist(c);
            session.getTransaction().commit();
        }
    }
}