package pap.gui;

import java.time.Year;

public class OwnerFormGUI extends FormGUITemplate {

    String[] getFieldLabels() {
        String[] fieldLabels = {"Username", "Password", "Company name", "Email", "Phone number", "NIP", "Country", "City", "Street", "Street number", "Postal Code"};
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

        Object[] fieldParameters = {15, 15, 20, 20, 15, 10, 15, 15, 15, 6, 6};

        return fieldParameters;
    }

    public static void main(String[] args) {
        new OwnerFormGUI().createGUI();
    }
}
