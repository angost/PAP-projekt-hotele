package pap.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.Client;

public class AdminDAO {
    SessionFactory factory = SessionFactoryMaker.getFactory();

    public Client findById(int id) {
        try (Session session = factory.openSession()) {
            return session.find(Client.class, id);
        }
    }
}
