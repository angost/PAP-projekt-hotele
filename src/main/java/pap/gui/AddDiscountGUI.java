package pap.gui;

import pap.db.dao.HotelDAO;
import pap.db.entities.Hotel;
import pap.logic.ErrorCodes;
import pap.logic.discountCodes.AddDiscountCodeForAllHotels;
import pap.logic.discountCodes.AddNewDiscountCode;
import pap.logic.validators.DiscountCodeValidator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddDiscountGUI extends FormGUITemplate {
    public AddDiscountGUI(int userId, String userType) {
        super(userId, userType);
    }

    @Override
    String[] getFieldLabels() {
        return new String[]{"Code", "Code description", "Value", "Value type", "Hotel"};
    }

    @Override
    String[] getFieldTypes() {
        return new String[]{"text", "text", "text", "comboBoxString", "comboBoxString"};
    }

    @Override
    Object[] getFieldParameters() {
        String[] valueTypes = new String[]{"Percentage (%)", "Fixed (PLN)"};
        List<Hotel> hotels = new ArrayList<>();
        if (userType.equals("Admin"))
            hotels = new HotelDAO().findAll();
        else if (userType.equals("Owner"))
            hotels = new HotelDAO().findByOwnerId(userId);
        int size = hotels.size();
        String[] hotelsOptions = new String[size];
        for (int i=0; i < size; i++) {
            hotelsOptions[i] = hotels.get(i).getName();
        }

        return new Object[]{15, 20, 15, new String[][]{valueTypes}, new String[][]{hotelsOptions}};
    }

    @Override
    void finishFormButtonClicked(){
        HashMap<String, String> formFieldsValues = getFieldValues();
        String code = formFieldsValues.get("Code");
        String description = formFieldsValues.get("Code description");
        String value = formFieldsValues.get("Value");
        String valueType = formFieldsValues.get("Value type");
        String hotelName = formFieldsValues.get("Hotel");
        Hotel hotel = new HotelDAO().findByNameAndOwnerId(hotelName, userId);
        List<Integer> errorCodes = new DiscountCodeValidator(code, valueType.equals("Percentage (%)") ? 0 : 1, 1, description, Float.parseFloat(value), hotel, true).validate();

        if (errorCodes.isEmpty()) {
            if (hotelName.equals("ALL HOTELS"))
                new AddDiscountCodeForAllHotels(code, valueType.equals("Percentage (%)") ? 0 : 1, description, Float.parseFloat(value), true).insertIntoDatabase();
            else {
                if (hotel == null)
                    hotel = new HotelDAO().findByName(hotelName);
                new AddNewDiscountCode(code, valueType.equals("Percentage (%)") ? 0 : 1, description, Float.parseFloat(value), hotel, true).insertIntoDatabase();
            }
            JOptionPane.showMessageDialog(frame, "Success! Discount code added!");
            new HomePageGUI(userId, userType).createGUI();
            frame.setVisible(false);
        }
        else {
            String statusLabelText = "<html>"; String spacingCharacter = "<br/>";
            if (errorCodes.size() > 10) spacingCharacter = " | ";
            for (Integer code1 : errorCodes) {
                statusLabelText = statusLabelText + ErrorCodes.getErrorDescription(code1) + spacingCharacter;
            }
            statusLabelText = statusLabelText + "</html>";
            statusLabel.setText(statusLabelText);
            statusLabel.setForeground(statusWrong);
        }
    }

    @Override
    void undoBtnClickedAction(){
        new HomePageGUI(userId, userType).createGUI();
        frame.setVisible(false);
    }

    @Override
    void setFinishFormButtonText() {
        finishFormButtonText = "Add discount";
    }

    public static void main(String[] args) {
        new AddDiscountGUI(1, "Admin").createGUI();
    }
}
