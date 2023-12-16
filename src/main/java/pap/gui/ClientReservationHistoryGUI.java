package pap.gui;

import pap.db.dao.ClientDAO;
import pap.db.dao.ReservationDAO;

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
        reservationInfo.put("date_from", startDate);
        reservationInfo.put("date_to", endDate);
        reservationInfo.put("description", description);
        reservationInfo.put("paid_amount", paidAmount);
        reservationInfo.put("status", status);

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

        JPanel reservationInfoPanel = new JPanel();
        reservationInfoPanel.setBackground(neutralGray);
        reservationInfoPanel.setLayout(new BoxLayout(reservationInfoPanel, BoxLayout.PAGE_AXIS));
        reservationInfoPanel.setPreferredSize(new Dimension(offerWidth, offerHeight));
        reservationInfoPanel.setMaximumSize(new Dimension(offerWidth, offerHeight));

        JLabel dateFromLabel = new JLabel("Date from: " + reservationInfo.get("date_from"), JLabel.CENTER);
        dateFromLabel.setPreferredSize(new Dimension(offerWidth, offerHeight));
        dateFromLabel.setMaximumSize(new Dimension(offerWidth, offerHeight));
        dateFromLabel.setFont(fontMiddle);
        reservationInfoPanel.add(dateFromLabel);

        JLabel dateToLabel = new JLabel("Date to: " + reservationInfo.get("date_to"), JLabel.CENTER);
        dateToLabel.setPreferredSize(new Dimension(offerWidth, offerHeight));
        dateToLabel.setMaximumSize(new Dimension(offerWidth, offerHeight));
        dateToLabel.setFont(fontMiddle);
        reservationInfoPanel.add(dateToLabel);

        JLabel descriptionLabel = new JLabel(reservationInfo.get("description"), JLabel.CENTER);
        descriptionLabel.setPreferredSize(new Dimension(offerWidth, offerHeight));
        descriptionLabel.setMaximumSize(new Dimension(offerWidth, offerHeight));
        descriptionLabel.setFont(fontMiddle);
        reservationInfoPanel.add(descriptionLabel);

        JLabel paidAmountLabel = new JLabel(reservationInfo.get("paid_amount"), JLabel.CENTER);
        paidAmountLabel.setPreferredSize(new Dimension(offerWidth, offerHeight));
        paidAmountLabel.setMaximumSize(new Dimension(offerWidth, offerHeight));
        paidAmountLabel.setFont(fontMiddle);
        paidAmountLabel.setForeground(Color.RED);
        reservationInfoPanel.add(paidAmountLabel);

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
        offerWidth = frameWidth/3;
    }

    public static void main(String[] args) {
        new ClientReservationHistoryGUI(-1, "None").createGUI();
    }

}

