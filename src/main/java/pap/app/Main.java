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
            a.setCountry("Polando");
            a.setCity("Sfdf");
            a.setStreet("Kofdf");
            a.setStreetNumber("86");
            a.setPostalCode("00-667");

            session.persist(a);
            session.getTransaction().commit();
        }
    }
}