package pap.gui;

import pap.gui.components.ReviewPanel;
import pap.gui.components.ScrollElementButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ReviewsScrollGUI extends ScrollGUITemplate{

    int offerId;

    // mock funtion
    void getElementsData() {
        // Offer's reviews
        if (offerId != -1) {
            this.fittingElementsIds = new Integer[]{3};
        }
        // Client's reviews
        else if (userType.equals("Client")) {
            this.fittingElementsIds = new Integer[]{1,2,3,4};
        }
        // Owner's reviews
        else {
            this.fittingElementsIds = new Integer[]{1,4,5};
        }
        this.nrOfElements = fittingElementsIds.length;
    }

    // mock function
    HashMap<String, String> getElementData(int elementId) {
        HashMap<String, String> reviewInfo = new HashMap<String, String>();
        reviewInfo.put("offer_name", "Room with View");
        reviewInfo.put("hotel_name", "Hotel Grand");
        reviewInfo.put("user_name", "GreatUser");
        reviewInfo.put("comment", "Beautiful view, but the room was a bit small");
        reviewInfo.put("rating", "4");
        reviewInfo.put("date_added", "2023-04-30");
        return reviewInfo;
    }

    JPanel createScrollElement(int elementId) {

        HashMap<String, String> reviewInfo = getElementData(elementId);

        JPanel reviewPanel = new JPanel();
        reviewPanel.setBackground(neutralBlue);
        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.LINE_AXIS));
        reviewPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
        reviewPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
        reviewPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

        ReviewPanel reviewInfoPanel = new ReviewPanel(neutralGray, fontBigger, fontBiggerBold, fontMiddle, fontMiddleBold, fontSmaller,
                offerWidth, offerHeight, reviewInfo);
        reviewPanel.add(reviewInfoPanel);

        return reviewPanel;
    }

    void createScrollButtons(int elementId, JPanel reviewPanel) {

        int buttonSize = scrollButtonSize; int gapSize = buttonSize/3;
        reviewPanel.add(Box.createRigidArea(new Dimension(gapSize,0)));

        // mock
        boolean ratingByThisUser = true;

        if (userType.equals("Client") && ratingByThisUser) {
            ScrollElementButton editReviewButton = new ScrollElementButton("Edit review", buttonSize, buttonSize, secondColor, secondColorDarker, fontButtons, true, elementId);
            editReviewButton.addActionListener(actionEvent -> {
                ScrollElementButton button = (ScrollElementButton) actionEvent.getSource();
//            new OfferDetailsGUI(userId, userType, button.elementId).createGUI();
//            frame.setVisible(false);
            });
            reviewPanel.add(editReviewButton);
        }
    }

    @Override
    void undoBtnClickedAction(){
        if (offerId != -1) {
            if (userType.equals("Client")) {
                new OfferDetailsGUI(userId, userType, offerId).createGUI();
            } else {
                new OwnersOfferDetailsGUI(userId, userType, offerId).createGUI();
            }
        } else {
            new HomePageGUI(userId, userType).createGUI();
        }
        frame.setVisible(false);
    }

    public ReviewsScrollGUI(int userId, String userType){
        super(userId, userType);
        offerId = -1;
        getElementsData();
        offerHeight = frameHeight/6;
        offerWidth = frameWidth*3/5;
        pageName = "Reviews";
    }

    public ReviewsScrollGUI(int userId, String userType, int offerId){
        super(userId, userType);
        this.offerId = offerId;
        getElementsData();
        offerHeight = frameHeight/6;
        offerWidth = frameWidth*3/5;
        pageName = "Reviews";
    }

    public static void main(String[] args) {
        new ReviewsScrollGUI(-1, "None").createGUI();
    }

}



