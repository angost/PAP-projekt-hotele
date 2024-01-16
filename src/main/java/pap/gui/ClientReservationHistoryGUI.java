package pap.gui;

import pap.db.dao.*;
import pap.db.entities.Reservation;
import pap.gui.components.ReservationPanel;
import pap.gui.components.ScrollElementButton;
import pap.logic.get.GetReservationHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class ClientReservationHistoryGUI extends ScrollGUITemplate{

    void getElementsData() {
        List<Reservation> reservations = new GetReservationHistory(new ClientDAO().findById(userId)).getClientPastReservations();
        this.nrOfElements = reservations.size();
        this.fittingElementsIds = new Integer[nrOfElements];
        for (int i = 0; i < nrOfElements; i++) {
            fittingElementsIds[i] = reservations.get(i).getReservationId();
        }
    }

    // mock function
    HashMap<String, String> getElementData(int elementId) {
        HashMap<String, String> reservationInfo = new HashMap<String, String>();
        ReservationDAO rd = new ReservationDAO();
        String startDate = String.valueOf(rd.findById(elementId).getStartDate());
        String endDate = String.valueOf(rd.findById(elementId).getEndDate());
        String description = rd.findById(elementId).getDescription();
        String paidAmount = String.format("%.2f", rd.findById(elementId).getPaidAmount()) + " PLN";
        String status = String.valueOf(rd.findById(elementId).getStatus());
        String name = rd.findById(elementId).getOffer().getName();
        String city = rd.findById(elementId).getOffer().getHotel().getAddress().getCity();
        String people = String.valueOf(rd.findById(elementId).getOffer().getBedNumber());
        String offer = String.valueOf(rd.findById(elementId).getOffer().getOfferId());
        reservationInfo.put("date_from", startDate);
        reservationInfo.put("date_to", endDate);
        reservationInfo.put("description", description);
        reservationInfo.put("paid_amount", paidAmount);
        reservationInfo.put("status", status);
        reservationInfo.put("name", name);
        reservationInfo.put("city", city);
        reservationInfo.put("people", people);
        reservationInfo.put("offer", offer);
        return reservationInfo;
    }
    
    JPanel createScrollElement(int elementId) {

        HashMap<String, String> reservationInfo = getElementData(elementId);

        JPanel reservationPanel = new JPanel();
        reservationPanel.setBackground(neutralBlue);
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.LINE_AXIS));
        reservationPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
        reservationPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
        reservationPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));
        Image image = new OfferDAO().getImageById(Integer.parseInt(reservationInfo.get("offer")));

        String topPanelText = reservationInfo.get("city") + " from " + reservationInfo.get("date_from") +
                " to " + reservationInfo.get("date_to");
        ReservationPanel reservationInfoPanel = new ReservationPanel(neutralGray, fontBigger, fontMiddle, fontMiddleBold, offerWidth,
                offerHeight, topPanelText, reservationInfo, image);

        reservationPanel.add(reservationInfoPanel);
        reservationPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

        return reservationPanel;
    }

    void createScrollButtons(int elementId, JPanel reservationPanel) {
        HashMap<Integer, Boolean> isElRated = new HashMap<>();
        for (Integer el : fittingElementsIds) {
            int offer = new ReservationDAO().findById(el).getOffer().getOfferId();
            isElRated.put(el, new RatingDAO().isRatingForOfferByClient(offer, userId));
        }

        boolean rated = isElRated.get(elementId);
        HashMap<String, String> reservationInfo = getElementData(elementId);
        if ( !reservationInfo.get("status").equals("Cancelled")) {
            ScrollElementButton reviewButton;
            ActionListener reviewActionListener;
            if (!rated) {
                reviewButton = new ScrollElementButton("Rate your stay", frameHeight/7, frameHeight/7, secondColor, secondColorDarker, fontButtons, true, elementId);
                reviewActionListener = new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        ScrollElementButton button = (ScrollElementButton)actionEvent.getSource();
                        System.out.println("Rate reservation " + button.elementId);

//                new OfferPageGUI(-1, "None", button.elementId).createGUI();
//                frame.setVisible(false);
                    }
                };
            } else {
                reviewButton = new ScrollElementButton("See your review", frameHeight/7,frameHeight/7, Color.decode("#E1B87B"), secondColor, fontButtons, true, elementId);
                reviewActionListener = new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        ScrollElementButton button = (ScrollElementButton)actionEvent.getSource();
                        System.out.println("See review of reservation " + button.elementId);

//                new OfferPageGUI(-1, "None", button.elementId).createGUI();
//                frame.setVisible(false);
                    }
                };
            }
            reviewButton.addActionListener(reviewActionListener);
            reservationPanel.add(reviewButton);

//            if (penalty) {
//                reservationPanel.add(Box.createRigidArea(new Dimension(frameHeight/14,0)));
//
//                ScrollElementButton penaltiesButton = new ScrollElementButton("See penalties", frameHeight/7, frameHeight/7,
//                        statusWrongLighter, statusWrong, fontButtons, true, elementId);
//                ActionListener penatlyActionListener = new ActionListener() {
//                    public void actionPerformed(ActionEvent actionEvent) {
//                        ScrollElementButton button = (ScrollElementButton)actionEvent.getSource();
//                        System.out.println("Penalty for reservation " + button.elementId);
//
////                new OfferPageGUI(-1, "None", button.elementId).createGUI();
////                frame.setVisible(false);
//                    }
//                };
//                penaltiesButton.addActionListener(penatlyActionListener);
//                reservationPanel.add(penaltiesButton);
//            }
        }
    }

    public ClientReservationHistoryGUI(int userId, String userType){
        super(userId, userType);
        getElementsData();
        offerHeight = frameHeight/4;
        offerWidth = frameWidth/2;
        pageName = "Reservation history";
    }

    public static void main(String[] args) {
        new ClientReservationHistoryGUI(-1, "None").createGUI();
    }

}

