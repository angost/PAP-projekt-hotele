package pap.gui;

import javax.swing.*;
import java.awt.*;


public class ChooseAccountTypeGUI extends BaseGUI {

    RoundedButton clientButton, ownerButton, loginButton;
    JPanel mainPanel, buttonsPanel, textPanel, loginPanel;
    LogoPanel logoPanel;
    JLabel chooseUserLabel, loginLabel;

    public ChooseAccountTypeGUI(int userId, String userType) {
        super(userId, userType);
    }

    void createCustomGUI(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        logoPanel = new LogoPanel(logoColor, frameHeight,Integer.MAX_VALUE, frameHeight/5);
        mainPanel.add(logoPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight*3/20)));
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        textPanel.setBackground(bgColor);
        chooseUserLabel = new JLabel("Create new account:", JLabel.CENTER);
        chooseUserLabel.setFont(fontMiddle);
        textPanel.add(Box.createHorizontalGlue()); textPanel.add(chooseUserLabel); textPanel.add(Box.createHorizontalGlue());
        mainPanel.add(textPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,40)));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.setBackground(bgColor);
        clientButton = new RoundedButton("Client", frameWidth*3/20, frameHeight/10, secondColor, secondColorDarker, fontButtons, false);
        ownerButton = new RoundedButton("Owner", frameWidth*3/20, frameHeight/10, secondColor, secondColorDarker, fontButtons, false);
        clientButton.addActionListener(e->goToClientCreationFormAction());
        ownerButton.addActionListener(e->goToOwnerCreationFormAction());
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(clientButton); buttonsPanel.add(Box.createRigidArea(new Dimension(clientButton.preferredWidth/5,0))); buttonsPanel.add(ownerButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        mainPanel.add(buttonsPanel);
//        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight*3/10)));

        mainPanel.add(Box.createVerticalGlue());
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.LINE_AXIS));
        loginPanel.setBackground(bgColor);
        loginPanel.add(Box.createHorizontalGlue());
        loginLabel = new JLabel("I already have an account...", JLabel.CENTER);
        loginLabel.setFont(fontMiddle);
        loginPanel.add(loginLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(10,0)));
        loginButton = new RoundedButton("Log in", frameWidth*3/20, frameHeight/10, secondColor, secondColorDarker, fontButtons, false);
        loginButton.addActionListener(e-> logInBtnClickedAction());
        loginPanel.add(loginButton);
        loginPanel.add(Box.createRigidArea(new Dimension(10,0)));
        mainPanel.add(loginPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));

    }

    void goToClientCreationFormAction() {
        new ClientFormGUI(-1, "None").createGUI();
        frame.setVisible(false);
    }

    void goToOwnerCreationFormAction() {
        new OwnerFormGUI(-1, "None").createGUI();
        frame.setVisible(false);
    }

    void logInBtnClickedAction() {
        new LogInGUI(-1, "None").createGUI();
        frame.setVisible(false);
    }


    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new ChooseAccountTypeGUI(-1, "None").createGUI();
    }
}
