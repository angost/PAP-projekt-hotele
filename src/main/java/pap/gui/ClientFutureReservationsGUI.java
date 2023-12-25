package pap.gui;


import pap.gui.components.ScrollElementButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFutureReservationsGUI extends ClientReservationHistoryGUI {

    public ClientFutureReservationsGUI(int userId, String userType) {
        super(userId, userType);
        pageName = "Future reservations";
    }

    // mock function
    @Override
    void getElementsData() {
        fittingElementsIds = new Integer[]{2,3,5};
        // fittingElementsIds = new ...()...(userId);
        nrOfElements = fittingElementsIds.length;
    }

    @Override
    void createScrollButtons(int elementId, JPanel reservationPanel) {

        ScrollElementButton prolongButton = new ScrollElementButton("Prolong", frameHeight/7, frameHeight/7, secondColor, secondColorDarker, fontButtons, true, elementId);
        ActionListener prolongActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ScrollElementButton button = (ScrollElementButton)actionEvent.getSource();
                System.out.println("Prolong reservation " + button.elementId);

//                new OfferPageGUI(-1, "None", button.elementId).createGUI();
//                frame.setVisible(false);
            }
        };
        prolongButton.addActionListener(prolongActionListener);
        reservationPanel.add(prolongButton);
        reservationPanel.add(Box.createRigidArea(new Dimension(frameHeight/14,0)));

        ScrollElementButton cancelButton = new ScrollElementButton("Cancel", frameHeight/7, frameHeight/7, Color.decode("#A84B4C"), statusWrong, fontButtons, true, elementId);
        ActionListener cancelActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ScrollElementButton button = (ScrollElementButton)actionEvent.getSource();
                System.out.println("Cancel reservation " + button.elementId);

//                new OfferPageGUI(-1, "None", button.elementId).createGUI();
//                frame.setVisible(false);
            }
        };
        cancelButton.addActionListener(cancelActionListener);
        reservationPanel.add(cancelButton);
    }

    public static void main(String[] args) {
        new ClientFutureReservationsGUI(-1, "None").createGUI();
    }
}