package pap.gui;

import pap.db.dao.ClientDAO;
import pap.db.dao.OwnerDAO;
import pap.db.dao.RatingDAO;
import pap.db.entities.*;
import pap.gui.components.ReviewPanel;
import pap.gui.components.ScrollElementButton;
import pap.logic.ratings.GetAllRatingsForClient;
import pap.logic.ratings.GetAllRatingsForOwner;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReviewsScrollGUI extends ScrollGUITemplate{

    int offerId;

    void getElementsData() {
        // Offer's reviews
        if (offerId != -1) {
            this.fittingElementsIds = new Integer[]{3};
            List <Rating> ratingList = new RatingDAO().getRatingsForOffer(offerId);
            List <Integer> ids = new ArrayList<>();
            for (var rating : ratingList) {
                ids.add(rating.getRatingId());
            }
            this.fittingElementsIds = ids.toArray(new Integer[0]);
        }
        // Client's reviews
        else if (userType.equals("Client")) {
            Client client = new ClientDAO().findById(userId);
            List <Rating> ratingList = new GetAllRatingsForClient(client).getAllRatings();
            List <Integer> ids = new ArrayList<>();
            for (var rating : ratingList) {
                ids.add(rating.getRatingId());
            }
            this.fittingElementsIds = ids.toArray(new Integer[0]);
        }
        // Owner's reviews
        else {
            Owner owner = new OwnerDAO().findById(userId);
            List<Rating> ratingList = new GetAllRatingsForOwner(owner).getAllRatings();
            List <Integer> ids = new ArrayList<>();
            for (var rating : ratingList) {
                ids.add(rating.getRatingId());
            }
            this.fittingElementsIds = ids.toArray(new Integer[0]);
        }
        this.nrOfElements = fittingElementsIds.length;
    }

    HashMap<String, String> getElementData(int elementId) {
        Rating rating = new RatingDAO().findById(elementId);
        HashMap<String, String> reviewInfo = new HashMap<String, String>();
        reviewInfo.put("offer_name", rating.getOffer().getName());
        reviewInfo.put("hotel_name", rating.getOffer().getHotel().getName());
        reviewInfo.put("user_name", rating.getClient().getName());
        reviewInfo.put("comment", rating.getComment());
        reviewInfo.put("rating", String.valueOf(rating.getRating()));
        reviewInfo.put("date_added", String.valueOf(rating.getAddDate()));
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
//        boolean ratingByThisUser = true;
//
//        if (userType.equals("Client") && ratingByThisUser) {
//            ScrollElementButton editReviewButton = new ScrollElementButton("Edit review", buttonSize, buttonSize, secondColor, secondColorDarker, fontButtons, true, elementId);
//            editReviewButton.addActionListener(actionEvent -> {
//                ScrollElementButton button = (ScrollElementButton) actionEvent.getSource();
////            new OfferDetailsGUI(userId, userType, button.elementId).createGUI();
////            frame.setVisible(false);
//            });
//            reviewPanel.add(editReviewButton);
//        }
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



