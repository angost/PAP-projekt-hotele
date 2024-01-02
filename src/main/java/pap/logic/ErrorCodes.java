package pap.logic;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {
    private static final Map<Integer, String> errorDescriptions = new HashMap<>();

    static {
        errorDescriptions.put(1, "Database error");

        // User errors
        errorDescriptions.put(101, "Username is too short");
        errorDescriptions.put(102, "Username is too long");
        errorDescriptions.put(103, "Username contains illegal characters");
        errorDescriptions.put(104, "Username contains illegal keywords");
        errorDescriptions.put(105, "Username is not unique");
        errorDescriptions.put(106, "Password is too short");
        errorDescriptions.put(107, "Password is too long");
        errorDescriptions.put(108, "Password contains username");
        errorDescriptions.put(109, "Password doesn't contain capital letter");
        errorDescriptions.put(110, "Password doesn't contain lowercase letter");
        errorDescriptions.put(111, "Password doesn't contain number");
        errorDescriptions.put(112, "Password doesn't contain special character");
        errorDescriptions.put(113, "Email is too short");
        errorDescriptions.put(114, "Email is too long");
        errorDescriptions.put(115, "Email doesn't contain @");
        errorDescriptions.put(116, "Phone number is too short");
        errorDescriptions.put(117, "Phone number is too long");
        errorDescriptions.put(118, "Phone number doesn't contain only numbers");

        // Client errors
        errorDescriptions.put(201, "Client name is too short");
        errorDescriptions.put(202, "Client name is too long");
        errorDescriptions.put(203, "Client name doesn't contain only letters");
        errorDescriptions.put(204, "Client surname is too short");
        errorDescriptions.put(205, "Client surname is too long");
        errorDescriptions.put(206, "Client surname doesn't contain only letters");
        errorDescriptions.put(207, "Birthdate is invalid");
        errorDescriptions.put(208, "Nationality is too short");
        errorDescriptions.put(209, "Nationality is too long");
        errorDescriptions.put(210, "Nationality doesn't contain only letters");
        errorDescriptions.put(211, "Gender is invalid");

        // Owner errors
        errorDescriptions.put(301, "Owner company name is too short");
        errorDescriptions.put(302, "Owner company name is too long");
        errorDescriptions.put(303, "Owner NIP is wrong length");
        errorDescriptions.put(304, "Owner NIP contains not only digits");

        // Login errors
        errorDescriptions.put(401, "No such user in the database (not a successful login attempt)");
        errorDescriptions.put(402, "Wrong password (not a successful login attempt)");
        errorDescriptions.put(403, "User is not active (not a successful login attempt)");
        errorDescriptions.put(404, "No such owner in the database (not a successful login attempt)");
        errorDescriptions.put(405, "Wrong password for owner (not a successful login attempt)");
        errorDescriptions.put(406, "Owner is not active (not a successful login attempt)");

        // Addresses
        errorDescriptions.put(501, "Country is too short");
        errorDescriptions.put(502, "Country is too long");
        errorDescriptions.put(503, "City is too short");
        errorDescriptions.put(504, "City is too long");
        errorDescriptions.put(505, "Street is too short");
        errorDescriptions.put(506, "Street is too long");
        errorDescriptions.put(507, "Postal code is too short");
        errorDescriptions.put(508, "Postal code is too long");
        errorDescriptions.put(509, "Street number is too short");
        errorDescriptions.put(510, "Street number is too long");
        errorDescriptions.put(511, "Wrong street number format");
    }

    public static String getErrorDescription(int errorCode) {
        System.out.println(errorCode);
        return errorDescriptions.getOrDefault(errorCode, "Unknown error");
    }
}