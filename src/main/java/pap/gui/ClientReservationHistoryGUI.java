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
        fittingElementsIds = new Integer[]{4, 2, 1, 3, 5};
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
        reservationInfo.put("date_from", startDate);
        reservationInfo.put("date_to", endDate);
        reservationInfo.put("description", description);
        reservationInfo.put("paid_amount", paidAmount);
        return reservationInfo;
    }
    
    JPanel createScrollElement(int elementId) {

        HashMap<String, String> reservationInfo = getElementData(elementId);

        JPanel reservationPanel = new JPanel();
        reservationPanel.setBackground(Color.magenta);
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

        SeeOfferButton reviewButton = new SeeOfferButton("Rate your stay", frameHeight/7, frameHeight/7, Color.BLUE, Color.CYAN, fontButtons, true, elementId);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                SeeOfferButton button = (SeeOfferButton)actionEvent.getSource();
                System.out.println(button.offerId);

//                new OfferPageGUI(-1, "None", button.offerId).createGUI();
//                frame.setVisible(false);
            }
        };
        reviewButton.addActionListener(actionListener);
        reservationPanel.add(reviewButton);
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

