package pap.gui;

        import pap.db.dao.OwnerDAO;
        import pap.db.entities.Owner;
        import pap.logic.add.AddNewHotel;
        import pap.logic.add.AddNewOwner;
        import pap.logic.validators.HotelValidator;

        import java.time.LocalDate;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;

public class AddHotelGUI extends AddOwnerPropertyFormTemplate {

    public AddHotelGUI(int userId, String userType) {
        super(userId, userType);
        pageName = "Add Hotel";
    }

    @Override
    String[] getFieldLabels() {
        String[] fieldLabels = {"Hotel name", "Hotel description", "Hotel website", "Hotel email", "Hotel phone number", "Hotel bank account number", "Country", "City", "Street", "Street number", "Postal Code"};
        return fieldLabels;
    }

    @Override
    String[] getFieldTypes() {
        String[] fieldTypes = {"text", "text", "text", "text", "text", "text", "text", "text", "text", "text", "text"};
        return fieldTypes;
    }

    @Override
    Object[] getFieldParameters() {

        Object[] fieldParameters = {15, 60, 20, 20, 10, 20, 15, 15, 15, 6, 6};

        return fieldParameters;
    }

    @Override
    List<Integer> validatePropertyData(HashMap<String, String> textFieldsValues) {
        return new HotelValidator(textFieldsValues.get("Hotel name"), LocalDate.now(), textFieldsValues.get("Hotel description"),
                textFieldsValues.get("Country"), textFieldsValues.get("City"), textFieldsValues.get("Street"), textFieldsValues.get("Postal Code"), textFieldsValues.get("Street number"),
                textFieldsValues.get("Hotel email"), textFieldsValues.get("Hotel website"), textFieldsValues.get("Hotel phone number"),
                textFieldsValues.get("Hotel bank account number"), true, userId).validate();
    }

    @Override
    void addProperty(HashMap<String, String> textFieldsValues) {
        Owner owner = new OwnerDAO().findById(userId);
        new AddNewHotel(textFieldsValues.get("Hotel name"), LocalDate.now(), textFieldsValues.get("Hotel description"),
                textFieldsValues.get("Country"), textFieldsValues.get("City"), textFieldsValues.get("Street"), textFieldsValues.get("Postal Code"), textFieldsValues.get("Street number"),
                textFieldsValues.get("Hotel email"), textFieldsValues.get("Hotel website"), textFieldsValues.get("Hotel phone number"),
                textFieldsValues.get("Hotel bank account number"), true, owner).insertIntoDatabase();
    }

    @Override
    void setFinishFormButtonText() {
        finishFormButtonText = "Add hotel";
    }

    public static void main(String[] args) {
//        new AddHotelGUI(-1, "None").createGUI();
        new AddHotelGUI(3, "Owner").createGUI();
    }
}
