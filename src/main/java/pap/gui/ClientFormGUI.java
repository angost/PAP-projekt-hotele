package pap.gui;

import pap.logic.add.AddNewUser;
import pap.logic.validators.ClientValidator;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.List;

public class ClientFormGUI extends RegisterUserFormTemplate {

    public ClientFormGUI(int userId, String userType) {
        super(userId, userType);
        pageName = "Create Client Account";
    }

    @Override
    String[] getFieldLabels() {
        String[] fieldLabels = {"Username", "Password", "Name", "Surname", "Date of birth", "Gender", "Nationality", "Email", "Phone number", "Country", "City", "Street", "Street number", "Postal Code"};
        return fieldLabels;
    }

    @Override
    String[] getFieldTypes() {
        String[] fieldTypes = {"text", "password", "text", "text", "comboBoxDate",  "text", "text", "text", "text", "text", "text", "text", "text", "text"};
        return fieldTypes;
    }

    @Override
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

    @Override
    List<Integer> validateCredentials(HashMap<String, String> textFieldsValues) {
        List <Integer> errorCodes = new ClientValidator(textFieldsValues.get("Username"), textFieldsValues.get("Password"), textFieldsValues.get("Name"), textFieldsValues.get("Surname"),
                textFieldsValues.get("Email"), textFieldsValues.get("Phone number"), textFieldsValues.get("Country"), textFieldsValues.get("City"),
                textFieldsValues.get("Street"), textFieldsValues.get("Postal Code"), textFieldsValues.get("Street number"), LocalDate.parse(textFieldsValues.get("Date of birth")),
                textFieldsValues.get("Nationality"), textFieldsValues.get("Gender")).validateCredentials();
        return errorCodes;
    }

    @Override
    void createUser(HashMap<String, String> textFieldsValues) {
        new AddNewUser(textFieldsValues.get("Username"), textFieldsValues.get("Password"), textFieldsValues.get("Name"), textFieldsValues.get("Surname"),
                textFieldsValues.get("Email"), textFieldsValues.get("Phone number"), textFieldsValues.get("Country"), textFieldsValues.get("City"),
                textFieldsValues.get("Street"), textFieldsValues.get("Postal Code"), textFieldsValues.get("Street number"), LocalDate.parse(textFieldsValues.get("Date of birth")),
                textFieldsValues.get("Nationality"), textFieldsValues.get("Gender"), true).insertIntoDatabase();
    }


    public static void main(String[] args) {
        new ClientFormGUI(-1, "None").createGUI();
    }
}
