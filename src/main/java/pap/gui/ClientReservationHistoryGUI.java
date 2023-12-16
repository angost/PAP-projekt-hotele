package pap.gui;

import pap.db.dao.ClientDAO;
import pap.db.dao.ReservationDAO;
import pap.gui.usefulPanels.ReservationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ClientReservationHistoryGUI extends ScrollGUITemplate{

    // mock function
    void getElementsData() {
        fittingElementsIds = new Integer[]{1,2,3,4,5};
        // fittingElementsIds = new ...()...(userId);
        nrOfElements = fittingElementsIds.length;
    }

    // mock function
    HashMap<String, String> getElementData(int elementId) {
        HashMap<String, String> reservationInfo = new HashMap<String, String>();
        ReservationDAO rd = new ReservationDAO();
        String startDate = String.valueOf(rd.findById(elementId).getStartDate());
        String endDate = String.valueOf(rd.findById(elementId).getEndDate());
        String description = rd.findById(elementId).getDescription();
        String paidAmount = String.valueOf(rd.findById(elementId).getPaidAmount());
        String status = String.valueOf(rd.findById(elementId).getStatus());
        String name = rd.findById(elementId).getOffer().getName();
        String city = rd.findById(elementId).getOffer().getHotel().getAddress().getCity();
        String people = String.valueOf(rd.findById(elementId).getOffer().getBedNumber());
        reservationInfo.put("date_from", startDate);
        reservationInfo.put("date_to", endDate);
        reservationInfo.put("description", description);
        reservationInfo.put("paid_amount", paidAmount);
        reservationInfo.put("status", status);
        reservationInfo.put("name", name);
        reservationInfo.put("city", city);
        reservationInfo.put("img_path", "/room1.jpg");
        reservationInfo.put("people", people);

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

        String topPanelText = reservationInfo.get("city") + " from " + reservationInfo.get("date_from") +
                " to " + reservationInfo.get("date_to");
        ReservationPanel reservationInfoPanel = new ReservationPanel(neutralGray, fontBigger, fontMiddle, fontMiddleBold, offerWidth,
                offerHeight, topPanelText, reservationInfo.get("img_path"), reservationInfo);

        reservationPanel.add(reservationInfoPanel);
        reservationPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

        return reservationPanel;
    }

    void createScrollButtons(int elementId, JPanel reservationPanel) {

        // mock values
        HashMap<Integer, Boolean> isElRated = new HashMap<>();
        isElRated.put(1, true); isElRated.put(2, true); isElRated.put(3, false);
        isElRated.put(4, false); isElRated.put(5, true);
        HashMap<Integer, Boolean> hasElPenalty = new HashMap<>();
        hasElPenalty.put(1, true); hasElPenalty.put(2, false); hasElPenalty.put(3, true);
        hasElPenalty.put(4, false); hasElPenalty.put(5, true);

        boolean rated = isElRated.get(elementId); boolean penalty = hasElPenalty.get(elementId);

        ScrollElementButton reviewButton;
        ActionListener reviewActionListener;
        if (rated == false) {
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

        if (penalty == true) {
            reservationPanel.add(Box.createRigidArea(new Dimension(frameHeight/14,0)));

            ScrollElementButton penaltiesButton = new ScrollElementButton("See penalties", frameHeight/7, frameHeight/7, Color.decode("#A84B4C"), statusWrong, fontButtons, true, elementId);
            ActionListener penatlyActionListener = new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    ScrollElementButton button = (ScrollElementButton)actionEvent.getSource();
                    System.out.println("Penalty for reservation " + button.elementId);

//                new OfferPageGUI(-1, "None", button.elementId).createGUI();
//                frame.setVisible(false);
                }
            };
            penaltiesButton.addActionListener(penatlyActionListener);
            reservationPanel.add(penaltiesButton);
        }
    }

    public ClientReservationHistoryGUI(int userId, String userType){
        super(userId, userType);
        getElementsData();
        offerHeight = frameHeight/4;
        offerWidth = frameWidth/2;
    }

    public static void main(String[] args) {
        new ClientReservationHistoryGUI(-1, "None").createGUI();
    }

}

