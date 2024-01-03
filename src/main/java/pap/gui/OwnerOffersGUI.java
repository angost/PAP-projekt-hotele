package pap.gui;

import pap.gui.components.OwnersOfferPanel;
import pap.gui.components.ScrollElementButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class OwnerOffersGUI extends ScrollGUITemplate{

    // mock funtion
    void getElementsData() {
        this.fittingElementsIds = new Integer[]{1,2,3,5,7,8,9};
        this.nrOfElements = fittingElementsIds.length;
    }

    // mock function
    HashMap<String, String> getElementData(int elementId) {
        HashMap<String, String> offerInfo = new HashMap<String, String>();
        offerInfo.put("name", "Ocean View Standard Room");
        offerInfo.put("hotel", "Grand Hotel");
        offerInfo.put("add_date", "2023-08-10");
        offerInfo.put("price", "280");
        offerInfo.put("res_no", "40");
        offerInfo.put("total_income", "33600");
        return offerInfo;
    }

    JPanel createScrollElement(int elementId) {

        HashMap<String, String> offerInfo = getElementData(elementId);

        JPanel offerPanel = new JPanel();
        offerPanel.setBackground(neutralBlue);
        offerPanel.setLayout(new BoxLayout(offerPanel, BoxLayout.LINE_AXIS));
        offerPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
        offerPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
        offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

        OwnersOfferPanel offerInfoPanel = new OwnersOfferPanel(neutralGray, fontBigger, fontBiggerBold, fontMiddle, fontMiddleBold, offerWidth,
                offerHeight, offerInfo);
        offerPanel.add(offerInfoPanel);

        return offerPanel;
    }

    void createScrollButtons(int elementId, JPanel offerPanel) {

        int buttonSize = scrollButtonSize; int gapSize = buttonSize/3;
        offerPanel.add(Box.createRigidArea(new Dimension(gapSize,0)));

        ScrollElementButton offerPageBtn = new ScrollElementButton("Offer page", buttonSize, buttonSize, secondColor, secondColorDarker, fontButtons, true, elementId);
        offerPageBtn.addActionListener(actionEvent -> {
            ScrollElementButton button = (ScrollElementButton) actionEvent.getSource();
//            new OfferDetailsGUI(userId, userType, button.elementId).createGUI();
//            frame.setVisible(false);
        });
        offerPanel.add(offerPageBtn);
        offerPanel.add(Box.createRigidArea(new Dimension(gapSize,0)));

        ScrollElementButton editOfferBtn = new ScrollElementButton("Edit offer data", buttonSize, buttonSize, secondColor, secondColorDarker, fontButtons, true, elementId);
        editOfferBtn.addActionListener(actionEvent -> {
            ScrollElementButton button = (ScrollElementButton) actionEvent.getSource();
//            new OfferDetailsGUI(userId, userType, button.elementId).createGUI();
//            frame.setVisible(false);
        });
        offerPanel.add(editOfferBtn);
        offerPanel.add(Box.createRigidArea(new Dimension(gapSize,0)));

        ScrollElementButton deactivateOfferBtn = new ScrollElementButton("Deactivate offer", buttonSize, buttonSize, statusWrongLighter, statusWrong, fontButtons, true, elementId);
        deactivateOfferBtn.addActionListener(actionEvent -> {
            ScrollElementButton button = (ScrollElementButton) actionEvent.getSource();
//            new OfferDetailsGUI(userId, userType, button.elementId).createGUI();
//            frame.setVisible(false);
        });
        offerPanel.add(deactivateOfferBtn);
//        offerPanel.add(Box.createRigidArea(new Dimension(gapSize,0)));
    }

    public OwnerOffersGUI(int userId, String userType){
        super(userId, userType);
        getElementsData();
        offerHeight = frameHeight/4;
        offerWidth = frameWidth*2/5;
        pageName = "Your offers";
    }

    public static void main(String[] args) {
        new OwnerOffersGUI(-1, "None").createGUI();
    }

}



