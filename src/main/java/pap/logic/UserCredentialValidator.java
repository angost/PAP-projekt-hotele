package pap.logic;

import java.util.*;
import java.time.LocalDate;

public class UserCredentialValidator {
    private static final int MIN_USERNAME_LENGTH = 8;
    private static final int MAX_USERNAME_LENGTH = 64;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 64;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 64;
    private static final int MIN_SURNAME_LENGTH = 2;
    private static final int MAX_SURNAME_LENGTH = 64;
    private static final int MIN_EMAIL_LENGTH = 5;
    private static final int MAX_EMAIL_LENGTH = 64;
    private static final int MIN_PHONE_LENGTH = 9;
    private static final int MAX_PHONE_LENGTH = 14;
    private static final int MIN_NATIONALITY_LENGTH = 2;
    private static final int MAX_NATIONALITY_LENGTH = 64;
    private static final String USERNAME_ILLEGAL_CHARS_REGEX = ".*[@#$%&/\\\\].*";
    private static final List<String> BANNED_KEYWORDS = Arrays.asList("admin", "root", "system");
    private static final String SPECIAL_CHARACTERS_REGEX = ".*[!@#$%^&*()\\[\\]{}\\-_=+/\\\\?.,<>'\";:|§].*";

    private final String username;
    private final String password;
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final AddressValidator addressValidator;
    private final LocalDate birthDate;
    private final String nationality;
    private final String gender;

    public UserCredentialValidator(String username, String password, String name, String surname, String email, String phoneNumber,
                                   String country, String city, String street, String postalCode, String streetNo,
                                   LocalDate birthDate, String nationality, String gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addressValidator = new AddressValidator(country, city, street, postalCode, streetNo);
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.gender = gender;
    }

    public List <Integer> validateCredentials() {
        List <Integer> codes = new ArrayList<>();
        validateUsername(username, codes);
        validatePassword(password, codes, username);
        validateName(name, codes);
        validateSurname(surname, codes);
        validateEmail(email, codes);
        validatePhoneNumber(phoneNumber, codes);
        validateAddress(addressValidator, codes);
        validateBirthDate(birthDate, codes);
        validateNationality(nationality, codes);
        validateGender(gender, codes);
        return codes;
    }

    public static void validateUsername(String username, List <Integer> codes) {
        checkTooShortUsername(username, codes);
        checkTooLongUsername(username, codes);
        checkUsernameContainsIllegalChar(username, codes);
        checkUsernameContainsIllegalKeyword(username, codes);
        checkUsernameIsUnique(username, codes);
    }

    public static void validatePassword(String password, List <Integer> codes, String username) {
        checkTooShortPassword(password, codes);
        checkTooLongPassword(password, codes);
        checkPasswordContainsUsername(password, codes, username);
        checkPasswordDoesntContainCapital(password, codes);
        checkPasswordDoesntContainLower(password, codes);
        checkPasswordDoesntContainNumber(password, codes);
        checkPasswordDoesntContainSpecialChar(password, codes);
    }

    public static void validateName(String name, List <Integer> codes) {
        checkTooShortName(name, codes);
        checkTooLongName(name, codes);
    }

    public static void validateSurname(String surname, List <Integer> codes) {
        checkTooShortSurname(surname, codes);
        checkTooLongSurname(surname, codes);
    }

    public static void validateEmail(String email, List <Integer> codes) {
        checkTooShortEmail(email, codes);
        checkTooLongEmail(email, codes);
        checkEmailDoesntContainAt(email, codes);
    }

    public static void validatePhoneNumber(String phoneNumber, List <Integer> codes) {
        checkTooShortPhoneNumber(phoneNumber, codes);
        checkTooLongPhoneNumber(phoneNumber, codes);
    }

    public static void validateAddress(AddressValidator addressValidator, List <Integer> codes) {
//        validateAddress.validate(); // TODO
    }

    public static void validateBirthDate(LocalDate birthDate, List <Integer> codes) {
        checkInvalidBirthDate(birthDate, codes);
    }

    public static void validateNationality(String nationality, List <Integer> codes) {
        checkTooShortNationality(nationality, codes);
        checkTooLongNationality(nationality, codes);
    }

