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
        
        int textFieldWidth = frameWidth/3;
        int btnHeight = frameHeight/10;

        logoPanel = new LogoPanel(logoColor, frameHeight,Integer.MAX_VALUE, frameHeight/5);
        mainPanel.add(logoPanel);
//        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight*3/20)));

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));
        centerPanel.setPreferredSize(new Dimension(frameWidth, frameHeight - frameHeight/5 - 3*btnHeight));
        centerPanel.setMaximumSize(new Dimension(frameWidth, frameHeight - frameHeight/5 - 3*btnHeight));
        centerPanel.setBackground(bgColor);
        mainPanel.add(centerPanel);
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        loginPanel.setPreferredSize(new Dimension(textFieldWidth, frameHeight - frameHeight/5 - 3*btnHeight));
        loginPanel.setMaximumSize(new Dimension(textFieldWidth, frameHeight - frameHeight/5 -3*btnHeight));
        loginPanel.setBackground(bgColor);
        loginPanel.add(Box.createVerticalGlue());

        usernameLabel = new JLabel("Username:", JLabel.CENTER);
        usernameLabel.setFont(fontMiddle);
        loginPanel.add(usernameLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0,10)));

        usernameInputField = new JTextField();
        usernameInputField.setFont(fontMiddle);
        usernameInputField.setPreferredSize(new Dimension(frameWidth, 30));
        usernameInputField.setMaximumSize(new Dimension(frameWidth, 30));
        loginPanel.add(usernameInputField);
        loginPanel.add(Box.createRigidArea(new Dimension(0,20)));

        passwordLabel = new JLabel("Password:", JLabel.CENTER);
        passwordLabel.setFont(fontMiddle);
        loginPanel.add(passwordLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0,10)));

        passwordInputField = new JTextField();
        passwordInputField.setFont(fontMiddle);
        passwordInputField.setPreferredSize(new Dimension(frameWidth, 30));
        passwordInputField.setMaximumSize(new Dimension(frameWidth, 30));
        loginPanel.add(passwordInputField);
        loginPanel.add(Box.createRigidArea(new Dimension(0,20)));

        centerPanel.add(Box.createHorizontalGlue()); centerPanel.add(loginPanel); centerPanel.add(Box.createHorizontalGlue());


        JPanel buttonsPanel = new JPanel();
        logInClientButton = new RoundedButton("Log in as Client", frameWidth*3/20, btnHeight, secondColor, secondColorDarker, fontButtons, false);
        logInClientButton.addActionListener(e->logInClientClickedAction());
        loginPanel.add(logInClientButton);
        loginPanel.add(Box.createRigidArea(new Dimension(0,20)));
        logInOwnerButton = new RoundedButton("Log in as Owner", frameWidth*3/20, btnHeight, secondColor, secondColorDarker, fontButtons, false);
        logInOwnerButton.addActionListener(e->logInOwnerClickedAction());
        loginPanel.add(logInOwnerButton);

        statusLabel = new JLabel("<html>Insert your data</html>", JLabel.LEFT);
        statusLabel.setFont(fontSmaller);
        statusLabel.setForeground(Color.decode("#7a7373"));
        loginPanel.add(statusLabel);

        mainPanel.add(Box.createVerticalGlue());



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
