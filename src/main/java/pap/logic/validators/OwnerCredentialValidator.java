package pap.logic.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pap.db.dao.*;

public class OwnerCredentialValidator {
    private static final int MIN_USERNAME_LENGTH = 8;
    private static final int MAX_USERNAME_LENGTH = 64;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 64;
    private static final int MIN_COMPANY_NAME_LENGTH = 2;
    private static final int MAX_COMPANY_NAME_LENGTH = 64;
    private static final int MIN_EMAIL_LENGTH = 5;
    private static final int MAX_EMAIL_LENGTH = 64;
    private static final int MIN_PHONE_LENGTH = 9;
    private static final int MAX_PHONE_LENGTH = 14;
    private static final int NIP_LENGTH = 10;
    private static final String USERNAME_ILLEGAL_CHARS_REGEX = ".*[@#$%&/\\\\].*";
    private static final List<String> BANNED_KEYWORDS = Arrays.asList("admin", "root", "system");
    private static final String SPECIAL_CHARACTERS_REGEX = ".*[!@#$%^&*()\\[\\]{}\\-_=+/\\\\?.,<>'\";:|§].*";

    private final String username;
    private final String password;
    private final String companyName;
    private final String email;
    private final String phoneNumber;
    private final AddressValidator addressValidator;
    private final String nip;

    public OwnerCredentialValidator(String username, String password, String companyName, String email, String phoneNumber,
                                    String country, String city, String street, String postalCode, String streetNo, String nip) {
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addressValidator = new AddressValidator(country, city, street, postalCode, streetNo);
        this.nip = nip;
    }

    public List <Integer> validateCredentials() {
        List <Integer> codes = new ArrayList<>();
        validateUsername(username, codes);
        validatePassword(password, codes, username);
        validateCompanyName(companyName, codes);
        validateEmail(email, codes);
        validatePhoneNumber(phoneNumber, codes);
        validateAddress(addressValidator, codes);
        validateNip(nip, codes);
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

    public static void validateCompanyName(String companyName, List <Integer> codes) {
        checkTooShortCompanyName(companyName, codes);
        checkTooLongCompanyName(companyName, codes);
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
        codes.addAll(addressValidator.validateCredentials());
    }

    public static void validateNip(String nip, List <Integer> codes) {
        checkNipLength(nip, codes);
        checkNipNumbers(nip, codes);
        checkNipChecksum(nip, codes);
    }

    private static void checkTooShortUsername(String username, List<Integer> codes) {
        if (username.length() < MIN_USERNAME_LENGTH) codes.add(101);
    }
    private static void checkTooLongUsername(String username, List <Integer> codes) {
        if (username.length() > MAX_USERNAME_LENGTH) codes.add(102);
    }
    private static void checkUsernameContainsIllegalChar(String username, List <Integer> codes) {
        if (username.matches(USERNAME_ILLEGAL_CHARS_REGEX)) codes.add(103);
    }
    private static void checkUsernameContainsIllegalKeyword(String username, List <Integer> codes) {
        if (BANNED_KEYWORDS.stream().anyMatch(username.toLowerCase()::contains)) codes.add(104);
    }
    private static void checkUsernameIsUnique(String username, List <Integer> codes) {
        try {
            new OwnerDAO().findByUsername(username);
            codes.add(105);
        } catch (Exception ignored) {}
    }
    private static void checkTooShortPassword(String password, List <Integer> codes) {
        if (password.length() < MIN_PASSWORD_LENGTH) codes.add(106);
    }
    private static void checkTooLongPassword(String password, List <Integer> codes) {
        if (password.length() > MAX_PASSWORD_LENGTH) codes.add(107);
    }
    private static void checkPasswordContainsUsername(String password, List <Integer> codes, String username) {
        if (password.contains(username)) codes.add(108);
    }
    private static void checkPasswordDoesntContainCapital(String password, List <Integer> codes) {
        if (password.chars().noneMatch(Character::isUpperCase)) codes.add(109);
    }
    private static void checkPasswordDoesntContainLower(String password, List <Integer> codes) {
        if (password.chars().noneMatch(Character::isLowerCase)) codes.add(110);
    }
    private static void checkPasswordDoesntContainNumber(String password, List <Integer> codes) {
        if (password.chars().noneMatch(Character::isDigit)) codes.add(111);
    }
    private static void checkPasswordDoesntContainSpecialChar(String password, List <Integer> codes) {
        if (!password.matches(SPECIAL_CHARACTERS_REGEX)) codes.add(112);
    }
    private static void checkTooShortCompanyName(String companyName, List <Integer> codes) {
        if (companyName.length() < MIN_COMPANY_NAME_LENGTH) codes.add(113);
    }
    private static void checkTooLongCompanyName(String companyName, List <Integer> codes) {
        if (companyName.length() > MAX_COMPANY_NAME_LENGTH) codes.add(114);
    }
    private static void checkTooShortEmail(String email, List <Integer> codes) {
        if (email.length() < MIN_EMAIL_LENGTH) codes.add(115);
    }
    private static void checkTooLongEmail(String email, List <Integer> codes) {
        if (email.length() > MAX_EMAIL_LENGTH) codes.add(116);
    }
    private static void checkEmailDoesntContainAt(String email, List <Integer> codes) {
        if (!email.contains("@")) codes.add(117);
    }
    private static void checkTooShortPhoneNumber(String phoneNumber, List <Integer> codes) {
        if (phoneNumber.length() < MIN_PHONE_LENGTH) codes.add(118);
    }
    private static void checkTooLongPhoneNumber(String phoneNumber, List <Integer> codes) {
        if (phoneNumber.length() > MAX_PHONE_LENGTH) codes.add(119);
    }
    private static void checkNipLength(String nip, List <Integer> codes) {
        if (nip.length() != NIP_LENGTH) codes.add(120);
    }
    private static void checkNipNumbers(String nip, List <Integer> codes) {
        if (!nip.matches("\\d+")) codes.add(121);
    }
    private static void checkNipChecksum(String nip, List <Integer> codes) {
        if (nip == null || nip.length() != 10 || !nip.matches("\\d{10}")) {
            codes.add(122);
            return;
        }
        int[] weights = {6, 5, 7, 2, 3, 4, 5, 6, 7};
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += Integer.parseInt(String.valueOf(nip.charAt(i))) * weights[i];
        }
        int controlNumber = sum % 11;
        if (controlNumber == 10) controlNumber = 0;
        int lastDigit = Integer.parseInt(String.valueOf(nip.charAt(9)));
        if (controlNumber != lastDigit) codes.add(122);
    }
}
