
import javax.swing.*;
import java.awt.*;
import java.time.Year;

public class UserFormGUI extends FormGUITemplate {


    String[] getFieldLabels() {
        String[] fieldLabels = {"Name", "Surname", "Gender", "Date of birth", "Country", "City", "Street", "Street number", "Postal Code", "Phone number", "Email address", "Payment option"};
        return fieldLabels;
    }

    String[] getFieldTypes() {
        String[] fieldTypes = {"text", "text", "radioButton", "comboBoxInteger", "text", "text", "text", "text", "text", "text", "text", "comboBoxString"};
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

        Object[] fieldParameters = {20, 30, new String[]{"Male", "Female"}, new Integer[][]{days, months, years}, 20, 20, 30, 10, 10, 15, 30, new String[][]{new String[]{"Credit card", "Debet card", "Bank transfer", "BLIK", "Cash"}}};

        return fieldParameters;
    }

    public static void main(String[] args) {
        new UserFormGUI().createGUI();
    }
}
