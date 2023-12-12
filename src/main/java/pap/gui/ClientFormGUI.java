package pap.gui;

import pap.logic.validators.UserCredentialValidator;
import pap.logic.add.AddNewUser;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.List;

public class ClientFormGUI extends FormGUITemplate {


    String[] getFieldLabels() {
        String[] fieldLabels = {"Username", "Password", "Name", "Surname", "Date of birth", "Gender", "Nationality", "Email", "Phone number", "Country", "City", "Street", "Street number", "Postal Code"};
        return fieldLabels;
    }

    String[] getFieldTypes() {
        String[] fieldTypes = {"text", "text", "text", "text", "comboBoxInteger",  "text", "text", "text", "text", "text", "text", "text", "text", "text"};
        return fieldTypes;
    }

    Object[] getFieldParameters() {

        Integer[] days = new Integer[31];
        for (int i=0; i < days.length; i++) {
            days[i] = i+1;
        }
        Integer[] months = new Integer[12];
        for (int i=0; i < months.length; i++) {
            months[i] = i+1;
        }

        int baseYear = Year.now().getValue();
        Integer[] years = new Integer[110];
        for (int i=0; i < years.length; i++) {
            years[i] = baseYear-i;
        }

        Object[] fieldParameters = {15, 15, 15, 15, new Integer[][]{days, months, years}, 10, 15, 20, 10, 15, 15, 15, 6, 6};
        // new String[][]{new String[]{"Credit card", "Debet card", "Bank transfer", "BLIK", "Cash"}}
        return fieldParameters;
    }


    List<Integer> validateCredentials(HashMap<String, String> textFieldsValues) {
        List <Integer> errorCodes = new UserCredentialValidator(textFieldsValues.get("Username"), textFieldsValues.get("Password"), textFieldsValues.get("Name"), textFieldsValues.get("Surname"),
                textFieldsValues.get("Email"), textFieldsValues.get("Phone number"), textFieldsValues.get("Country"), textFieldsValues.get("City"),
                textFieldsValues.get("Street"), textFieldsValues.get("Postal Code"), textFieldsValues.get("Street number"), LocalDate.parse("2003-01-14"),
                textFieldsValues.get("Nationality"), textFieldsValues.get("Gender")).validateCredentials();
        return errorCodes;
    }

    void createUser(HashMap<String, String> textFieldsValues) {
        new AddNewUser(textFieldsValues.get("Username"), textFieldsValues.get("Password"), textFieldsValues.get("Name"), textFieldsValues.get("Surname"),
                textFieldsValues.get("Email"), textFieldsValues.get("Phone number"), textFieldsValues.get("Country"), textFieldsValues.get("City"),
                textFieldsValues.get("Street"), textFieldsValues.get("Postal Code"), textFieldsValues.get("Street number"), LocalDate.parse("2003-01-14"),
                textFieldsValues.get("Nationality"), textFieldsValues.get("Gender"), true).insertIntoDatabase();
    }


    public static void main(String[] args) {
        new ClientFormGUI().createGUI();
    }
}