    public static void validateGender(String gender, List <Integer> codes) {
        checkGender(gender, codes);
    }

    private static void checkTooShortUsername(String username, List <Integer> codes) {
        if (username.length() < MIN_USERNAME_LENGTH) codes.add(1);
    }
    private static void checkTooLongUsername(String username, List <Integer> codes) {
        if (username.length() > MAX_USERNAME_LENGTH) codes.add(2);
    }
    private static void checkUsernameContainsIllegalChar(String username, List <Integer> codes) {
        if (username.matches(USERNAME_ILLEGAL_CHARS_REGEX)) codes.add(3);
    }
    private static void checkUsernameContainsIllegalKeyword(String username, List <Integer> codes) {
        if (BANNED_KEYWORDS.stream().anyMatch(username.toLowerCase()::contains)) codes.add(4);
    }
    private static void checkUsernameIsUnique(String username, List <Integer> codes) {
        // TODO
    }
    private static void checkTooShortPassword(String password, List <Integer> codes) {
        if (password.length() < MIN_PASSWORD_LENGTH) codes.add(6);
    }
    private static void checkTooLongPassword(String password, List <Integer> codes) {
        if (password.length() > MAX_PASSWORD_LENGTH) codes.add(7);
    }
    private static void checkPasswordContainsUsername(String password, List <Integer> codes, String username) {
        if (password.contains(username)) codes.add(8);
    }
    private static void checkPasswordDoesntContainCapital(String password, List <Integer> codes) {
        if (password.chars().noneMatch(Character::isUpperCase)) codes.add(9);
    }
    private static void checkPasswordDoesntContainLower(String password, List <Integer> codes) {
        if (password.chars().noneMatch(Character::isLowerCase)) codes.add(10);
    }
    private static void checkPasswordDoesntContainNumber(String password, List <Integer> codes) {
        if (password.chars().noneMatch(Character::isDigit)) codes.add(11);
    }
    private static void checkPasswordDoesntContainSpecialChar(String password, List <Integer> codes) {
        if (!password.matches(SPECIAL_CHARACTERS_REGEX)) codes.add(12);
    }
    private static void checkTooShortName(String name, List <Integer> codes) {
        if (name.length() < MIN_NAME_LENGTH) codes.add(13);
    }
    private static void checkTooLongName(String name, List <Integer> codes) {
        if (name.length() > MAX_NAME_LENGTH) codes.add(14);
    }
    private static void checkTooShortSurname(String surname, List <Integer> codes) {
        if (surname.length() < MIN_SURNAME_LENGTH) codes.add(15);
    }
    private static void checkTooLongSurname(String surname, List <Integer> codes) {
        if (surname.length() > MAX_SURNAME_LENGTH) codes.add(16);
    }
    private static void checkTooShortEmail(String email, List <Integer> codes) {
        if (email.length() < MIN_EMAIL_LENGTH) codes.add(17);
    }
    private static void checkTooLongEmail(String email, List <Integer> codes) {
        if (email.length() > MAX_EMAIL_LENGTH) codes.add(18);
    }
    private static void checkEmailDoesntContainAt(String email, List <Integer> codes) {
        if (!email.contains("@")) codes.add(19);
    }
    private static void checkTooShortPhoneNumber(String phoneNumber, List <Integer> codes) {
        if (phoneNumber.length() < MIN_PHONE_LENGTH) codes.add(20);
    }
    private static void checkTooLongPhoneNumber(String phoneNumber, List <Integer> codes) {
        if (phoneNumber.length() > MAX_PHONE_LENGTH) codes.add(21);
    }
    private static void checkInvalidBirthDate(LocalDate birthDate, List <Integer> codes) {
        if (!birthDate.isBefore(LocalDate.now())) codes.add(31);
    }
    private static void checkTooShortNationality(String nationality, List <Integer> codes) {
        if (nationality.length() < MIN_NATIONALITY_LENGTH) codes.add(32);
    }
    private static void checkTooLongNationality(String nationality, List <Integer> codes) {
        if (nationality.length() > MAX_NATIONALITY_LENGTH) codes.add(33);
    }
    private static void checkGender(String gender, List <Integer> codes) {
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female") && !gender.equalsIgnoreCase("other"))
            codes.add(34);
    }
}
