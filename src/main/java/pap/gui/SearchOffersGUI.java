package pap.gui;

import pap.gui.components.*;
import pap.logic.guiAction.FindDisplayOffers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SearchOffersGUI extends ScrollGUITemplate{

    void getElementsData() {
        fittingElementsIds = new FindDisplayOffers().getFittingElementsIds();
        nrOfElements = fittingElementsIds.length;
    }

    HashMap<String, String> getElementData(int elementId) {
        HashMap<String, String> offerInfo = new FindDisplayOffers().getElementInfo(elementId);
        return offerInfo;
    }

    //mock function
    String getImgPath(int offerId) {
        HashMap<Integer, String> imgPathMap = new HashMap<Integer, String>();
        imgPathMap.put(1, "/room1.jpg"); imgPathMap.put(2, "/room2.jpg");
        imgPathMap.put(3, "/room3.jpg"); imgPathMap.put(4, "/room4.jpg");
        imgPathMap.put(5, "/room5.jpg"); imgPathMap.put(6, "/room6.jpeg");
        imgPathMap.put(7, "/room7.jpg"); imgPathMap.put(8, "/room8.jpg");
        imgPathMap.put(9, "/room9.jpeg"); imgPathMap.put(10, "/room10.jpg");
        return imgPathMap.get(offerId);
    }


    JPanel createScrollElement(int elementId) {

        HashMap<String, String> offerInfo = getElementData(elementId);

        JPanel offerPanel = new JPanel();
        offerPanel.setBackground(neutralBlue);
        offerPanel.setLayout(new BoxLayout(offerPanel, BoxLayout.LINE_AXIS));
        offerPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
        offerPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
        offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

        OfferPanel offerInfoPanel = new OfferPanel(neutralGray, fontBigger, fontMiddle, fontMiddleBold, offerWidth,
                offerHeight, offerInfo.get("name"), getImgPath(elementId), offerInfo);

        offerPanel.add(offerInfoPanel);
        offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

        return offerPanel;

    }

    void createScrollButtons(int elementId, JPanel offerPanel) {

        ScrollElementButton seeOfferBtn = new ScrollElementButton("See offer", scrollButtonSize, scrollButtonSize, secondColor, secondColorDarker, fontButtons, true, elementId);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ScrollElementButton button = (ScrollElementButton)actionEvent.getSource();
                new OfferDetailsGUI(userId, userType, button.elementId).createGUI();
                frame.setVisible(false);
            }
        };
        seeOfferBtn.addActionListener(actionListener);
        offerPanel.add(seeOfferBtn);
        offerPanel.add(Box.createRigidArea(new Dimension(scrollButtonSize,0)));

        FavouritesButton favouritesButton = FavouritesButtonCreator.createFavouritesButton(scrollButtonSize/2, scrollButtonSize/2, elementId, userId);
        ActionListener favActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                FavouritesButton button = (FavouritesButton)actionEvent.getSource();
                FavouritesButtonCreator.favouritesBtnClicked(button, userId);
            }
        };
        favouritesButton.addActionListener(favActionListener);
        offerPanel.add(favouritesButton);
    }

    public SearchOffersGUI(int userId, String userType){
        super(userId, userType);
        getElementsData();
        offerHeight = frameHeight/4;
        offerWidth = frameWidth/2;
        pageName = "Look for offers";
    }

    public static void main(String[] args) {
        new SearchOffersGUI(-1, "None").createGUI();
    }

}

