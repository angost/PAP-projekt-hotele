package pap.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import pap.db.dao.ClientDAO;
import pap.db.dao.OwnerDAO;
import pap.logic.DeactivateAccount;


public class HomePageGUI extends BaseGUI {

    RoundedButton findOffersButton, seeReservationsButton, favouritesButton,
            reservationHistoryButton, reviewsButton, paymentsButton,
            deactivateAccountButton;
    JPanel mainPanel, buttonsPanel, buttonsRowsPanel, textPanel, logoutPanel;
    LogoPanel logoPanel;
    JLabel chooseActionLabel;
    LogOutButton logOutButton;
    int menuButtonWidth = frameWidth*3/20; int menuButtonHeight = frameHeight/10;
    int menuButtonGap = menuButtonWidth/3;

    public HomePageGUI(int userId, String userType) {
        super(userId, userType);
    }

    void createCustomGUI(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        logoPanel = new LogoPanel(logoColor, frameHeight,Integer.MAX_VALUE, frameHeight/5);
        mainPanel.add(logoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight/40)));

        logoutPanel = new JPanel();
        logoutPanel.setLayout(new BoxLayout(logoutPanel, BoxLayout.LINE_AXIS));
        logoutPanel.setBackground(bgColor);
        logoutPanel.add(Box.createHorizontalGlue());
        logOutButton = new LogOutButton(frameHeight/20, frameHeight/20, frameHeight/20, frameHeight/20);
        logOutButton.addActionListener(e -> logOutBtnClickedAction());
        logoutPanel.add(logOutButton); logoutPanel.add(Box.createRigidArea(new Dimension(frameHeight/40,0)));
        mainPanel.add(logoutPanel);

        mainPanel.add(Box.createVerticalGlue());
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        textPanel.setBackground(bgColor);

        String name;
        if (userType.equals("Client")) {
            ClientDAO cd = new ClientDAO();
            name = cd.findById(userId).getName();
        } else if (userType.equals("Owner")) {
            OwnerDAO od = new OwnerDAO();
            name = od.findById(userId).getCompanyName();
        } else {
            name = "User";
        }

        chooseActionLabel = new JLabel("Hello " + name + "!", JLabel.CENTER);
        chooseActionLabel.setFont(fontMiddle);
        textPanel.add(Box.createHorizontalGlue()); textPanel.add(chooseActionLabel); textPanel.add(Box.createHorizontalGlue());
        mainPanel.add(textPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,40)));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.setBackground(bgColor);
        buttonsRowsPanel = new JPanel();
        buttonsRowsPanel.setLayout(new BoxLayout(buttonsRowsPanel, BoxLayout.PAGE_AXIS));
        buttonsRowsPanel.setBackground(bgColor);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
        buttonsPanel.add(buttonsRowsPanel);

        if (userType.equals("Client")){

            JPanel buttonsRow1 = new JPanel();
            buttonsRow1.setLayout(new BoxLayout(buttonsRow1, BoxLayout.LINE_AXIS));
            buttonsRow1.setBackground(bgColor);
            findOffersButton = new MenuButton("Look for offers", "/icons/search_offers.png");
            findOffersButton.addActionListener(e->goToFindOffersAction());
            seeReservationsButton = new MenuButton("See your reservations", "/icons/reservations.png");
            seeReservationsButton.addActionListener(e->goToYourReservationsAction());
            favouritesButton = new MenuButton("See your favourites", "/icons/favourite.png");
            buttonsRow1.add(findOffersButton); buttonsRow1.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow1.add(seeReservationsButton); buttonsRow1.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow1.add(favouritesButton); buttonsRow1.add(Box.createHorizontalGlue());

            JPanel buttonsRow2 = new JPanel();
            buttonsRow2.setLayout(new BoxLayout(buttonsRow2, BoxLayout.LINE_AXIS));
            buttonsRow2.setBackground(bgColor);
            reservationHistoryButton = new MenuButton("See reservation history", "/icons/history.png");
            reviewsButton = new MenuButton("See your reviews", "/icons/reviews.png");
            buttonsRow2.add(reservationHistoryButton); buttonsRow2.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow2.add(reviewsButton); buttonsRow2.add(Box.createHorizontalGlue());

            JPanel buttonsRow3 = new JPanel();
            buttonsRow3.setLayout(new BoxLayout(buttonsRow3, BoxLayout.LINE_AXIS));
            buttonsRow3.setBackground(bgColor);
            paymentsButton = new MenuButton("See payment options", "/icons/payment.png");
            deactivateAccountButton = new MenuButton("Deactivate account", "/icons/deactivate.png");
            deactivateAccountButton.addActionListener(e->deactivateAccountAction());
            buttonsRow3.add(paymentsButton); buttonsRow3.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow3.add(deactivateAccountButton); buttonsRow3.add(Box.createHorizontalGlue());

            buttonsRowsPanel.add(buttonsRow1); buttonsRowsPanel.add(Box.createVerticalGlue());
            buttonsRowsPanel.add(buttonsRow2); buttonsRowsPanel.add(Box.createVerticalGlue());
            buttonsRowsPanel.add(buttonsRow3); buttonsRowsPanel.add(Box.createVerticalGlue());
        }

        mainPanel.add(buttonsPanel);
        mainPanel.add(Box.createVerticalGlue());
    }

    void goToFindOffersAction() {
        new ScrollGUITemplate(userId, userType).createGUI();
        frame.setVisible(false);
    }

    void goToYourReservationsAction() {
        ;
    }

    void deactivateAccountAction() {

        List<Integer> errorCodes = new ArrayList<>();

        if (userType.equals("Client")){
            errorCodes = DeactivateAccount.deactivateUserAccount(userId);
        } else if (userType.equals("Owner")){
            errorCodes = DeactivateAccount.deactivateOwnerAccount(userId);
        } else {
            errorCodes.add(-1);
        }

        if (errorCodes.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Account deactivated");
            new LogInGUI(-1, "None").createGUI();
            frame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(frame, "Account cannot be deactivated");
        }
    }

    void logOutBtnClickedAction(){
        new LogInGUI(-1, "None").createGUI();
        frame.setVisible(false);
    }

    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }

    class MenuButton extends TextIconButton {
        public MenuButton(String text, String imgPath){
            super(text, menuButtonWidth, menuButtonHeight, secondColor, secondColorDarker, fontButtons, false, imgPath, menuButtonHeight/2);
    }
}

    public static void main(String[] args) {
//        new HomePageGUI(-1, "None").createGUI();
        new HomePageGUI(7, "Client").createGUI();
    }
}
