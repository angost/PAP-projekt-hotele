package logic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import pap.logic.AddressValidator;


public class AddressValidatorTest {
    @Test
    public void validateCountry_ValidCountry_ShouldBeEmpty() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateCountry("Poland", codes);
        assertEquals(List.of(), codes);
    }

    @Test
    public void validateCountry_TooShortCountry_ShouldContain51() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateCountry("P", codes);
        assertEquals(List.of(51), codes);
    }

    @Test
    public void validateCountry_TooLongCountry_ShouldContain52() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateCountry("a".repeat(65), codes);
        assertEquals(List.of(52), codes);
    }

    @Test
    public void validateCity_ValidCity_ShouldBeEmpty() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateCity("Warsaw", codes);
        assertEquals(List.of(), codes);
    }

    @Test
    public void validateCity_TooShortCity_ShouldContain53() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateCity("W", codes);
        assertEquals(List.of(53), codes);
    }

    @Test
    public void validateCity_TooLongCity_ShouldContain54() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateCity("a".repeat(65), codes);
        assertEquals(List.of(54), codes);
    }

    @Test
    public void validateStreet_ValidStreet_ShouldBeEmpty() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateStreet("Main Street", codes);
        assertEquals(List.of(), codes);
    }

    @Test
    public void validateStreet_TooShortStreet_ShouldContain55() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateStreet("M", codes);
        assertEquals(List.of(55), codes);
    }

    @Test
    public void validateStreet_TooLongStreet_ShouldContain56() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateStreet("a".repeat(65), codes);
        assertEquals(List.of(56), codes);
    }

    @Test
    public void validatePostalCode_ValidPostalCode_ShouldBeEmpty() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validatePostalCode("12345", codes);
        assertEquals(List.of(), codes);
    }

    @Test
    public void validatePostalCode_TooShortPostalCode_ShouldContain57() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validatePostalCode("1234", codes);
        assertEquals(List.of(57), codes);
    }

    @Test
    public void validatePostalCode_TooLongPostalCode_ShouldContain58() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validatePostalCode("a".repeat(65) + "1", codes);
        assertEquals(List.of(58), codes);
    }

    @Test
    public void validateStreetNo_ValidStreetNo_ShouldBeEmpty() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateStreetNo("42", codes);
        assertEquals(List.of(), codes);
    }

    @Test
    public void validateStreetNo_TooShortStreetNo_ShouldContain59() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateStreetNo("", codes);
        assertEquals(List.of(59, 61), codes);
    }

    @Test
    public void validateStreetNo_TooLongStreetNo_ShouldContain60() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateStreetNo("a".repeat(65) + "1", codes);
        assertEquals(List.of(60), codes);
    }

    @Test
    public void validateStreetNo_StreetNoWithoutNumber_ShouldContain61() {
        List<Integer> codes = new ArrayList<>();
        AddressValidator.validateStreetNo("NoNumbers", codes);
        assertEquals(List.of(61), codes);
    }
}
