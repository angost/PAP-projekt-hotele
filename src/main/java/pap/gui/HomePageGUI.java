package pap.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pap.db.dao.ClientDAO;
import pap.db.dao.OwnerDAO;
import pap.gui.components.LogOutButton;
import pap.gui.components.LogoPanel;
import pap.gui.components.TextIconButton;
import pap.logic.DeactivateAccount;
import pap.logic.ErrorCodes;


public class HomePageGUI extends BaseGUI {

    MenuButton findOffersButton, seeReservationsButton, favouritesButton,
            reservationHistoryButton, reviewsButton, paymentsButton,
            deactivateAccountButton, yourHotelsButton, yourOffersButton,
            addHotelButton, addOfferButton,
            discountsButton;
    JPanel mainPanel, buttonsPanel, buttonsRowsPanel, infoPanel;
    LogoPanel logoPanel;
    JLabel welcomeLabel;
    LogOutButton logOutButton;
    int menuButtonWidth = frameWidth*4/20; int menuButtonHeight = frameHeight*4/40;
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
        mainPanel.add(Box.createVerticalGlue());

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.LINE_AXIS));
        infoPanel.setBackground(bgColor);

        String name; String userImgPath = "";
        if (userType.equals("Client")) {
            ClientDAO cd = new ClientDAO();
            name = cd.findById(userId).getName();
            userImgPath = "/tourist.png";
        } else if (userType.equals("Owner")) {
            OwnerDAO od = new OwnerDAO();
            name = od.findById(userId).getCompanyName();
            userImgPath = "/business.png";
        } else {
            name = "User";
        }
        welcomeLabel = new JLabel("Hello " + name + "!", JLabel.CENTER);
        welcomeLabel.setFont(fontMiddle);
        infoPanel.add(Box.createRigidArea(new Dimension(menuButtonGap*3/2,0))); infoPanel.add(welcomeLabel);

        if (!userImgPath.isEmpty()) {
            try {
                Image userImg = ImageIO.read(new File(getClass().getResource(userImgPath).getPath()));
                userImg = userImg.getScaledInstance(frameHeight/10, frameHeight/10, Image.SCALE_SMOOTH);
                ImageIcon userImgIcon = new ImageIcon(userImg);
                JLabel userImgLabel = new JLabel();
                userImgLabel.setIcon(userImgIcon);
                infoPanel.add(Box.createRigidArea(new Dimension(menuButtonGap/2,0))); infoPanel.add(userImgLabel);
            } catch (Exception e) {
                ;
            }
        }
        infoPanel.add(Box.createHorizontalGlue());

        logOutButton = new LogOutButton(frameHeight/20, frameHeight/20, frameHeight/20, frameHeight/20);
        logOutButton.addActionListener(e -> logOutBtnClickedAction());
        infoPanel.add(logOutButton); infoPanel.add(Box.createRigidArea(new Dimension(frameHeight/40,0)));
        mainPanel.add(infoPanel);
        mainPanel.add(Box.createVerticalGlue());

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.setBackground(bgColor);
        buttonsRowsPanel = new JPanel();
        buttonsRowsPanel.setLayout(new BoxLayout(buttonsRowsPanel, BoxLayout.PAGE_AXIS));
        buttonsRowsPanel.setBackground(bgColor);
