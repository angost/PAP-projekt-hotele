package pap.logic.add;

import pap.db.dao.HotelDAO;
import pap.db.dao.OwnerDAO;
import pap.db.entities.Address;
import pap.db.entities.Hotel;
import pap.db.entities.Owner;

import java.time.LocalDate;

public class AddHotel {
    private final Hotel hotel;

    public AddHotel(String name, LocalDate addDate, String description, String country, String city,
                    String street, String postalCode, String streetNo, String email, String website, String phoneNumber,
                    String bankAccountNumber, boolean isActive, Owner owner) {
        this.hotel = new Hotel();
        this.hotel.setName(name);
        this.hotel.setAddDate(addDate);
        this.hotel.setDescription(description);
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setPostalCode(postalCode);
        address.setStreetNumber(streetNo);
        this.hotel.setAddress(address);
        this.hotel.setEmail(email);
        this.hotel.setWebsite(website);
        this.hotel.setPhoneNumber(phoneNumber);
        this.hotel.setBankAccountNumber(bankAccountNumber);
        this.hotel.setActive(isActive);
        this.hotel.setOwner(owner);
    }

    public void insertIntoDatabase() {
        new HotelDAO().createWithNewAddress(hotel);
    }

    public static void main(String[] args) {
        Owner owner = new OwnerDAO().findById(1);
        AddHotel addHotel = new AddHotel("avad", LocalDate.now(), "da awd d ada a", "Poland", "Warsaw", "ADJADAW", "01-123", "2A", "danwidoa@gmail.com", "awdnanwdw.com", "123465789", "12345678912345678912345678", false, owner);
        addHotel.insertIntoDatabase();
    }
}
