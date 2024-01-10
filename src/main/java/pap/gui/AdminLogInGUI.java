package pap.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import pap.db.entities.Admin;
import pap.gui.components.LogoPanel;
import pap.gui.components.RoundedButtonDefault;
import pap.logic.login.*;
import pap.logic.ErrorCodes;

public class AdminLogInGUI extends BaseGUI {
    JPanel mainPanel, centerPanel, loginPanel, adminLabelPanel;
    LogoPanel logoPanel;
    JLabel usernameLabel, adminLabel, passwordLabel, statusLabel;
    JTextField usernameInputField;
    JPasswordField passwordInputField;
    RoundedButtonDefault logInButton;

    public AdminLogInGUI(int userId, String userType) {
        super(userId, userType);
    }

    void createCustomGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        int btnHeight = frameHeight / 10;
        int loginPanelWidth = frameWidth / 3;
        int loginPanelHeight = frameHeight - frameHeight / 5 - 2 * btnHeight;

        logoPanel = new LogoPanel(logoColor, frameHeight, Integer.MAX_VALUE, frameHeight / 5);
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
        textPanel.add(Box.createHorizontalGlue());
        textPanel.add(usernameLabel);
        textPanel.add(Box.createHorizontalGlue());
        loginPanel.add(textPanel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        usernameInputField = new JTextField();
        usernameInputField.setFont(fontMiddle);
        usernameInputField.setPreferredSize(new Dimension(frameWidth, 30));
        usernameInputField.setMaximumSize(new Dimension(frameWidth, 30));
        loginPanel.add(usernameInputField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        passwordLabel = new JLabel("Password:", JLabel.CENTER);
        passwordLabel.setFont(fontMiddle);
        JPanel textPanel2 = new JPanel();
        textPanel2.setLayout(new BoxLayout(textPanel2, BoxLayout.LINE_AXIS));
        textPanel2.setBackground(bgColor);
        textPanel2.add(Box.createHorizontalGlue());
        textPanel2.add(passwordLabel);
        textPanel2.add(Box.createHorizontalGlue());
        loginPanel.add(textPanel2);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        passwordInputField = new JPasswordField();
        passwordInputField.setEchoChar('•');
        passwordInputField.setFont(fontMiddle);
        passwordInputField.setPreferredSize(new Dimension(frameWidth, 30));
        passwordInputField.setMaximumSize(new Dimension(frameWidth, 30));
        loginPanel.add(passwordInputField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        centerPanel.add(Box.createHorizontalGlue());
        centerPanel.add(loginPanel);
        centerPanel.add(Box.createHorizontalGlue());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setPreferredSize(new Dimension(loginPanelWidth, btnHeight+20));
        buttonsPanel.setMaximumSize(new Dimension(loginPanelWidth, btnHeight+20));
        buttonsPanel.setBackground(bgColor);
        loginPanel.add(buttonsPanel);

        logInButton = new RoundedButtonDefault("Log in", frameWidth * 3 / 20, btnHeight, false, false);
        logInButton.addActionListener(e -> logInClickedAction());
        buttonsPanel.add(logInButton);

        statusLabel = new JLabel("<html>Insert your data</html>", JLabel.CENTER);
        statusLabel.setFont(fontSmaller);
        statusLabel.setForeground(statusNeutral);
        JPanel textPanel3 = new JPanel();
        textPanel3.setLayout(new BoxLayout(textPanel3, BoxLayout.LINE_AXIS));
        textPanel3.setBackground(bgColor);
        textPanel3.add(Box.createHorizontalGlue());
        textPanel3.add(statusLabel);
        textPanel3.add(Box.createHorizontalGlue());
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(textPanel3);
        loginPanel.add(Box.createVerticalGlue());

        adminLabelPanel = new JPanel();
        adminLabelPanel.setLayout(new BoxLayout(adminLabelPanel, BoxLayout.LINE_AXIS));
        adminLabelPanel.setBackground(bgColor);
        adminLabelPanel.add(Box.createHorizontalGlue());
        adminLabel = new JLabel("ADMIN PANEL", JLabel.LEFT);
        adminLabelPanel.add(adminLabel);
        adminLabelPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        mainPanel.add(adminLabelPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
    }

    void logInClickedAction() {
        String usernameText = usernameInputField.getText();
        String passwordText = passwordInputField.getText();

        // Found user account
        String statusLabelText = "<html>Please wait...</html>";
        statusLabel.setText(statusLabelText);
        statusLabel.setForeground(statusNeutral);
        statusLabel.paintImmediately(statusLabel.getVisibleRect());

        new HomePageGUI(1, "Admin").createGUI();
        frame.setVisible(false);
//        AdminLogin al = new AdminLogin(usernameText, passwordText);
//        Admin admin = al.getAdminAccount();
//        java.util.List<Integer> errorCodesAdmin = al.getErrorCodes();
//
//        // Successful log in
//        if (errorCodesAdmin.isEmpty()) {
//            new HomePageGUI(admin.getAdminId(), "Admin").createGUI();
//            frame.setVisible(false);
//        } else {
//            statusLabelText = "<html>";
//            statusLabelText = statusLabelText + ErrorCodes.getErrorDescription(errorCodesAdmin.get(0));
//            statusLabelText = statusLabelText + "</html>";
//            statusLabel.setText(statusLabelText);
//            statusLabel.setForeground(statusWrong);
//            statusLabel.paintImmediately(statusLabel.getVisibleRect());
//        }
    }

    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new AdminLogInGUI(-1, "None").createGUI();
    }
}
