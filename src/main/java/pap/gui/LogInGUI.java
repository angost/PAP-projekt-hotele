package pap.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import pap.db.entities.Client;
import pap.db.entities.Owner;
import pap.logic.login.*;


public class LogInGUI extends BaseGUI {

    JPanel mainPanel, centerPanel, loginPanel, createAccountPanel;
    LogoPanel logoPanel;
    JLabel usernameLabel, createAccountLabel, passwordLabel, statusLabel;
    JTextField usernameInputField, passwordInputField;
    RoundedButton logInClientButton, logInOwnerButton, createAccountButton;

    void createCustomGUI(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        int btnHeight = frameHeight/10;
        int loginPanelWidth = frameWidth/3;
        int loginPanelHeight = frameHeight - frameHeight/5 - 2*btnHeight;

        logoPanel = new LogoPanel(logoColor, frameHeight,Integer.MAX_VALUE, frameHeight/5);
        mainPanel.add(logoPanel);
//        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight*3/20)));

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));
        centerPanel.setPreferredSize(new Dimension(frameWidth, loginPanelHeight));
        centerPanel.setMaximumSize(new Dimension(frameWidth, loginPanelHeight));
        centerPanel.setBackground(bgColor);
        mainPanel.add(centerPanel);
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        loginPanel.setPreferredSize(new Dimension(loginPanelWidth, loginPanelHeight));
        loginPanel.setMaximumSize(new Dimension(loginPanelWidth, loginPanelHeight));
        loginPanel.setBackground(bgColor);
        loginPanel.add(Box.createVerticalGlue());

        usernameLabel = new JLabel("Username:", JLabel.CENTER);
        usernameLabel.setFont(fontMiddle);
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        textPanel.setBackground(bgColor);
        textPanel.add(Box.createHorizontalGlue()); textPanel.add(usernameLabel); textPanel.add(Box.createHorizontalGlue());
        loginPanel.add(textPanel);
        loginPanel.add(Box.createRigidArea(new Dimension(0,10)));

        usernameInputField = new JTextField();
        usernameInputField.setFont(fontMiddle);
        usernameInputField.setPreferredSize(new Dimension(frameWidth, 30));
        usernameInputField.setMaximumSize(new Dimension(frameWidth, 30));
        loginPanel.add(usernameInputField);
        loginPanel.add(Box.createRigidArea(new Dimension(0,20)));

        passwordLabel = new JLabel("Password:", JLabel.CENTER);
        passwordLabel.setFont(fontMiddle);
        JPanel textPanel2 = new JPanel();
        textPanel2.setLayout(new BoxLayout(textPanel2, BoxLayout.LINE_AXIS));
        textPanel2.setBackground(bgColor);
        textPanel2.add(Box.createHorizontalGlue()); textPanel2.add(passwordLabel); textPanel2.add(Box.createHorizontalGlue());
        loginPanel.add(textPanel2);
        loginPanel.add(Box.createRigidArea(new Dimension(0,10)));

        passwordInputField = new JTextField();
        passwordInputField.setFont(fontMiddle);
        passwordInputField.setPreferredSize(new Dimension(frameWidth, 30));
        passwordInputField.setMaximumSize(new Dimension(frameWidth, 30));
        loginPanel.add(passwordInputField);
        loginPanel.add(Box.createRigidArea(new Dimension(0,20)));

        centerPanel.add(Box.createHorizontalGlue()); centerPanel.add(loginPanel); centerPanel.add(Box.createHorizontalGlue());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.setPreferredSize(new Dimension(loginPanelWidth, btnHeight));
        buttonsPanel.setMaximumSize(new Dimension(loginPanelWidth, btnHeight));
        buttonsPanel.setBackground(bgColor);
        loginPanel.add(buttonsPanel);

        logInClientButton = new RoundedButton("Log in as Client", frameWidth*3/20, btnHeight, secondColor, secondColorDarker, fontButtons, false);
        logInClientButton.addActionListener(e->logInClientClickedAction());
        buttonsPanel.add(logInClientButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20,0)));
        logInOwnerButton = new RoundedButton("Log in as Owner", frameWidth*3/20, btnHeight, secondColor, secondColorDarker, fontButtons, false);
        logInOwnerButton.addActionListener(e->logInOwnerClickedAction());
        buttonsPanel.add(logInOwnerButton);

        statusLabel = new JLabel("<html>Insert your data</html>", JLabel.CENTER);
        statusLabel.setFont(fontSmaller);
        statusLabel.setForeground(Color.decode("#7a7373"));
        JPanel textPanel3 = new JPanel();
        textPanel3.setLayout(new BoxLayout(textPanel3, BoxLayout.LINE_AXIS));
        textPanel3.setBackground(bgColor);
        textPanel3.add(Box.createHorizontalGlue());
        textPanel3.add(statusLabel);
//        textPanel3.add(Box.createHorizontalGlue());
        loginPanel.add(textPanel3);
        loginPanel.add(Box.createVerticalGlue());


        createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.LINE_AXIS));
        createAccountPanel.setBackground(bgColor);
        createAccountPanel.add(Box.createHorizontalGlue());
        createAccountLabel = new JLabel("I don't have an account...", JLabel.CENTER);
        createAccountLabel.setFont(fontMiddle);
        createAccountPanel.add(createAccountLabel);
        createAccountPanel.add(Box.createRigidArea(new Dimension(10,0)));
        createAccountButton = new RoundedButton("Create account", frameWidth*3/20, btnHeight, secondColor, secondColorDarker, fontButtons, false);
        createAccountButton.addActionListener(e->createAccountBtnClickedAction());
        createAccountPanel.add(createAccountButton);
        createAccountPanel.add(Box.createRigidArea(new Dimension(10,0)));
        mainPanel.add(createAccountPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));

    }

    void logInClientClickedAction() {
        String usernameText = usernameInputField.getText();
        String passwordText = passwordInputField.getText();

        // Found user account
        UserLogin ul = new UserLogin(usernameText, passwordText);
        Client user = ul.getUserAccount();
        List<Integer> errorCodesUser = ul.getErrorCodes();

        if (errorCodesUser.isEmpty()) {
            new HomePageGUI().createGUI();
            frame.setVisible(false);
        } else {
            statusLabel.setText(String.valueOf(errorCodesUser.get(0)));
        }
    }

    void logInOwnerClickedAction() {
        String usernameText = usernameInputField.getText();
        String passwordText = passwordInputField.getText();

        // Found user account
        OwnerLogin ol = new OwnerLogin(usernameText, passwordText);
        Owner owner = ol.getOwnerAccount();
        List<Integer> errorCodesOwner = ol.getErrorCodes();

        if (errorCodesOwner.isEmpty()) {
            new HomePageGUI().createGUI();
            frame.setVisible(false);
        } else {
            statusLabel.setText(String.valueOf(errorCodesOwner.get(0)));
        }

    }


    void createAccountBtnClickedAction() {
        new ChooseAccountTypeGUI().createGUI();
        frame.setVisible(false);
    }

    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new LogInGUI().createGUI();
    }
}
