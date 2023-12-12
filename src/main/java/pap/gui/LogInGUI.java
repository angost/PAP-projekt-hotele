package pap.gui;

import javax.swing.*;
import java.awt.*;


public class LogInGUI extends BaseGUI {

    JPanel mainPanel, centerPanel, loginPanel, createAccountPanel;
    LogoPanel logoPanel;
    JLabel usernameLabel, createAccountLabel, passwordLabel;
    JTextField usernameInputField, passwordInputField;
    RoundedButton logInButton, createAccountButton;

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
        centerPanel.setPreferredSize(new Dimension(frameWidth, frameHeight - frameHeight/5 - 2*btnHeight));
        centerPanel.setMaximumSize(new Dimension(frameWidth, frameHeight - frameHeight/5 - 2*btnHeight));
        centerPanel.setBackground(bgColor);
        mainPanel.add(centerPanel);
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        loginPanel.setPreferredSize(new Dimension(textFieldWidth, frameHeight - frameHeight/5 - 2*btnHeight));
        loginPanel.setMaximumSize(new Dimension(textFieldWidth, frameHeight - frameHeight/5 -2*btnHeight));
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
        loginPanel.add(Box.createRigidArea(new Dimension(0,10)));

        centerPanel.add(Box.createHorizontalGlue()); centerPanel.add(loginPanel); centerPanel.add(Box.createHorizontalGlue());

        logInButton = new RoundedButton("Log in", frameWidth*3/20, btnHeight, secondColor, secondColorDarker, fontButtons, false);
        logInButton.addActionListener(e->logInBtnClickedAction());
        loginPanel.add(logInButton);
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

    void logInBtnClickedAction() {
        String usernameText = usernameInputField.getText();
        String passwordText = passwordInputField.getText();
        boolean dataValid = true; //tyczmaczowo, docelowo funkcja
        if (usernameText.isEmpty() || passwordText.isEmpty()){
            dataValid = false;
        }

        if (dataValid) {
            new HomePageGUI().createGUI();
            frame.setVisible(false);
            // go to home page
        } else {
            JOptionPane.showMessageDialog(frame, "Couldn't log in");
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
