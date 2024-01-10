package pap.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.Rating;

import java.util.List;

public class RatingDAO {
    SessionFactory factory = SessionFactoryMaker.getFactory();

    public void create(Rating rating) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(rating);
            session.getTransaction().commit();
            System.out.println("[RatingDAO] Created rating with id: " + rating.getRatingId());
        }
    }

    public void createMany(Iterable<Rating> ratingsList) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            for (Rating rating : ratingsList) {
                session.persist(rating);
                System.out.println("[RatingDAO] Created rating with id: " + rating.getRatingId());
            }
            session.getTransaction().commit();
        }
    }

    public void delete(Rating rating) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.remove(rating);
            session.getTransaction().commit();
            System.out.println("[RatingDAO] Deleted rating with id: " + rating.getRatingId());
        }
    }

    public void deleteMany(Iterable<Rating> ratingsList) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            for (Rating rating : ratingsList) {
                session.remove(rating);
                System.out.println("[RatingDAO] Deleted rating with id: " + rating.getRatingId());
            }
            session.getTransaction().commit();
        }
    }

    public void update(Rating rating) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.merge(rating);
            session.getTransaction().commit();
            System.out.println("[RatingDAO] Updated rating with id: " + rating.getRatingId());
        }
    }

    public void updateMany(Iterable<Rating> ratingsList) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            for (Rating rating : ratingsList) {
                session.merge(rating);
                System.out.println("[RatingDAO] Updated rating with id: " + rating.getRatingId());
            }
            session.getTransaction().commit();
        }
    }

    public List <Rating> findAll() {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("SELECT * FROM ratings WHERE is_hidden = false", Rating.class)
                    .list();
            System.out.println("[RatingDAO] Updated offer with id: " + rating.getRatingId());
        }
    }

    public Rating findById(int id) {
        try (Session session = factory.openSession()) {
            return session.find(Rating.class, id);
        }
    }

    public List<Rating> getRatingsForOffer(int offerId) {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("FROM ratings WHERE offer_id = :offerId AND is_hidden = false", Rating.class)
                    .setParameter("offerId", offerId)
                    .list();
        }
    }

    public List<Rating> getRatingsForClient(int clientId) {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("SELECT * FROM ratings WHERE client_id = :clientId AND is_hidden = false", Rating.class)
                    .setParameter("clientId", clientId)
                    .list();
        }
    }

    public int getNumberOfRatingsForOffer(int offerId) {
        try (Session session = factory.openSession()) {
            Long count = (Long) session.createNativeQuery("SELECT COUNT(*) FROM ratings WHERE offer_id = :offerId AND is_hidden = false")
                    .setParameter("offerId", offerId)
                    .uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }

    public Float getAverageRatingForOffer(int offerId) {
        try (Session session = factory.openSession()) {
            Number average = (Number) session.createNativeQuery("SELECT AVG(rating) FROM ratings WHERE offer_id = :offerId AND is_hidden = false")
                    .setParameter("offerId", offerId)
                    .uniqueResult();
            return average != null ? average.floatValue() : 0;
        }
    }

    public int getNumberOfRatingsForClient(int clientId) {
        try (Session session = factory.openSession()) {
            Long count = (Long) session.createNativeQuery("SELECT COUNT(*) FROM ratings WHERE client_id = :clientId AND is_hidden = false")
                    .setParameter("clientId", clientId)
                    .uniqueResult();
            return count != null ? count.intValue() : 0;
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
