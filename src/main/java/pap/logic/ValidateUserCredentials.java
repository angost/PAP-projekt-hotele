package pap.logic;

import java.util.*;

public class ValidateUserCredentials {
    private static final int MIN_USERNAME_LENGTH = 8;
    private static final int MAX_USERNAME_LENGTH = 64;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 64;
    private static final String USERNAME_ILLEGAL_CHARS_REGEX = ".*[@#$%&/\\\\].*";
    private static final List<String> BANNED_KEYWORDS = Arrays.asList("admin", "root", "system");
    private static final String SPECIAL_CHARACTERS_REGEX = ".*[!@#$%^&*()\\[\\]{}\\-_=+/\\\\?.,<>'\";:|§].*";

    public ValidateUserCredentials() {}

    public static List <Integer> validateCredentials(String username, String password) {
        List <Integer> codes = new ArrayList<>();
        validateUsername(username, codes);
        validatePassword(password, codes, username);
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
}