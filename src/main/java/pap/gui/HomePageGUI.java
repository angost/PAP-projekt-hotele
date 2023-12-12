package pap.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import pap.db.dao.ClientDAO;
import pap.db.dao.OwnerDAO;
import pap.logic.DeactivateAccount;


public class HomePageGUI extends BaseGUI {

    RoundedButton findOffersButton, seeReservationsButton, desactivateAccountButton;
    JPanel mainPanel, buttonsPanel, textPanel, logoutPanel;
    LogoPanel logoPanel;
    JLabel chooseActionLabel;
    LogOutButton logOutButton;

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

        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight*3/20)));
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
        buttonsPanel.add(Box.createHorizontalGlue());
        if (userType.equals("Client")){
            findOffersButton = new RoundedButton("Look for offers", frameWidth*3/20, frameHeight/10, secondColor, secondColorDarker, fontButtons, false);
            seeReservationsButton = new RoundedButton("See your reservations", frameWidth*3/20, frameHeight/10, secondColor, secondColorDarker, fontButtons, false);
            findOffersButton.addActionListener(e->goToFindOffersAction());
            seeReservationsButton.addActionListener(e->goToYourReservationsAction());

            buttonsPanel.add(findOffersButton); buttonsPanel.add(Box.createRigidArea(new Dimension(findOffersButton.preferredWidth/5,0)));
            buttonsPanel.add(seeReservationsButton); buttonsPanel.add(Box.createRigidArea(new Dimension(findOffersButton.preferredWidth/5,0)));
        }
        desactivateAccountButton = new RoundedButton("Desactivate account", frameWidth*3/20, frameHeight/10, secondColor, secondColorDarker, fontButtons, false);
        desactivateAccountButton.addActionListener(e->desactivateAccountAction());
        buttonsPanel.add(desactivateAccountButton);
        buttonsPanel.add(Box.createHorizontalGlue());
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

    void desactivateAccountAction() {

        List<Integer> errorCodes = new ArrayList<>();

        if (userType.equals("Client")){
            errorCodes = DeactivateAccount.deactivateUserAccount(userId);
        } else if (userType.equals("Owner")){
            errorCodes = DeactivateAccount.deactivateOwnerAccount(userId);
        } else {
            errorCodes.add(-1);
        }

        if (errorCodes.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Account desactivated");
            new LogInGUI(-1, "None").createGUI();
            frame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(frame, "Account cannot be desactivated");
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


    public static void main(String[] args) {
        new HomePageGUI(-1, "None").createGUI();
    }
}
