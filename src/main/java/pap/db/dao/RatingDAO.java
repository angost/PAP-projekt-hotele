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
            return session.createNativeQuery("SELECT * FROM ratings", Rating.class)
                    .list();
        }
    }

    public Rating findById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Rating.class, id);
        }
    }

    public List<Rating> getRatingsForOffer(int offerId) {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("FROM ratings WHERE offer_id = :offerId", Rating.class)
                    .setParameter("offerId", offerId)
                    .list();
        }
    }

    public List<Rating> getRatingsForClient(int clientId) {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("FROM ratings WHERE client_id = :clientId", Rating.class)
                    .setParameter("clientId", clientId)
                    .list();
        }
    }

    public int getNumberOfRatingsForOffer(int offerId) {
        try (Session session = factory.openSession()) {
            Long count = (Long) session.createNativeQuery("SELECT COUNT(*) FROM ratings WHERE offer_id = :offerId")
                    .setParameter("offerId", offerId)
                    .uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }

    public double getAverageRatingForOffer(int offerId) {
        try (Session session = factory.openSession()) {
            Double average = (Double) session.createNativeQuery("SELECT AVG(rating) FROM ratings WHERE offer_id = :offerId")
                    .setParameter("offerId", offerId)
                    .uniqueResult();
            return average != null ? average : 0.0;
        }
    }

    public int getNumberOfRatingsForClient(int clientId) {
        try (Session session = factory.openSession()) {
            Long count = (Long) session.createNativeQuery("SELECT COUNT(*) FROM ratings WHERE client_id = :clientId")
                    .setParameter("clientId", clientId)
                    .uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }
}
