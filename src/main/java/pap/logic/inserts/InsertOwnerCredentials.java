package pap.logic.inserts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pap.db.SessionFactoryMaker;
import pap.db.entities.*;
import java.util.*;


public class InsertOwnerCredentials {
//    private final Owner owner;
//    private final Address ownerAddress;

    public InsertOwnerCredentials(String username, String password, String companyName, String email, String phoneNumber,
                                  String country, String city, String street, String postalCode, String streetNo,
                                  String nip, boolean isVerified, boolean activeStatus, List <Hotel> hotels) {
        // TODO
    }

    public boolean insertIntoDatabase() {
        // TODO
        return true;
    }
}
