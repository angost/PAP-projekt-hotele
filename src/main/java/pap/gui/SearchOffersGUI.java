package pap.gui;

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

    JPanel createScrollElement(int elementId) {

        HashMap<String, String> offerInfo = getElementData(elementId);

        JPanel offerPanel = new JPanel();
        offerPanel.setBackground(neutralBlue);
        offerPanel.setLayout(new BoxLayout(offerPanel, BoxLayout.LINE_AXIS));
        offerPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
        offerPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
        offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

        JPanel offerInfoPanel = new JPanel();
        offerInfoPanel.setBackground(neutralGray);
        offerInfoPanel.setLayout(new BoxLayout(offerInfoPanel, BoxLayout.PAGE_AXIS));
        offerInfoPanel.setPreferredSize(new Dimension(offerWidth, offerHeight));
        offerInfoPanel.setMaximumSize(new Dimension(offerWidth, offerHeight));

        JLabel offerNameLabel = new JLabel(offerInfo.get("name"), JLabel.CENTER);
        offerNameLabel.setPreferredSize(new Dimension(offerWidth, offerHeight));
        offerNameLabel.setMaximumSize(new Dimension(offerWidth, offerHeight));
        offerNameLabel.setFont(fontMiddle);
        offerInfoPanel.add(offerNameLabel);

        JLabel offerInfoLabel = new JLabel(offerInfo.get("info"), JLabel.CENTER);
        offerInfoLabel.setPreferredSize(new Dimension(offerWidth, offerHeight));
        offerInfoLabel.setMaximumSize(new Dimension(offerWidth, offerHeight));
        offerInfoLabel.setFont(fontSmaller);
        offerInfoPanel.add(offerInfoLabel);

        JLabel offerPriceLabel = new JLabel(offerInfo.get("price"), JLabel.CENTER);
        offerPriceLabel.setPreferredSize(new Dimension(offerWidth, offerHeight));
        offerPriceLabel.setMaximumSize(new Dimension(offerWidth, offerHeight));
        offerPriceLabel.setFont(fontMiddle);
        offerPriceLabel.setForeground(Color.RED);
        offerInfoPanel.add(offerPriceLabel);

        offerPanel.add(offerInfoPanel);
        offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

        return offerPanel;

    }

    void createScrollButtons(int elementId, JPanel offerPanel) {

        ScrollElementButton seeOfferBtn = new ScrollElementButton("See offer", frameHeight/7, frameHeight/7,secondColor, secondColorDarker, fontButtons, true, elementId);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ScrollElementButton button = (ScrollElementButton)actionEvent.getSource();
                System.out.println(button.elementId);

//                new OfferPageGUI(-1, "None", button.offerId).createGUI();
//                frame.setVisible(false);
            }
        };
        seeOfferBtn.addActionListener(actionListener);
        offerPanel.add(seeOfferBtn);

    }

    public SearchOffersGUI(int userId, String userType){
        super(userId, userType);
        getElementsData();
        offerHeight = frameHeight/4;
        offerWidth = frameWidth/3;
        pageName = "Look for offers";
    }

    public static void main(String[] args) {
        new SearchOffersGUI(-1, "None").createGUI();
    }

}

