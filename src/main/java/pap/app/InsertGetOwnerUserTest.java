package pap.app;

import pap.logic.add.*;
import pap.logic.login.*;
import java.time.LocalDate;

import pap.db.entities.*;

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
        new AddNewOwner("jason123", "testPassword1!", "Test Company", "testemail@email.com", "123456789", "1234567890", true, true).insertIntoDatabase();
    }

    public static void getUser() {
        UserLogin ul = new UserLogin("testusername", "ValidPassword1!");
        Client user = ul.getUserAccount();
        System.out.println(user.getEmail());
    }

    public static void getOwner() {
        OwnerLogin ol = new OwnerLogin("jason123", "testPassword1!");
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
