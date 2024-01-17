package pap.logic.admin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.Owner;

import java.util.List;

public class GetNotVerifiedOwners {
    private SessionFactory factory = SessionFactoryMaker.getFactory();

    /**
     * method finds not verified owners from database
     * @return list of not verified owners
     * @usage: new GetNotVerifiedOwners().find()
     * @see pap.db.entities.Owner
     * @see pap.db.dao.OwnerDAO
     * @see VerificationRequest
     * @info (useful for admin functionality - verify owners)
     */
    public List <Owner> find() {
        try (Session session = factory.openSession()) {
            return session.createNativeQuery("select * from owners where is_verified = false", Owner.class).list();
        }
    }
}
