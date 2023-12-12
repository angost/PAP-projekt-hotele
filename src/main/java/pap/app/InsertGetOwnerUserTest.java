package pap.app;

import pap.logic.add.*;
import pap.logic.login.*;
import java.time.LocalDate;

import pap.db.entities.*;
import pap.db.dao.*;

public class InsertGetOwnerUserTest {
    public static void insertNewUser() {
        new AddNewUser("testusername", "ValidPassword1!", "Jason", "Statham", "jasons@gmail.com", "123456789",
                "Poland", "Warsaw", "Nowowiejska", "00-123", "2", LocalDate.of(2001, 1, 1), "Polish", "Male", true).insertIntoDatabase();
    }

    public static void insertNewUserNoAddress() {
        new AddNewUser("testusername1", "ValidPassword1!", "Jason", "Statham", "jasons@gmail.com", "123456789",
                LocalDate.of(2001, 1, 1), "Polish", "Male", true).insertIntoDatabase();
    }

    public static void insertNewOwner() {
        Owner owner = new Owner();
        owner.setUsername("testUsername");
        owner.setPassword("testPassword1!");
        owner.setEmail("testemail@email.com");
        owner.setPhoneNumber("123456789");
        owner.setCompanyName("Test Company");
        owner.setNip("1234567890");
        owner.setActive(true);
        owner.setVerified(true);
        new OwnerDAO().create(owner);
    }

    public static void getUser() {
        UserLogin ul = new UserLogin("testusername", "ValidPassword1!");
        Client user = ul.getUserAccount();
        System.out.println(user.getEmail());
    }

    public static void getOwner() {
        OwnerLogin ol = new OwnerLogin("testUsername", "testPassword1!");
        Owner owner = ol.getOwnerAccount();
        System.out.println(owner.getEmail());
    }

    public static void main(String[] args) {
        insertNewUser();
        insertNewUserNoAddress();
        insertNewOwner();
        getUser();
        getOwner();
    }
}
