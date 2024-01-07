package pap.gui;

import pap.logic.add.AddNewOwner;
import pap.logic.validators.OwnerValidator;

import java.time.Year;
import java.util.HashMap;
import java.util.List;

public class OwnerFormGUI extends FormGUITemplate {

    public OwnerFormGUI(int userId, String userType) {
        super(userId, userType);
        pageName = "Create Owner Account";
    }

    String[] getFieldLabels() {
        String[] fieldLabels = {"Username", "Password", "Company name", "Email", "Phone number", "NIP", "Country", "City", "Street", "Street number", "Postal Code"};
        return fieldLabels;
    }

    String[] getFieldTypes() {
        String[] fieldTypes = {"text", "password", "text", "text", "text", "text", "text", "text", "text", "text", "text"};
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

        Object[] fieldParameters = {15, 15, 20, 20, 15, 10, 15, 15, 15, 6, 6};

        return fieldParameters;
    }

    List<Integer> validateCredentials(HashMap<String, String> textFieldsValues) {
        List <Integer> errorCodes = new OwnerValidator(textFieldsValues.get("Username"), textFieldsValues.get("Password"), textFieldsValues.get("Company name"),
                textFieldsValues.get("Email"), textFieldsValues.get("Phone number"), textFieldsValues.get("Country"), textFieldsValues.get("City"),
                textFieldsValues.get("Street"), textFieldsValues.get("Postal Code"), textFieldsValues.get("Street number"), textFieldsValues.get("NIP")).validateOwnerCredentials();
        return errorCodes;
    }

    void createUser(HashMap<String, String> textFieldsValues) {
        new AddNewOwner(textFieldsValues.get("Username"), textFieldsValues.get("Password"), textFieldsValues.get("Company name"),
                textFieldsValues.get("Email"), textFieldsValues.get("Phone number"), textFieldsValues.get("Country"), textFieldsValues.get("City"),
                textFieldsValues.get("Street"), textFieldsValues.get("Postal Code"), textFieldsValues.get("Street number"), textFieldsValues.get("NIP"),
                false, true).insertIntoDatabase();
    }

    public static void main(String[] args) {
        new OwnerFormGUI(-1, "None").createGUI();
    }
}
