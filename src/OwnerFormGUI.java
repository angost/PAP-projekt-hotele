
import javax.swing.*;
import java.awt.*;
import java.time.Year;

public class OwnerFormGUI extends FormGUITemplate {

    String[] getFieldLabels() {
        String[] fieldLabels = {"Name", "Surname", "PESEL Number", "ID Number", "Company name", "NIP", "Country of Registration", "Headquarters City", "Headquarters Street", "Headquarters Street number", "Headquarters Postal Code"};
        return fieldLabels;
    }

    String[] getFieldTypes() {
        String[] fieldTypes = {"text", "text", "text", "text", "text", "text", "text", "text", "text", "text", "text"};
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

        Object[] fieldParameters = {20, 30, 20, 10, 30, 20, 20, 30, 30, 10, 10};

        return fieldParameters;
    }

    public static void main(String[] args) {
        new OwnerFormGUI().createGUI();
    }
}
