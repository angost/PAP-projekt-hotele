package pap.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.Offer;
import pap.db.entities.Reservation;

import java.util.List;

public class OfferDAO {
    SessionFactory factory = SessionFactoryMaker.getFactory();

    public void create(Offer offer) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(offer);
            session.getTransaction().commit();
            System.out.println("[OfferDAO] Created offer with id: " + offer.getOfferId());
        }
    }

    public void createMany(Iterable<Offer> offers) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            for (Offer offer : offers)
                session.persist(offer);
            session.getTransaction().commit();
            for (Offer offer : offers)
                System.out.println("[OfferDAO] Created offer with id: " + offer.getOfferId());
        }
    }

    public void update(Offer offer) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.merge(offer);
            session.getTransaction().commit();
            System.out.println("[OfferDAO] Updated offer with id: " + offer.getOfferId());
        }
    }

    public void updateMany(Iterable<Offer> offers) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            for (Offer offer : offers)
                session.merge(offer);
            session.getTransaction().commit();
            for (Offer offer : offers)
                System.out.println("[OfferDAO] Updated offer with id: " + offer.getOfferId());
        }
    }

    public void delete(Offer offer) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.remove(offer);
            session.getTransaction().commit();
            System.out.println("[OfferDAO] Deleted offer with id: " + offer.getOfferId());
        }
    }

    public void deleteMany(Iterable<Offer> offers) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            for (Offer offer : offers)
                session.remove(offer);
            session.getTransaction().commit();
            for (Offer offer : offers)
                System.out.println("[OfferDAO] Deleted offer with id: " + offer.getOfferId());
        }
    }

    public List<Offer> findAll() {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("select * from offers", Offer.class).list();
        }
    }

    public Offer findById(int id) {
        try (Session session = factory.openSession()) {
            return session.find(Offer.class, id);
        }
    }

    public List<Offer> findByHotelId(int id) {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("select * from offers where hotel_id = " + id, Offer.class).list();
        }
    }

    public List<Offer> customQuery(String query) {
        try (Session session = factory.openSession()) {
            // it is not safe to use this method with user input
            return session.createNativeQuery(query, Offer.class).list();
        }
    }
}
