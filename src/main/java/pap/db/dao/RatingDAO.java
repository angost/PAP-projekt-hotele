package pap.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.Offer;
import pap.db.entities.Rating;

import java.util.List;

public class RatingDAO {
    SessionFactory factory = SessionFactoryMaker.getFactory();

    public void create(Rating rating) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(rating);
            session.getTransaction().commit();
            System.out.println("[RatingDAO] Created offer with id: " + rating.getRatingId());
        }
    }

    public void update(Rating rating) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.merge(rating);
            session.getTransaction().commit();
            System.out.println("[RatingDAO] Updated offer with id: " + rating.getRatingId());
        }
    }

    public List<Rating> findAll() {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("select * from ratings", Rating.class).list();
        }
    }

    public Rating findById(int id) {
        try (Session session = factory.openSession()) {
            return session.find(Rating.class, id);
        }
    }

    public int getOfferRatingsAmount(int offer_id) {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("select count(*) from ratings where offer_id = :offer_id", Integer.class)
                    .setParameter("offer_id", offer_id)
                    .getSingleResult();
        }
    }

    public float getAverageOfferRating(int offer_id) {
        if (getOfferRatingsAmount(offer_id) == 0)
            return 0.00f;
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("select avg(rating) from ratings where offer_id = :offer_id", Float.class)
                    .setParameter("offer_id", offer_id)
                    .getSingleResult();
        }
    }
}
