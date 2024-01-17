package pap.gui;

import pap.db.dao.HotelDAO;
import pap.db.entities.Hotel;
import pap.logic.add.AddNewOffer;
import pap.logic.validators.OfferValidator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class AddOfferGUI extends AddOwnerPropertyFormTemplate {

    public AddOfferGUI(int userId, String userType) {
        super(userId, userType);
        pageName = "Add Offer";
    }

    @Override
    String[] getFieldLabels() {
        return new String[]{"Offer name", "Hotel", "Room type", "Description", "Price per night", "Room no.", "Bathroom no.", "Bed no.", "Has kitchen", "Pet friendly", "Has wifi", "Smoking allowed", "Has parking"};
    }

    @Override
    String[] getFieldTypes() {
        return new String[]{"text", "comboBoxString", "comboBoxString", "text", "text", "text", "text", "text", "comboBoxString", "comboBoxString", "comboBoxString", "comboBoxString", "comboBoxString"};
    }

    @Override
    Object[] getFieldParameters() {

        List<Hotel> hotels = new HotelDAO().findByOwnerId(userId);
        int size = hotels.size();
        String[] hotelsOptions = new String[size];
        for (int i=0; i < size; i++) {
            hotelsOptions[i] = hotels.get(i).getName();
        }
        String[][] roomTypes = new String[][]{{"Standard", "Double", "Luxury"}};
        String[][] yesNoOptions = new String[][]{{"No", "Yes"}};

        return new Object[]{15, new String[][]{hotelsOptions}, roomTypes, 60, 7, 5, 5, 5, yesNoOptions, yesNoOptions, yesNoOptions, yesNoOptions, yesNoOptions};
    }

    @Override
    List<Integer> validatePropertyData(HashMap<String, String> textFieldsValues) {
        return new OfferValidator(textFieldsValues.get("Room type"), textFieldsValues.get("Offer name"), LocalDate.now(), textFieldsValues.get("Description"),
                Integer.parseInt(textFieldsValues.get("Bathroom no.")), Integer.parseInt(textFieldsValues.get("Room no.")), Integer.parseInt(textFieldsValues.get("Bed no.")),
                textFieldsValues.get("Has kitchen").equals("Yes"), textFieldsValues.get("Pet friendly").equals("Yes"), Float.parseFloat(textFieldsValues.get("Price per night")), true).validate();
    }

    @Override
    void addProperty(HashMap<String, String> textFieldsValues) {
        Hotel hotel = new HotelDAO().findByNameAndOwnerId(textFieldsValues.get("Hotel"), userId);
        if (hotel == null) {
            System.out.println("Hotel not found");
            return;
        }
        new AddNewOffer(textFieldsValues.get("Room type"), textFieldsValues.get("Offer name"), LocalDate.now(), textFieldsValues.get("Description"),
                Integer.parseInt(textFieldsValues.get("Bathroom no.")), Integer.parseInt(textFieldsValues.get("Room no.")), Integer.parseInt(textFieldsValues.get("Bed no.")),
                textFieldsValues.get("Has kitchen").equals("Yes"), textFieldsValues.get("Pet friendly").equals("Yes"), textFieldsValues.get("Has wifi").equals("Yes"), textFieldsValues.get("Smoking allowed").equals("Yes"), textFieldsValues.get("Has parking").equals("Yes"),
                Float.parseFloat(textFieldsValues.get("Price per night")), true, hotel).insertIntoDatabase();
    }

    @Override
    void setFinishFormButtonText() {
        finishFormButtonText = "Add offer";
    }

    public static void main(String[] args) {
//        new AddHotelGUI(-1, "None").createGUI();
        new AddOfferGUI(3, "Owner").createGUI();
    }
}
