package logic;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import pap.logic.UserCredentialValidator;
import java.time.LocalDate;

public class UserCredentialValidatorTest {

    @Test
    public void testUserCredentialsValidator_TooShortUsername() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("short", result);
        assertEquals(List.of(1), result);
    }

    @Test
    public void testUserCredentialsValidator_TooLongUsername() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("a".repeat(65), result);
        assertEquals(List.of(2), result);
    }

    @Test
    public void testUserCredentialsValidator_UsernameContainsIllegalChar() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("user@name", result);
        assertEquals(List.of(3), result);
    }

    @Test
    public void testUserCredentialsValidator_UsernameContainsIllegalKeyword() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("adminUser", result);
        assertEquals(List.of(4), result);
    }

    @Test
    public void testUserCredentialsValidator_TooShortPassword() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validatePassword("Short1!", result, "ValidUsername");
        assertEquals(List.of(6), result);
    }

    @Test
    public void testUserCredentialsValidator_TooLongPassword() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validatePassword("a".repeat(65) + "A!1", result, "ValidUsername");
        assertEquals(List.of(7), result);
    }

    @Test
    public void testUserCredentialsValidator_PasswordContainsUsername() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validatePassword("ValidUsername1!", result, "ValidUsername");
        assertEquals(List.of(8), result);
    }

    @Test
    public void testUserCredentialsValidator_PasswordDoesntContainCapital() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validatePassword("validpassword1!", result, "ValidUsername");
        assertEquals(List.of(9), result);
    }

    @Test
    public void testUserCredentialsValidator_PasswordDoesntContainLower() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validatePassword("VALIDPASSWORD1!", result, "ValidUsername");
        assertEquals(List.of(10), result);
    }

    @Test
    public void testUserCredentialsValidator_PasswordDoesntContainNumber() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validatePassword("ValidPassword!", result, "ValidUsername");
        assertEquals(List.of(11), result);
    }

    @Test
    public void testUserCredentialsValidator_PasswordDoesntContainSpecialChar() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validatePassword("ValidPassword1", result, "ValidUsername");
        assertEquals(List.of(12), result);
    }

    @Test
    public void testUserCredentialsValidator_CorrectCredentials() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validatePassword("ValidPassword1!", result, "ValidUsername");
        assertEquals(List.of(), result);
    }

    @Test
    public void testUserCredentialsValidator_UsernameTooShortAndPasswordTooLong() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("Usr", result);
        UserCredentialValidator.validatePassword("a".repeat(65) + "ValidPassword1!", result, "Usr");
        assertEquals(List.of(1, 7), result);
    }

    @Test
    public void testUserCredentialsValidator_UsernameTooLongAndContainsIllegalChar() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("a".repeat(65) + "$", result);
        UserCredentialValidator.validatePassword("ValidPassw0rd!", result, "a".repeat(65) + "$");
        assertEquals(List.of(2, 3), result);
    }

    @Test
    public void testUserCredentialsValidator_UsernameContainsMultipleIllegalKeywords() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("AdminUserRoot", result);
        UserCredentialValidator.validatePassword("ValidPassword1!", result, "AdminUserRoot");
        assertEquals(List.of(4), result);
    }

    @Test
    public void testUserCredentialsValidator_PasswordTooShortAndDoesntContainSpecialChar() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("ValidUsername", result);
        UserCredentialValidator.validatePassword("Pwd1", result, "ValidUsername");
        assertEquals(List.of(6, 12), result);
    }

    @Test
    public void testUserCredentialsValidator_PasswordTooLongAndContainsUsername() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("ValidUsername", result);
        UserCredentialValidator.validatePassword("a".repeat(65) + "ValidUsername1!", result, "ValidUsername");
        assertEquals(List.of(7, 8), result);
    }

    @Test
    public void testUserCredentialsValidator_UsernameContainsIllegalCharAndPasswordContainsSpecialChar() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("User@Name", result);
        UserCredentialValidator.validatePassword("SecurePwd1!", result, "User@Name");
        assertEquals(List.of(3), result);
    }

    @Test
    public void testUserCredentialsValidator_UsernameContainsIllegalKeywordAndPasswordDoesntContainLower() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("AdminUser", result);
        UserCredentialValidator.validatePassword("SECUREPWD1!", result, "AdminUser");
        assertEquals(List.of(4, 10), result);
    }

    @Test
    public void testUserCredentialsValidator_PasswordContainsCapitalLowerNumberAndSpecialChar() {
        List <Integer> result = new ArrayList<>();
        UserCredentialValidator.validateUsername("ValidUsername", result);
        UserCredentialValidator.validatePassword("SecurePwd1!", result, "ValidUsername");
        assertEquals(List.of(), result);
    }

    @Test
    public void testUserCredentialsValidator_TooShortName() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateName("J", codes);
        assertEquals(List.of(13), codes);
    }

    @Test
    public void testUserCredentialsValidator_TooLongName() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateName("a".repeat(65), codes);
        assertEquals(List.of(14), codes);
    }

    @Test
    public void testUserCredentialsValidator_TooShortSurname() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateSurname("D", codes);
        assertEquals(List.of(15), codes);
    }

    @Test
    public void testUserCredentialsValidator_TooLongSurname() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateSurname("a".repeat(65), codes);
        assertEquals(List.of(16), codes);
    }

    @Test
    public void testUserCredentialsValidator_TooShortEmail() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateEmail("a@bc", codes);
        assertEquals(List.of(17), codes);
    }

    @Test
    public void testUserCredentialsValidator_TooLongEmail() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateEmail("a".repeat(65) + "@", codes);
        assertEquals(List.of(18), codes);
    }

    @Test
    public void testUserCredentialsValidator_EmailDoesNotContainAt() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateEmail("invalid-email", codes);
        assertEquals(List.of(19), codes);
    }

    @Test
    public void testUserCredentialsValidator_TooShortPhoneNumber() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validatePhoneNumber("123", codes);
        assertEquals(List.of(20), codes);
    }

    @Test
    public void testUserCredentialsValidator_TooLongPhoneNumber() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validatePhoneNumber("12345678901234567890", codes);
        assertEquals(List.of(21), codes);
    }

    @Test
    public void testUserCredentialsValidator_InvalidBirthDate() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateBirthDate(LocalDate.now(), codes);
        assertEquals(List.of(31), codes);
    }

    @Test
    public void testUserCredentialsValidator_TooShortNationality() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateNationality("U", codes);
        assertEquals(List.of(32), codes);
    }

    @Test
    public void testUserCredentialsValidator_TooLongNationality() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateNationality("a".repeat(65), codes);
        assertEquals(List.of(33), codes);
    }

    @Test
    public void testUserCredentialsValidator_InvalidGender() {
        List<Integer> codes = new ArrayList<>();
        UserCredentialValidator.validateGender("unknown", codes);
        assertEquals(List.of(34), codes);
    }

    @Test
    public void testUserCredentialsValidator_ValidateCredentials() {
        UserCredentialValidator vuc = new UserCredentialValidator("ValidUsername", "InvalidPassword", "Jan", "Kowalski",
                "jankowalski@pw.edu.pl", "123456789", "Poland", "Warsaw", "Nowowiejska", "00-123", "1",
                LocalDate.parse("2020-01-01"), "Polish", "Male");
        List <Integer> codes = vuc.validateCredentials();
        assertEquals(List.of(11, 12), codes);
    }
}

