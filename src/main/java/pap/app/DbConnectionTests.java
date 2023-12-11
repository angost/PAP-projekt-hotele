package pap.app;

import pap.db.dao.*;
import pap.db.entities.*;

import java.util.List;

public class DbConnectionTests {
    public static void getAllUsers() {
        List<Client> clients = new ClientDAO().findAll();
        for (Client c : clients) {
            System.out.println(c.getClientId() + " " + c.getName());
        }
    }

    public static void getUserById(int id) {
        Client c = new ClientDAO().findById(id);
        if (c == null)
            System.out.println("Client with id " + id + " not found");
        else
            System.out.println(c.getClientId() + " " + c.getName());
    }

    public static void addUser() {
        Address a = new Address();
        a.setCountry("Poland");
        a.setCity("Warszawa");
        a.setStreet("Nowogrodzka");
        a.setStreetNumber("20/3");
        a.setPostalCode("00-000");

        Client c = new Client();
        c.setUsername("johndoe123");
        c.setPassword("password");
        c.setName("John");
        c.setSurname("Doe");
        c.setEmail("johndoe@example.com");
        c.setPhoneNumber("123456789");
        c.setAddress(a);
        c.setBirthDate(java.time.LocalDate.of(1990, 1, 1));
        c.setNationality("American");
        c.setGender("male");
        c.setActive(true);

        new ClientDAO().createWithNewAddress(c);
    }

    public static void main(String[] args) {
        getAllUsers();
        getUserById(45);
    }
}