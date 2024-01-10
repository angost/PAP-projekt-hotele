package pap.gui;


import pap.gui.components.ScrollElementButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pap.logic.reservation.ReservationFunctionality;

public class ClientFutureReservationsGUI extends ClientReservationHistoryGUI {

    public ClientFutureReservationsGUI(int userId, String userType) {
        super(userId, userType);
        pageName = "Future reservations";
    }

    // mock function
    @Override
    void getElementsData() {
        this.fittingElementsIds = new Integer[]{2,3,5};
        // fittingElementsIds = new ...()...(userId);
        this.nrOfElements = fittingElementsIds.length;
    }

    @Override
    void createScrollButtons(int elementId, JPanel reservationPanel) {

        ScrollElementButton prolongButton = new ScrollElementButton("Prolong", frameHeight/7, frameHeight/7, secondColor, secondColorDarker, fontButtons, true, elementId);
        ActionListener prolongActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ScrollElementButton button = (ScrollElementButton)actionEvent.getSource();
                System.out.println("Prolong reservation " + button.elementId);

                new ChangeReservationDateGUI(userId, userType, button.elementId).createGUI();
                frame.setVisible(false);
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
                int result = JOptionPane.showConfirmDialog(null, "Do you really want to cancel this reservation?", "Cancel confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.out.println("Cancel reservation " + button.elementId);
                    new ReservationFunctionality(button.elementId).cancelReservation();
                }
            }
        };
        cancelButton.addActionListener(cancelActionListener);
        reservationPanel.add(cancelButton);
    }

    public static void main(String[] args) {
        new ClientFutureReservationsGUI(-1, "None").createGUI();
    }
}