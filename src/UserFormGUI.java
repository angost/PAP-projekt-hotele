
import javax.swing.*;
import java.awt.*;
import java.time.Year;

public class UserFormGUI extends FormGUITemplate {


    String[] getFieldLabels() {
        String[] fieldLabels = {"Username", "Password", "Name", "Surname", "Date of birth", "Gender", "Nationality", "Email", "Phone number", "Country", "City", "Street", "Street number", "Postal Code"};
        return fieldLabels;
    }

    String[] getFieldTypes() {
        String[] fieldTypes = {"text", "text", "text", "text", "comboBoxInteger",  "radioButton", "text", "text", "text", "text", "text", "text", "text", "text"};
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

        Object[] fieldParameters = {15, 15, 15, 15, new Integer[][]{days, months, years}, new String[]{"Male", "Female"}, 15, 20, 10, 15, 15, 15, 6, 6};
        // new String[][]{new String[]{"Credit card", "Debet card", "Bank transfer", "BLIK", "Cash"}}
        return fieldParameters;
    }

    public static void main(String[] args) {
        new UserFormGUI().createGUI();
    }
}
