package pap;

import db.*;
import db.entities.*;
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
            a.setStreet("Koszykowa");
            a.setStreetNumber("86");
            a.setPostalCode("00-667");

            session.persist(a);
            session.getTransaction().commit();
        }
    }
}