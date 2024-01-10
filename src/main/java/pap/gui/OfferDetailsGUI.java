package pap.gui;

import pap.db.dao.OfferDAO;
import pap.db.dao.RatingDAO;
import pap.db.entities.Offer;
import pap.logic.guiAction.OfferDetails;
import pap.gui.components.LogoPanel;
import pap.gui.components.OfferDetailsPanel;
import pap.gui.components.RoundedButtonDefault;
import pap.gui.components.UndoButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class OfferDetailsGUI extends BaseGUI {

    JPanel mainPanel;
    int offerId;

    void createCustomGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        int logoPanelHeight = frameHeight / 10; int footerHeight = frameHeight/10;
        int gap = frameHeight/30; int gap2 = frameHeight/60;
        LogoPanel logoPanel = new LogoPanel(logoColor, frameHeight, frameWidth, logoPanelHeight);
        mainPanel.add(logoPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, gap)));
        OfferDetails offerDetails = new OfferDetails();
        HashMap<String, String> offerInfo = offerDetails.getOfferInfo(offerId);
        Image image = new OfferDAO().getImageById(offerId);
        // Should be info passed to this class's constructor - hashmap<String,String>, which will be later passed to payment view
        HashMap<String, String> reservationInfo = new HashMap<>();
        OfferDetailsPanel offerPanel = new OfferDetailsPanel(neutralGray, fontBigger, fontBiggerBold, fontMiddle,
                fontMiddleBold, fontSmaller, fontSmallerBold, frameWidth, frameHeight - logoPanelHeight - footerHeight - gap - gap2*2,
                offerInfo, image, reservationInfo, offerId, userId, userType);
        mainPanel.add(offerPanel);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.LINE_AXIS));
        footerPanel.setBackground(bgColor);
        footerPanel.setPreferredSize(new Dimension(frameWidth, footerHeight));
        footerPanel.setMaximumSize(new Dimension(frameWidth, footerHeight));

        int undoButtonSize = footerHeight/2;
        footerPanel.add(Box.createRigidArea(new Dimension(undoButtonSize/2, 0)));
        UndoButton undoButton = new UndoButton(undoButtonSize, undoButtonSize, undoButtonSize, undoButtonSize);
        undoButton.addActionListener(e->undoBtnClickedAction());
        footerPanel.add(undoButton);
        footerPanel.add(Box.createHorizontalGlue());

        if (userType.equals("Client")) {
            RoundedButtonDefault reserveButton = new RoundedButtonDefault("Reserve", frameWidth*3/20, frameHeight/10, false, false);
            reserveButton.addActionListener(e-> reserveBtnClickedAction());
            footerPanel.add(reserveButton);
            footerPanel.add(Box.createRigidArea(new Dimension(undoButtonSize/2, 0)));
        }

        mainPanel.add(Box.createRigidArea(new Dimension(0,gap2)));
        mainPanel.add(footerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,gap2)));
    }

    void undoBtnClickedAction(){
        // przechowywac jakos filtry jakie byly ustawione
        new SearchOffersGUI(userId, userType).createGUI();
        frame.setVisible(false);
    }

    void reserveBtnClickedAction(){
        System.out.println("User " + userId + " reserved offer " + offerId);
        new ReservationGUI(userId, userType, offerId).createGUI();
        frame.setVisible(false);
    }

    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }

    public OfferDetailsGUI(int userId, String userType, int offerId){
        super(userId, userType);
        this.offerId = offerId;
    }

    public static void main(String[] args) {
        new OfferDetailsGUI(-1, "None", 1).createGUI();
    }
}
