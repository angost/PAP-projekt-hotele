

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;


public class ChooseAccountTypeGUI extends BaseGUI {

    RoundedButton userButton;
    RoundedButton ownerButton;
    JPanel mainPanel, buttonsPanel, logoPanel, textPanel;
    JLabel chooseUserLabel;
    BufferedImage logoImage;

    void createCustomGUI(){
//        Color bgColor = Color.decode("#fff3b0");
        Color bgColor = Color.decode("#e3e3e3");
        Color logoColor = Color.decode("#9e2a2b");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.LINE_AXIS));
        logoPanel.setBackground(logoColor);
        logoPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, frameHeight/5));
        logoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, frameHeight/5));
        try {
            logoImage = ImageIO.read(new File("res/logo_name_mixed.png"));
            Image scaledLogoImage = logoImage.getScaledInstance((int)(frameHeight/7*3.6), frameHeight/7, Image.SCALE_SMOOTH);
            JLabel logoImageLabel = new JLabel(new ImageIcon(scaledLogoImage));
            logoPanel.add(Box.createRigidArea(new Dimension(20,0)));
            logoPanel.add(logoImageLabel);
            logoPanel.add(Box.createHorizontalGlue());
        } catch (Exception e) {
            ;
        }
        mainPanel.add(logoPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight*3/20)));
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        textPanel.setBackground(bgColor);
        chooseUserLabel = new JLabel("Choose user type:", JLabel.CENTER);
        chooseUserLabel.setFont(chooseUserLabel.getFont().deriveFont(Font.BOLD, 16));
        textPanel.add(Box.createHorizontalGlue()); textPanel.add(chooseUserLabel); textPanel.add(Box.createHorizontalGlue());
        mainPanel.add(textPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,40)));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.setBackground(bgColor);
        userButton = new RoundedButton("User", frameWidth*3/20, frameHeight/10, "#e09f3e", "#b88232", "#e09f3e");
        ownerButton = new RoundedButton("Owner", frameWidth*3/20, frameHeight/10, "#e09f3e", "#b88232", "#e09f3e");
        userButton.addActionListener(e->createUserAccountAction());
        ownerButton.addActionListener(e->createOwnerAccountAction());
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(userButton); buttonsPanel.add(Box.createRigidArea(new Dimension(userButton.preferredWidth/5,0))); buttonsPanel.add(ownerButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        mainPanel.add(buttonsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight*3/10)));

    }

    void createUserAccountAction() {
        JOptionPane.showMessageDialog(frame, "User account created");
    }

    void createOwnerAccountAction() {
        JOptionPane.showMessageDialog(frame, "Owner account created");
    }


    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new ChooseAccountTypeGUI().createGUI();
    }
}
