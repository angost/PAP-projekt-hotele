package pap.logic;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

public class AddressValidator {
    private static final int MIN_COUNTRY_LENGTH = 2;
    private static final int MAX_COUNTRY_LENGTH = 64;
    private static final int MIN_CITY_LENGTH = 2;
    private static final int MAX_CITY_LENGTH = 64;
    private static final int MIN_STREET_LENGTH = 2;
    private static final int MAX_STREET_LENGTH = 64;
    private static final int MIN_POSTAL_CODE_LENGTH = 5;
    private static final int MAX_POSTAL_CODE_LENGTH = 32;
    private static final int MIN_STREET_NO_LENGTH = 1;
    private static final int MAX_STREET_NO_LENGTH = 16;

    private final String country;
    private final String city;
    private final String street;
    private final String postalCode;
    private final String streetNo;

    public AddressValidator(String country, String city, String street, String postalCode, String streetNo) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.streetNo = streetNo;
    }

    public List <Integer> validateCredentials() {
        List <Integer> codes = new ArrayList<>();
        validateCountry(country, codes);
        validateCity(city, codes);
        validateStreet(street, codes);
        validatePostalCode(postalCode, codes);
        validateStreetNo(streetNo, codes);
        return codes;
    }

    public static void validateCountry(String country, List <Integer> codes) {
        checkTooShortCountry(country, codes);
        checkTooLongCountry(country, codes);
    }
    public static void validateCity(String city, List <Integer> codes) {
        checkTooShortCity(city, codes);
        checkTooLongCity(city, codes);
    }

    public static void validateStreet(String street, List <Integer> codes) {
        checkTooShortStreet(street, codes);
        checkTooLongStreet(street, codes);
    }

    public static void validatePostalCode(String postalCode, List <Integer> codes) {
        checkTooShortPostalCode(postalCode, codes);
        checkTooLongPostalCode(postalCode, codes);
    }

    public static void validateStreetNo(String streetNo, List <Integer> codes) {
        checkTooShortStreetNo(streetNo, codes);
        checkTooLongStreetNo(streetNo, codes);
        checkStreetNoDoesntContainNumber(streetNo, codes);
    }

    private static void checkTooShortCountry(String country, List <Integer> codes) {
        if (country.length() < MIN_COUNTRY_LENGTH) codes.add(51);
    }
    private static void checkTooLongCountry(String country, List <Integer> codes) {
        if (country.length() > MAX_COUNTRY_LENGTH) codes.add(52);
    }
    private static void checkTooShortCity(String city, List <Integer> codes) {
        if (city.length() < MIN_CITY_LENGTH) codes.add(53);
    }
    private static void checkTooLongCity(String city, List <Integer> codes) {
        if (city.length() > MAX_CITY_LENGTH) codes.add(54);
    }
    private static void checkTooShortStreet(String street, List <Integer> codes) {
        if (street.length() < MIN_STREET_LENGTH) codes.add(55);
    }
    private static void checkTooLongStreet(String street, List <Integer> codes) {
        if (street.length() > MAX_STREET_LENGTH) codes.add(56);
    }
    private static void checkTooShortPostalCode(String postalCode, List <Integer> codes) {
        if (postalCode.length() < MIN_POSTAL_CODE_LENGTH) codes.add(57);
    }
    private static void checkTooLongPostalCode(String postalCode, List <Integer> codes) {
        if (postalCode.length() > MAX_POSTAL_CODE_LENGTH) codes.add(58);
    }
    private static void checkTooShortStreetNo(String streetNo, List <Integer> codes) {
        if (streetNo.length() < MIN_STREET_NO_LENGTH) codes.add(59);
    }
    private static void checkTooLongStreetNo(String streetNo, List <Integer> codes) {
        if (streetNo.length() > MAX_STREET_NO_LENGTH) codes.add(60);
    }
    private static void checkStreetNoDoesntContainNumber(String streetNo, List <Integer> codes) {
        if (!streetNo.matches(".*\\d.*")) codes.add(61);
    }
}