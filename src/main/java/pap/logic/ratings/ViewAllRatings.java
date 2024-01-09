package pap.logic.ratings;

import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.Rating;

import java.util.List;

public class ViewAllRatings {
    private SessionFactory factory = SessionFactoryMaker.getFactory();
    public static List <Rating> getAllRatings() {

    }

    public static List <Rating> getAllRatingsOrderByNewest() {

    }
}
