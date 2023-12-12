package pap.gui;

import javax.swing.*;
import java.awt.*;


public class ChooseAccountTypeGUI extends BaseGUI {

    RoundedButton clientButton;
    RoundedButton ownerButton;
    JPanel mainPanel, buttonsPanel, textPanel;
    LogoPanel logoPanel;
    JLabel chooseUserLabel;

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
        chooseUserLabel = new JLabel("Choose user type:", JLabel.CENTER);
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
        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight*3/10)));

    }

    void goToClientCreationFormAction() {
        new ClientFormGUI().createGUI();
        frame.setVisible(false);
    }

    void goToOwnerCreationFormAction() {
        new OwnerFormGUI().createGUI();
        frame.setVisible(false);
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
