package logic;
import java.util.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import pap.logic.OwnerCredentialValidator;

public class OwnerCredentialValidatorTest {

    @Test
    public void testValidateCredentials_CorrectCredentials() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "validUsername", "ValidPassword1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidUsername_TooShort() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "z", "ValidPassword1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(101), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidUsername_TooLong() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "a".repeat(65), "ValidPassword1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(102), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidUsername_IllegalChar() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "user@name", "ValidPassword1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(103), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidUsername_IllegalKeyword() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "adminUser", "ValidPassword1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(104), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidPassword_TooShort() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "Pwd1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(106), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidPassword_TooLong() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "a".repeat(65) + "ValidPassword1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(107), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidPassword_ContainsUsername() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidUsername1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(108), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidPassword_NoCapital() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "validpassword1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(109), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidPassword_NoLower() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "VALIDPASSWORD1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(110), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidPassword_NoNumber() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(111), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidPassword_NoSpecialChar() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(112), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidCompanyName_TooShort() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1!", "A", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(113), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidCompanyName_TooLong() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1!", "A".repeat(65), "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(114), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidEmail_TooShort() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1!", "ValidCompany", "a@b",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(115), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidEmail_TooLong() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1!", "ValidCompany", "a".repeat(65) + "@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(116), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidEmail_NoAtSymbol() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1!", "ValidCompany", "invalidemail.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(117), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidPhoneNumber_TooShort() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1!", "ValidCompany", "valid@example.com",
                "123", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(118), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidPhoneNumber_TooLong() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1!", "ValidCompany", "valid@example.com",
                "1234567890123456", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567890"
        );
        assertEquals(List.of(119), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidNip_TooShort() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "12345678"
        );
        assertEquals(List.of(120, 122), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidNip_NonNumeric() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "12345678aa"
        );
        assertEquals(List.of(121, 122), validator.validateCredentials());
    }

    @Test
    public void testValidateCredentials_InvalidNip_InvalidChecksum() {
        OwnerCredentialValidator validator = new OwnerCredentialValidator(
                "ValidUsername", "ValidPassword1!", "ValidCompany", "valid@example.com",
                "123456789", "Poland", "Warsaw", "Nowogrodzka", "00-000", "20/3", "1234567891"
        );
        assertEquals(List.of(122), validator.validateCredentials());
    }
}
