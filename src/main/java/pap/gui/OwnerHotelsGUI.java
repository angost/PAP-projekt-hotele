package pap.gui;

import pap.db.dao.HotelDAO;
import pap.db.dao.OfferDAO;
import pap.db.dao.OwnerDAO;
import pap.db.entities.Hotel;
import pap.db.entities.Offer;
import pap.gui.components.*;
import pap.logic.get.GetAllOwnerHotels;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class OwnerHotelsGUI extends ScrollGUITemplate{

    void getElementsData() {
        List<Hotel> hotels = new GetAllOwnerHotels(new OwnerDAO().findById(userId)).get();
        this.nrOfElements = hotels.size();
        this.fittingElementsIds = new Integer[nrOfElements];
        for (int i = 0; i < nrOfElements; i++) {
            fittingElementsIds[i] = hotels.get(i).getHotelId();
        }
    }

    HashMap<String, String> getElementData(int elementId) {
        HashMap<String, String> elInfo = new HashMap<>();

        Hotel hotel = new HotelDAO().findById(elementId);
        List<Offer> offers = new OfferDAO().findByHotelId(elementId);

        elInfo.put("name", hotel.getName());
        elInfo.put("offer_no", String.valueOf(offers.size()));
        elInfo.put("country", hotel.getAddress().getCountry());
        elInfo.put("city", hotel.getAddress().getCity());
        elInfo.put("website", hotel.getWebsite());
        elInfo.put("email", hotel.getEmail());
        elInfo.put("phone_number", hotel.getPhoneNumber());
        elInfo.put("bank_account_nr", hotel.getBankAccountNumber());

        return elInfo;
    }

    JPanel createScrollElement(int elementId) {
        
        HashMap<String, String> hotelInfo = getElementData(elementId);

        JPanel hotelPanel = new JPanel();
        hotelPanel.setBackground(neutralBlue);
        hotelPanel.setLayout(new BoxLayout(hotelPanel, BoxLayout.LINE_AXIS));
        hotelPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
        hotelPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
        hotelPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

        HotelPanel hotelInfoPanel = new HotelPanel(neutralGray, fontBigger, fontBiggerBold, fontMiddle, fontMiddleBold, offerWidth,
                offerHeight, hotelInfo);
        hotelPanel.add(hotelInfoPanel);

        return hotelPanel;
    }

    void createScrollButtons(int elementId, JPanel hotelPanel) {

        int buttonSize = scrollButtonSize; int gapSize = buttonSize/3;
        hotelPanel.add(Box.createRigidArea(new Dimension(gapSize,0)));

        ScrollElementButton hotelPageBtn = new ScrollElementButton("Hotel page", buttonSize, buttonSize, secondColor, secondColorDarker, fontButtons, true, elementId);
        hotelPageBtn.addActionListener(actionEvent -> {
            ScrollElementButton button = (ScrollElementButton) actionEvent.getSource();
//            new OfferDetailsGUI(userId, userType, button.elementId).createGUI();
//            frame.setVisible(false);
        });
        hotelPanel.add(hotelPageBtn);
        hotelPanel.add(Box.createRigidArea(new Dimension(gapSize,0)));

        ScrollElementButton editHotelBtn = new ScrollElementButton("Edit hotel data", buttonSize, buttonSize, secondColor, secondColorDarker, fontButtons, true, elementId);
        editHotelBtn.addActionListener(actionEvent -> {
            ScrollElementButton button = (ScrollElementButton) actionEvent.getSource();
//            new OfferDetailsGUI(userId, userType, button.elementId).createGUI();
//            frame.setVisible(false);
        });
        hotelPanel.add(editHotelBtn);
        hotelPanel.add(Box.createRigidArea(new Dimension(gapSize,0)));

        ScrollElementButton deactivateHotelBtn = new ScrollElementButton("Deactivate hotel", buttonSize, buttonSize, statusWrongLighter, statusWrong, fontButtons, true, elementId);
        deactivateHotelBtn.addActionListener(actionEvent -> {
            ScrollElementButton button = (ScrollElementButton) actionEvent.getSource();
//            new OfferDetailsGUI(userId, userType, button.elementId).createGUI();
//            frame.setVisible(false);
        });
        hotelPanel.add(deactivateHotelBtn);
//        hotelPanel.add(Box.createRigidArea(new Dimension(gapSize,0)));
    }
    
    public OwnerHotelsGUI(int userId, String userType){
        super(userId, userType);
        getElementsData();
        offerHeight = frameHeight/4;
        offerWidth = frameWidth*2/5;
        pageName = "Your hotels";
    }

    public static void main(String[] args) {
        new OwnerHotelsGUI(-1, "None").createGUI();
    }

}



