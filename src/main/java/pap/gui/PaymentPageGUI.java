package pap.gui;

import pap.gui.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class PaymentPageGUI extends BaseGUI {

    JPanel mainPanel;
    String pageName = "";
    int offerId;

    void createCustomGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        int logoPanelHeight = frameHeight / 10; int footerHeight = frameHeight/10;
        int gapSmall = frameHeight/60; int gapMedium = frameHeight/10; int gapBig = frameHeight/7;
        LogoPanel logoPanel = new LogoPanel(logoColor, frameHeight, frameWidth, logoPanelHeight);
        mainPanel.add(logoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, gapBig)));

        JPanel textPanel1 = new JPanel();
        textPanel1.setLayout(new BoxLayout(textPanel1, BoxLayout.LINE_AXIS));
        textPanel1.setBackground(bgColor);
        mainPanel.add(textPanel1);
        JPanel resDetailsPanel = new JPanel();
        resDetailsPanel.setLayout(new BoxLayout(resDetailsPanel, BoxLayout.PAGE_AXIS));
        resDetailsPanel.setBackground(bgColor);
        textPanel1.add(resDetailsPanel);

        textPanel1.add(Box.createRigidArea(new Dimension(gapMedium, 0)));
        textPanel1.add(resDetailsPanel);
        textPanel1.add(Box.createHorizontalGlue());

        JLabel resDetailsLabel = new JLabel("Reservation details:", JLabel.LEFT);
        resDetailsLabel.setFont(fontMiddleBold);
        resDetailsPanel.add(resDetailsLabel);

        JLabel cityLabel = new JLabel("City: " +  "Warszawa", JLabel.LEFT);
        cityLabel.setFont(fontMiddle);
        resDetailsPanel.add(cityLabel);
        JLabel datesLabel = new JLabel("Date from: " +  "20.05.2023" + "     Date to: " + "23.05.2023", JLabel.LEFT);
        datesLabel.setFont(fontMiddle);
        resDetailsPanel.add(datesLabel);
        JLabel nameLabel = new JLabel("Name: " +  "Luxury Room", JLabel.LEFT);
        nameLabel.setFont(fontMiddle);
        resDetailsPanel.add(nameLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, gapSmall*2)));

        JPanel textPanel2 = new JPanel();
        textPanel2.setLayout(new BoxLayout(textPanel2, BoxLayout.LINE_AXIS));
        textPanel2.setBackground(bgColor);
        mainPanel.add(textPanel2);
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.PAGE_AXIS));
        paymentPanel.setBackground(bgColor);
        textPanel2.add(paymentPanel);

        textPanel2.add(Box.createRigidArea(new Dimension(gapMedium, 0)));
        textPanel2.add(paymentPanel);
        textPanel2.add(Box.createHorizontalGlue());

        JLabel paymentPanelLabel = new JLabel("Payment:", JLabel.LEFT);
        paymentPanelLabel.setFont(fontMiddleBold);
        paymentPanel.add(paymentPanelLabel);

        JLabel amountLabel = new JLabel("Payment amount: " + "457,00 PLN", JLabel.LEFT);
        amountLabel.setFont(fontMiddle);
        paymentPanel.add(amountLabel);
        JLabel paymentOptionLabel = new JLabel("Payment option:", JLabel.LEFT);
        paymentOptionLabel.setFont(fontMiddle);
        paymentPanel.add(paymentOptionLabel);
        paymentPanel.add(Box.createRigidArea(new Dimension(0, gapSmall)));

        JPanel paymentOptionPanel = new JPanel();
        paymentOptionPanel.setLayout(new BoxLayout(paymentOptionPanel, BoxLayout.LINE_AXIS));
        paymentOptionPanel.setBackground(bgColor);
        paymentOptionPanel.add(Box.createRigidArea(new Dimension(gapMedium*3/2, 0)));

        String[] payOptionData = {"Card 1", "Card 2", "Card 3", "Card 4"};
        JComboBox payOptionComboBox = new JComboBox(payOptionData);
        payOptionComboBox.setFont(fontMiddle);
        List<String> strings = Arrays.asList(payOptionData);
        String longestEl = strings.stream().
                max(Comparator.comparingInt(String::length)).get();
        int longestElWidth = payOptionComboBox.getFontMetrics(fontMiddle).stringWidth(longestEl);
        payOptionComboBox.setPreferredSize(new Dimension(longestElWidth+50, gapSmall*3));
        payOptionComboBox.setMaximumSize(new Dimension(longestElWidth+50, gapSmall*3));
        paymentOptionPanel.add(payOptionComboBox);

        paymentOptionPanel.add(Box.createRigidArea(new Dimension(gapMedium/2, 0)));
        RoundedButtonDefault addPaymentOptionButton = new RoundedButtonDefault("Add a card", frameWidth*2/20, frameHeight/20, false, true);
        addPaymentOptionButton.addActionListener(e-> addPayOptBtnClickedAction());
        paymentOptionPanel.add(addPaymentOptionButton); paymentOptionPanel.add(Box.createHorizontalGlue());
        mainPanel.add(paymentOptionPanel);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
        btnPanel.setBackground(bgColor);
        RoundedButtonDefault payButton = new RoundedButtonDefault("Pay", frameWidth*3/20, frameHeight/10, false, false);
        payButton.addActionListener(e-> payBtnClickedAction());
        btnPanel.add(Box.createRigidArea(new Dimension(gapMedium, 0)));
        btnPanel.add(payButton); btnPanel.add(Box.createHorizontalGlue());
        mainPanel.add(Box.createRigidArea(new Dimension(0, gapMedium)));
        mainPanel.add(btnPanel);

        mainPanel.add(Box.createVerticalGlue());
        UndoPanel undoPanel = new UndoPanel(frameWidth, frameHeight/20, bgColor, e->undoBtnClickedAction(), pageName, fontMiddle);
        mainPanel.add(undoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, gapSmall)));

    }

    void payBtnClickedAction(){
        // przechowywac jakos filtry jakie byly ustawione
        System.out.println("PAID");
    }

    void addPayOptBtnClickedAction(){
        // przechowywac jakos filtry jakie byly ustawione
        System.out.println("Added payment option");
    }

    void undoBtnClickedAction(){
        // przechowywac jakos filtry jakie byly ustawione
        new OfferDetailsGUI(userId, userType, offerId).createGUI();
        frame.setVisible(false);
    }

    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }

    public PaymentPageGUI(int userId, String userType, int offerId){
        super(userId, userType);
        this.offerId = offerId;
        pageName = "Finalise reservation";
    }

    public static void main(String[] args) {
        new PaymentPageGUI(-1, "None", 1).createGUI();
    }
}
