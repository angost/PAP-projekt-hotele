package pap.logic.inserts;

import pap.db.entities.*;
import pap.db.dao.OwnerDAO;


public class InsertOwnerCredentials {
    private final Owner owner;
    private Address ownerAddress;
    boolean address;

    public InsertOwnerCredentials(String username, String password, String companyName, String email, String phoneNumber,
                                  String country, String city, String street, String postalCode, String streetNo,
                                  String nip, boolean isVerified, boolean activeStatus) {
        ownerAddress = new Address();
        ownerAddress.setCountry(country);
        ownerAddress.setCity(city);
        ownerAddress.setStreet(street);
        ownerAddress.setStreetNumber(streetNo);
        ownerAddress.setPostalCode(postalCode);
        owner = new Owner();
        owner.setUsername(username);
        owner.setPassword(password);
        owner.setEmail(email);
        owner.setPhoneNumber(phoneNumber);
        owner.setCompanyName(companyName);
        owner.setNip(nip);
        owner.setActive(isVerified);
        owner.setVerified(activeStatus);
        owner.setAddress(ownerAddress);
    }

    public InsertOwnerCredentials(String username, String password, String companyName, String email, String phoneNumber,
                                  String nip, boolean isVerified, boolean activeStatus) {
        owner = new Owner();
        owner.setUsername(username);
        owner.setPassword(password);
        owner.setEmail(email);
        owner.setPhoneNumber(phoneNumber);
        owner.setCompanyName(companyName);
        owner.setNip(nip);
        owner.setActive(isVerified);
        owner.setVerified(activeStatus);
    }

    public void insertIntoDatabase() {
        if (address) {
            new OwnerDAO().createWithNewAddress(owner);
        } else {
            new OwnerDAO().create(owner);
        }
    }
}
