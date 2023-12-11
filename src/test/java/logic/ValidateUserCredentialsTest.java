package logic;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;
import pap.logic.ValidateUserCredentials;


public class ValidateUserCredentialsTest {

    @Test
    public void testValidateUserCredentials_TooShortUsername() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("short", "ValidPassword1!");
        assertEquals(List.of(1), result);
    }

    @Test
    public void testValidateUserCredentials_TooLongUsername() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("a".repeat(65), "ValidPassword1!");
        assertEquals(List.of(2), result);
    }

    @Test
    public void testValidateUserCredentials_UsernameContainsIllegalChar() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("user@name", "ValidPassword1!");
        assertEquals(List.of(3), result);
    }

    @Test
    public void testValidateUserCredentials_UsernameContainsIllegalKeyword() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("adminUser", "ValidPassword1!");
        assertEquals(List.of(4), result);
    }

    @Test
    public void testValidateUserCredentials_TooShortPassword() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "Short1!");
        assertEquals(List.of(6), result);
    }

    @Test
    public void testValidateUserCredentials_TooLongPassword() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "a".repeat(65) + "A1!");
        assertEquals(List.of(7), result);
    }

    @Test
    public void testValidateUserCredentials_PasswordContainsUsername() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "ValidUsername1!");
        assertEquals(List.of(8), result);
    }

    @Test
    public void testValidateUserCredentials_PasswordDoesntContainCapital() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "validpassword1!");
        assertEquals(List.of(9), result);
    }

    @Test
    public void testValidateUserCredentials_PasswordDoesntContainLower() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "VALIDPASSWORD1!");
        assertEquals(List.of(10), result);
    }

    @Test
    public void testValidateUserCredentials_PasswordDoesntContainNumber() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "ValidPassword!");
        assertEquals(List.of(11), result);
    }

    @Test
    public void testValidateUserCredentials_PasswordDoesntContainSpecialChar() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "ValidPassword1");
        assertEquals(List.of(12), result);
    }

    @Test
    public void testValidateUserCredentials_CorrectCredentials() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "ValidPassword1!");
        assertEquals(List.of(), result);
    }

    @Test
    public void testValidateUserCredentials_UsernameTooShortAndPasswordTooLong() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("Usr", "a".repeat(65) + "ValidPassword1!");
        assertEquals(List.of(1, 7), result);
    }

    @Test
    public void testValidateUserCredentials_UsernameTooLongAndContainsIllegalChar() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("a".repeat(65) + "$", "ValidPassw0rd!");
        assertEquals(List.of(2, 3), result);
    }

    @Test
    public void testValidateUserCredentials_UsernameContainsMultipleIllegalKeywords() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("AdminUserRoot", "ValidPassword1!");
        assertEquals(List.of(4), result);
    }

    @Test
    public void testValidateUserCredentials_PasswordTooShortAndDoesntContainSpecialChar() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "Pwd1");
        assertEquals(List.of(6, 12), result);
    }

    @Test
    public void testValidateUserCredentials_PasswordTooLongAndContainsUsername() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "a".repeat(65) + "ValidUsername1!");
        assertEquals(List.of(7, 8), result);
    }

    @Test
    public void testValidateUserCredentials_UsernameContainsIllegalCharAndPasswordContainsSpecialChar() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("User@Name", "SecurePwd1!");
        assertEquals(List.of(3), result);
    }

    @Test
    public void testValidateUserCredentials_UsernameContainsIllegalKeywordAndPasswordDoesntContainLower() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("AdminUser", "SECUREPWD1!");
        assertEquals(List.of(4, 10), result);
    }

    @Test
    public void testValidateUserCredentials_PasswordContainsCapitalLowerNumberAndSpecialChar() {
        List<Integer> result = ValidateUserCredentials.validateCredentials("ValidUsername", "SecurePwd1!");
        assertEquals(List.of(), result);
    }
}
