package pap.gui;

import pap.db.dao.OfferDAO;
import pap.gui.components.FavouritesButton;
import pap.gui.components.FavouritesButtonCreator;
import pap.gui.components.OfferPanel;
import pap.gui.components.ScrollElementButton;
import pap.logic.guiAction.FindDisplayOffers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ScrollDiscountsGUI extends ScrollGUITemplate {
    public ScrollDiscountsGUI(int userId, String userType){
        super(userId, userType);
        getElementsData();
        offerHeight = frameHeight/4;
        offerWidth = frameWidth/2;
        pageName = "Manage discounts";
    }

//    void getElementsData() {
//        this.fittingElementsIds = new FindDisplayOffers().getFittingElementsIds();
//        this.nrOfElements = fittingElementsIds.length;
//    }
//
//    HashMap<String, String> getElementData(int elementId) {
//        HashMap<String, String> offerInfo = new FindDisplayOffers().getElementInfo(elementId);
//        return offerInfo;
//    }
//
//    JPanel createScrollElement(int elementId) {
//
//        HashMap<String, String> offerInfo = getElementData(elementId);
//
//        JPanel offerPanel = new JPanel();
//        offerPanel.setBackground(neutralBlue);
//        offerPanel.setLayout(new BoxLayout(offerPanel, BoxLayout.LINE_AXIS));
//        offerPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
//        offerPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
//        offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));
//        Image image = new OfferDAO().getImageById(elementId);
//        OfferPanel offerInfoPanel = new OfferPanel(neutralGray, fontBigger, fontMiddle, fontMiddleBold, offerWidth,
//                offerHeight, offerInfo.get("name"), offerInfo, image);
//
//        offerPanel.add(offerInfoPanel);
//        offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));
//
//        return offerPanel;
//
//    }
//
//    void createScrollButtons(int elementId, JPanel offerPanel) {
//
//        ScrollElementButton seeOfferBtn = new ScrollElementButton("See offer", scrollButtonSize, scrollButtonSize, secondColor, secondColorDarker, fontButtons, true, elementId);
//        ActionListener actionListener = new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                ScrollElementButton button = (ScrollElementButton)actionEvent.getSource();
//                new OfferDetailsGUI(userId, userType, button.elementId).createGUI();
//                frame.setVisible(false);
//            }
//        };
//        seeOfferBtn.addActionListener(actionListener);
//        offerPanel.add(seeOfferBtn);
//        offerPanel.add(Box.createRigidArea(new Dimension(scrollButtonSize,0)));
//
//        FavouritesButton favouritesButton = FavouritesButtonCreator.createFavouritesButton(scrollButtonSize/2, scrollButtonSize/2, elementId, userId);
//        ActionListener favActionListener = new ActionListener() {
//            public void actionPerformed(ActionEvent actionEvent) {
//                FavouritesButton button = (FavouritesButton)actionEvent.getSource();
//                FavouritesButtonCreator.favouritesBtnClicked(button, userId);
//            }
//        };
//        favouritesButton.addActionListener(favActionListener);
//        offerPanel.add(favouritesButton);
//    }


}
