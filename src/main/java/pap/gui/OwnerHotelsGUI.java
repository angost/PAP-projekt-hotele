package pap.gui;

import pap.gui.components.*;
import pap.logic.guiAction.FindDisplayOffers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class OwnerHotelsGUI extends ScrollGUITemplate{

    // mock funtion
    void getElementsData() {
        this.fittingElementsIds = new Integer[]{2,3,5,8};
        this.nrOfElements = fittingElementsIds.length;
    }

    // mock function
    HashMap<String, String> getElementData(int elementId) {
        HashMap<String, String> hotelInfo = new HashMap<String, String>();
        hotelInfo.put("name", "Grand Hotel");
        hotelInfo.put("offer_no", "47");
        hotelInfo.put("country", "Poland");
        hotelInfo.put("city", "Wroclaw");
        hotelInfo.put("website", "www.sunsetresorts.com");
        hotelInfo.put("email", "goldensands@email.com");
        hotelInfo.put("phone_number", "+5544332211");
        hotelInfo.put("bank_account_nr", "7777888899");
        return hotelInfo;
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



