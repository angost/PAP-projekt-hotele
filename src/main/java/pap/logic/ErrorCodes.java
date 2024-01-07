package pap.logic;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodes {
    private static final Map<Integer, String> errorDescriptions = new HashMap<>();

    static {
        // User Errors
        errorDescriptions.put(1, "Username is too short");
        errorDescriptions.put(2, "Username is too long");
        errorDescriptions.put(3, "Username contains illegal characters");
        errorDescriptions.put(4, "Username contains illegal keywords");
        errorDescriptions.put(5, "Username is not unique");
        errorDescriptions.put(6, "Password is too short");
        errorDescriptions.put(7, "Password is too long");
        errorDescriptions.put(8, "Password contains username");
        errorDescriptions.put(9, "Password doesn't contain capital letter");
        errorDescriptions.put(10, "Password doesn't contain lowercase letter");
        errorDescriptions.put(11, "Password doesn't contain number");
        errorDescriptions.put(12, "Password doesn't contain special character");
        errorDescriptions.put(13, "Name is too short");
        errorDescriptions.put(14, "Name is too long");
        errorDescriptions.put(15, "Surname is too short");
        errorDescriptions.put(16, "Surname is too long");
        errorDescriptions.put(17, "Email is too short");
        errorDescriptions.put(18, "Email is too long");
        errorDescriptions.put(19, "Email doesn't contain @");
        errorDescriptions.put(20, "Phone number is too short");
        errorDescriptions.put(21, "Phone number is too long");
        errorDescriptions.put(22, "Birthdate is invalid");
        errorDescriptions.put(23, "Nationality is too short");
        errorDescriptions.put(24, "Nationality is too long");
        errorDescriptions.put(25, "Gender is invalid");

        // Address errors
        errorDescriptions.put(51, "Country is too short");
        errorDescriptions.put(52, "Country is too long");
        errorDescriptions.put(53, "City is too short");
        errorDescriptions.put(54, "City is too long");
        errorDescriptions.put(55, "Street is too short");
        errorDescriptions.put(56, "Street is too long");
        errorDescriptions.put(57, "Postal code is too short");
        errorDescriptions.put(58, "Postal code is too long");
        errorDescriptions.put(59, "Street number is too short");
        errorDescriptions.put(60, "Street number is too long");
        errorDescriptions.put(61, "Street number doesn’t contain number");

        // Owner Errors
        errorDescriptions.put(101, "Owner username is too short");
        errorDescriptions.put(102, "Owner username is too long");
        errorDescriptions.put(103, "Owner username contains illegal characters");
        errorDescriptions.put(104, "Owner username contains illegal keywords");
        errorDescriptions.put(105, "Owner username is not unique");
        errorDescriptions.put(106, "Owner password is too short");
        errorDescriptions.put(107, "Owner password is too long");
        errorDescriptions.put(108, "Owner password contains username");
        errorDescriptions.put(109, "Owner password doesn't contain capital");
        errorDescriptions.put(110, "Owner password doesn't contain lowercase");
        errorDescriptions.put(111, "Owner password doesn't contain number");
        errorDescriptions.put(112, "Owner password doesn't contain special character");
        errorDescriptions.put(113, "Owner company name is too short");
        errorDescriptions.put(114, "Owner company name is too long");
        errorDescriptions.put(115, "Owner email is too short");
        errorDescriptions.put(116, "Owner email is too long");
        errorDescriptions.put(117, "Owner email doesn't contain @");
        errorDescriptions.put(118, "Owner phone number is too short");
        errorDescriptions.put(119, "Owner phone number is too long");
        errorDescriptions.put(120, "Owner nip is wrong length");
        errorDescriptions.put(121, "Owner nip contains not only digits");
        errorDescriptions.put(122, "Owner nip has wrong checksum");

        // Login Errors
        errorDescriptions.put(201, "No such user in database (not successful user login attempt)");
        errorDescriptions.put(202, "Wrong password (not successful user login attempt)");
        errorDescriptions.put(203, "User account is not active (not successful login attempt)");

        errorDescriptions.put(301, "No such owner in database (not successful owner login attempt)");
        errorDescriptions.put(302, "Wrong password for owner (not successful owner login attempt)");
        errorDescriptions.put(303, "Owner account is not active (not successful owner login attempt)");

        errorDescriptions.put(501, "No such admin in database (not successful admin login attempt)");
        errorDescriptions.put(502, "Wrong password (not successful admin login attempt)");
        errorDescriptions.put(503, "Admin account is not active (not successful admin login attempt)");

        // Deactivate Errors
        errorDescriptions.put(401, "User not found");
        errorDescriptions.put(402, "User has active reservations (not successful deactivate account)");
        errorDescriptions.put(403, "Owner not found");
        errorDescriptions.put(404, "Owner has active hotels (not successful deactivate account)");
    }

    public static String getErrorDescription(int errorCode) {
        return errorDescriptions.getOrDefault(errorCode, "Unknown error");
    }
}