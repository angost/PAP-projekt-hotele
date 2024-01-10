package pap.gui;

import pap.db.dao.OfferDAO;
import pap.db.dao.OwnerDAO;
import pap.db.dao.ReservationDAO;
import pap.db.entities.Offer;
import pap.db.entities.Reservation;
import pap.gui.components.OwnersOfferPanel;
import pap.gui.components.ScrollElementButton;
import pap.logic.get.GetAllOwnerOffers;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class OwnerOffersGUI extends ScrollGUITemplate{

    void getElementsData() {
        List<Offer> offers = new GetAllOwnerOffers(new OwnerDAO().findById(userId)).get();
        this.nrOfElements = offers.size();
        this.fittingElementsIds = new Integer[nrOfElements];
        for (int i = 0; i < nrOfElements; i++) {
            fittingElementsIds[i] = offers.get(i).getOfferId();
        }
    }

    HashMap<String, String> getElementData(int elementId) {
        HashMap<String, String> elInfo = new HashMap<>();

        Offer offer = new OfferDAO().findById(elementId);
        List<Reservation> reservations = new ReservationDAO().findByOfferId(elementId);
        float income = 0;
        for (Reservation reservation : reservations) {
            income += reservation.getPaidAmount();
        }

        elInfo.put("name", offer.getName());
        elInfo.put("hotel", offer.getHotel().getName());
        elInfo.put("add_date", offer.getAddDate().toString());
        elInfo.put("price", String.format("%.2f", offer.getPrice()) + " PLN");
        elInfo.put("res_no", String.valueOf(reservations.size()));
        elInfo.put("total_income", String.format("%.2f", income) + " PLN");

        return elInfo;
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
            new OwnersOfferDetailsGUI(userId, userType, button.elementId).createGUI();
            frame.setVisible(false);
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

    public OwnerOffersGUI(int userId, String userType) {
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