//        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
        buttonsPanel.add(buttonsRowsPanel);

        if (userType.equals("Client")){

            JPanel buttonsRow1 = new JPanel();
            buttonsRow1.setLayout(new BoxLayout(buttonsRow1, BoxLayout.LINE_AXIS));
            buttonsRow1.setBackground(bgColor);
            findOffersButton = new MenuButton("Look for offers", "/icons/search_offers.png");
            findOffersButton.addActionListener(e->findOffersAction());
            seeReservationsButton = new MenuButton("See future reservations", "/icons/reservations.png");
            seeReservationsButton.addActionListener(e->seeFutureReservationsAction());
            favouritesButton = new MenuButton("See your favourites", "/icons/favourite.png");
            buttonsRow1.add(findOffersButton); buttonsRow1.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow1.add(seeReservationsButton); buttonsRow1.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow1.add(favouritesButton); buttonsRow1.add(Box.createHorizontalGlue());

            JPanel buttonsRow2 = new JPanel();
            buttonsRow2.setLayout(new BoxLayout(buttonsRow2, BoxLayout.LINE_AXIS));
            buttonsRow2.setBackground(bgColor);
            reservationHistoryButton = new MenuButton("See reservation history", "/icons/history.png");
            reservationHistoryButton.addActionListener(e->seeClientHistoryAction());
            reviewsButton = new MenuButton("See your reviews", "/icons/reviews.png");
            buttonsRow2.add(reservationHistoryButton); buttonsRow2.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow2.add(reviewsButton); buttonsRow2.add(Box.createHorizontalGlue());

            JPanel buttonsRow3 = new JPanel();
            buttonsRow3.setLayout(new BoxLayout(buttonsRow3, BoxLayout.LINE_AXIS));
            buttonsRow3.setBackground(bgColor);
            paymentsButton = new MenuButton("See payment options", "/icons/payment.png");
            deactivateAccountButton = new MenuButton("Deactivate account", "/icons/deactivate.png");
            deactivateAccountButton.fillColor = statusWrongLighter;
            deactivateAccountButton.hoverColor = statusWrong;
            deactivateAccountButton.addActionListener(e->deactivateAccountAction());
            buttonsRow3.add(paymentsButton); buttonsRow3.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow3.add(deactivateAccountButton); buttonsRow3.add(Box.createHorizontalGlue());

            buttonsRowsPanel.add(buttonsRow1); buttonsRowsPanel.add(Box.createVerticalGlue());
            buttonsRowsPanel.add(buttonsRow2); buttonsRowsPanel.add(Box.createVerticalGlue());
            buttonsRowsPanel.add(buttonsRow3); buttonsRowsPanel.add(Box.createVerticalGlue());
        } else if (userType.equals("Owner")) {
            JPanel buttonsRow1 = new JPanel();
            buttonsRow1.setLayout(new BoxLayout(buttonsRow1, BoxLayout.LINE_AXIS));
            buttonsRow1.setBackground(bgColor);
            yourHotelsButton = new MenuButton("See your hotels", "/icons/hotel.png");
            yourHotelsButton.addActionListener(e->seeYourHotelsAction());
            yourOffersButton = new MenuButton("See your offers", "/icons/offer.png");
            yourOffersButton.addActionListener(e->seeYourOffersAction());
            buttonsRow1.add(yourHotelsButton); buttonsRow1.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow1.add(yourOffersButton); buttonsRow1.add(Box.createHorizontalGlue());

            JPanel buttonsRow2 = new JPanel();
            buttonsRow2.setLayout(new BoxLayout(buttonsRow2, BoxLayout.LINE_AXIS));
            buttonsRow2.setBackground(bgColor);
            addHotelButton = new MenuButton("Add hotel", "/icons/add_hotel.png");
            addOfferButton = new MenuButton("Add offer", "/icons/add_offer.png");
            buttonsRow2.add(addHotelButton); buttonsRow2.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow2.add(addOfferButton); buttonsRow2.add(Box.createHorizontalGlue());

            JPanel buttonsRow3 = new JPanel();
            buttonsRow3.setLayout(new BoxLayout(buttonsRow3, BoxLayout.LINE_AXIS));
            buttonsRow3.setBackground(bgColor);
            reservationHistoryButton = new MenuButton("See reservation history", "/icons/history.png");
            reviewsButton = new MenuButton("See reviews", "/icons/reviews.png");
            buttonsRow3.add(reservationHistoryButton); buttonsRow3.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow3.add(reviewsButton); buttonsRow3.add(Box.createHorizontalGlue());

            JPanel buttonsRow4 = new JPanel();
            buttonsRow4.setLayout(new BoxLayout(buttonsRow4, BoxLayout.LINE_AXIS));
            buttonsRow4.setBackground(bgColor);
            discountsButton = new MenuButton("Discount codes", "/icons/discount.png");
            deactivateAccountButton = new MenuButton("Deactivate account", "/icons/deactivate.png");
            deactivateAccountButton.fillColor = statusWrongLighter;
            deactivateAccountButton.hoverColor = statusWrong;
            deactivateAccountButton.addActionListener(e->deactivateAccountAction());
            buttonsRow4.add(discountsButton); buttonsRow4.add(Box.createRigidArea(new Dimension(menuButtonGap,0)));
            buttonsRow4.add(deactivateAccountButton); buttonsRow4.add(Box.createHorizontalGlue());

            buttonsRowsPanel.add(buttonsRow1); buttonsRowsPanel.add(Box.createVerticalGlue());
            buttonsRowsPanel.add(buttonsRow2); buttonsRowsPanel.add(Box.createVerticalGlue());
            buttonsRowsPanel.add(buttonsRow3); buttonsRowsPanel.add(Box.createVerticalGlue());
            buttonsRowsPanel.add(buttonsRow4); buttonsRowsPanel.add(Box.createVerticalGlue());
        }

        mainPanel.add(buttonsPanel);
        mainPanel.add(Box.createVerticalGlue());
    }

    void findOffersAction() {
        new SearchOffersGUI(userId, userType).createGUI();
        frame.setVisible(false);
    }

    void seeFutureReservationsAction() {
        new ClientFutureReservationsGUI(userId, userType).createGUI();
        frame.setVisible(false);
    }

    void seeClientHistoryAction() {
        new ClientReservationHistoryGUI(userId, userType).createGUI();
        frame.setVisible(false);
    }

    void deactivateAccountAction() {

        String[] options = {"No", "Yes"};
        int pickedOption = JOptionPane.showOptionDialog(null, "This action is irreversible. Are you sure you want to continue?",
                "Confirm action", 0, 0, null, options, "No");

        if (pickedOption == 1) {
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
                String errorText = "";
                for (Integer errorCode : errorCodes) {
                    errorText = errorText + ErrorCodes.getErrorDescription(errorCode) + " ";
                }

                JOptionPane.showMessageDialog(frame, errorText);
            }
        }
    }

    void seeYourHotelsAction(){
        new OwnerHotelsGUI(userId, userType).createGUI();
        frame.setVisible(false);
    }

    void seeYourOffersAction(){
        new OwnerOffersGUI(userId, userType).createGUI();
        frame.setVisible(false);
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
            super(text, menuButtonWidth, menuButtonHeight, secondColor, secondColorDarker, fontButtons, false, imgPath, menuButtonHeight*2/3);
        }
    }

    public static void main(String[] args) {
//        new HomePageGUI(-1, "None").createGUI();
        new HomePageGUI(1, "Owner").createGUI();
//        new HomePageGUI(8, "Client").createGUI();
    }
}
