package pap.gui.components;

import pap.gui.components.RoundedButtonDefault;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class OfferDetailsPanel extends JPanel {

    int panelWidth, panelHeight;
    Color bgColor; Font fontBigger, fontMiddle, fontMiddleBold, fontSmaller, fontSmallerBold;
    RoundedButtonDefault seeHotelButton;

    public OfferDetailsPanel(Color bgColor, Font fontBigger, Font fontBiggerBold, Font fontMiddle, Font fontMiddleBold, Font fontSmaller, Font fontSmallerBold, int panelWidth, int panelHeight,
                             HashMap<String, String> offerInfo, HashMap<String, String> reservationInfo) {

        this.panelWidth = panelWidth; this.panelHeight = panelHeight;
        this.bgColor = bgColor; this.fontBigger = fontBigger; this.fontMiddle = fontMiddle;
        this.fontMiddleBold = fontMiddleBold; this.fontSmaller = fontSmaller; this.fontSmallerBold = fontSmallerBold;
        int topPanelHeight = panelHeight/6;

        setBackground(bgColor);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setMaximumSize(new Dimension(panelWidth, panelHeight));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(bgColor);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.setPreferredSize(new Dimension(panelWidth, topPanelHeight));
        topPanel.setMaximumSize(new Dimension(panelWidth, topPanelHeight));
        topPanel.add(Box.createRigidArea(new Dimension(10,0)));

        JLabel offerTitle1 = new JLabel(offerInfo.get("name"), JLabel.LEFT);
        offerTitle1.setFont(fontBiggerBold);
        JLabel offerTitle2 = new JLabel(" by ", JLabel.LEFT);
        offerTitle2.setFont(fontBigger);
        JLabel offerTitle3 = new JLabel(offerInfo.get("hotel"), JLabel.LEFT);
        offerTitle3.setFont(fontBiggerBold);

        topPanel.add(offerTitle1); topPanel.add(offerTitle2); topPanel.add(offerTitle3);
        topPanel.add(Box.createRigidArea(new Dimension(20,0)));
        seeHotelButton = new RoundedButtonDefault("See hotel", panelWidth/12, topPanelHeight/2, false, true);
        topPanel.add(seeHotelButton);
        topPanel.add(Box.createRigidArea(new Dimension(10,0)));
        add(topPanel);

        int contentPanelsHeight = panelHeight - topPanelHeight;
        JPanel groupPanel1 = new JPanel();
        groupPanel1.setBackground(bgColor);
        groupPanel1.setLayout(new BoxLayout(groupPanel1, BoxLayout.LINE_AXIS));
        groupPanel1.setPreferredSize(new Dimension(panelWidth, contentPanelsHeight));
        groupPanel1.setMaximumSize(new Dimension(panelWidth, contentPanelsHeight));
        groupPanel1.add(Box.createRigidArea(new Dimension(10,0)));

        int leftPanelWidth = panelWidth*4/10;
        JPanel contentPanelLeft = new JPanel();
        contentPanelLeft.setBackground(bgColor);
        contentPanelLeft.setLayout(new BoxLayout(contentPanelLeft, BoxLayout.PAGE_AXIS));
        contentPanelLeft.setPreferredSize(new Dimension(leftPanelWidth, contentPanelsHeight));
        contentPanelLeft.setMaximumSize(new Dimension(leftPanelWidth, contentPanelsHeight));
        groupPanel1.add(contentPanelLeft);
        JPanel contentPanelRight= new JPanel();
        contentPanelRight.setBackground(bgColor);
        contentPanelRight.setLayout(new BoxLayout(contentPanelRight, BoxLayout.PAGE_AXIS));
        contentPanelRight.setPreferredSize(new Dimension(panelWidth - leftPanelWidth, contentPanelsHeight));
        contentPanelRight.setMaximumSize(new Dimension(panelWidth - leftPanelWidth, contentPanelsHeight));
        groupPanel1.add(contentPanelRight);
        add(groupPanel1);
        groupPanel1.add(Box.createRigidArea(new Dimension(10,0)));

        // LEFT PANEL
        int offerImgWidth = leftPanelWidth; int offerImgHeight = contentPanelsHeight/2;
        try {
            Image offerImg = ImageIO.read(new File(getClass().getResource(offerInfo.get("img_path")).getPath()));
            offerImg = offerImg.getScaledInstance(offerImgWidth, offerImgHeight, Image.SCALE_SMOOTH);
            ImageIcon offerImgIcon = new ImageIcon(offerImg);
            JLabel offerImgLabel = new JLabel();
            offerImgLabel.setIcon(offerImgIcon);
            contentPanelLeft.add(offerImgLabel);
        } catch (Exception e) {
            JPanel imgPanel = new JPanel();
            imgPanel.setBackground(Color.RED);
            imgPanel.setPreferredSize(new Dimension(offerImgWidth, offerImgHeight));
            imgPanel.setMaximumSize(new Dimension(offerImgWidth, offerImgHeight));
            contentPanelLeft.add(imgPanel);
        }
        addJLabel("<html><p style='font-family:verdana'>" + offerInfo.get("facilities_yes") + "</p></html>", Color.BLACK, fontSmaller, contentPanelLeft);
        addJLabel("<html><p style='font-family:verdana'>" + offerInfo.get("facilities_no") + "</p></html>", Color.BLACK, fontSmaller, contentPanelLeft);

        // RIGHT PANEL
        JPanel groupPanel3 = new JPanel();
        groupPanel3.setBackground(bgColor);
        groupPanel3.setLayout(new BoxLayout(groupPanel3, BoxLayout.LINE_AXIS));
        groupPanel3.add(Box.createRigidArea(new Dimension(contentPanelsHeight/30, 0)));
        contentPanelRight.add(groupPanel3);
        addJLabel("<html><p style='font-family:verdana'>" + "<b>Description:</b><br/>" + offerInfo.get("description") + "</p></html>", Color.BLACK, fontSmaller, groupPanel3);
        //        contentPanelRight.add(Box.createVerticalGlue());

        contentPanelRight.add(Box.createRigidArea(new Dimension(0, contentPanelsHeight/30)));

        JPanel  groupPanel4 = new JPanel();
        groupPanel4.setBackground(bgColor);
        groupPanel4.setLayout(new BoxLayout( groupPanel4, BoxLayout.LINE_AXIS));
        groupPanel4.add(Box.createRigidArea(new Dimension(contentPanelsHeight/30, 0)));
        contentPanelRight.add(groupPanel4);

        String locationTitle = "<b>Location:</b><br/>";
        String locationData = offerInfo.get("street") + " " + offerInfo.get("street_nr") + ",<br/>" + offerInfo.get("city") + ", " + offerInfo.get("country");
        addJLabel("<html><p style='font-family:verdana'>" + locationTitle + locationData + "</p></html>", Color.BLACK, fontSmaller,  groupPanel4);

        addJLabel("<html><p style='font-family:verdana'>" + "<b>Room type:</b><br/>" + offerInfo.get("room_type") + "</p></html>", Color.BLACK, fontSmaller,  groupPanel4);

        String roomNrText = "<b>Nr of rooms: </b>" + offerInfo.get("rooms_nr") + "<br/>";
        String bathroomNrText = "<b>Nr of bathrooms: </b>" + offerInfo.get("bathrooms_nr") + "<br/>";
        String peopleNrText = "<b>For people: </b>" + offerInfo.get("people_nr");

        addJLabel("<html><p style='font-family:verdana'>" + roomNrText + bathroomNrText +
                peopleNrText + "</p></html>", Color.BLACK, fontSmaller,  groupPanel4);
        contentPanelRight.add(Box.createVerticalGlue());

//        JPanel bottomPanel = new JPanel();
//        bottomPanel.setBackground(bgColor);
//        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
//        bottomPanel.setPreferredSize(new Dimension(panelWidth, bottomPanelHeight));
//        bottomPanel.setMaximumSize(new Dimension(panelWidth, bottomPanelHeight));
//
//        try {
//            Image offerImg = ImageIO.read(new File(getClass().getResource(imgPath).getPath()));
////            int currHeight = offerImg.getHeight(); int currWidth = offerImg.getHeight();
////            int goalHeight = bottomPanelHeight; int goalWidth = (currWidth*goalHeight)/currHeight;
////            offerImg = (BufferedImage) offerImg.getScaledInstance(goalWidth, goalHeight, Image.SCALE_SMOOTH);
//            offerImg = offerImg.getScaledInstance(offerImgWidth, bottomPanelHeight, Image.SCALE_SMOOTH);
//            ImageIcon offerImgIcon = new ImageIcon(offerImg);
//            JLabel offerImgLabel = new JLabel();
//            offerImgLabel.setIcon(offerImgIcon);
//            bottomPanel.add(offerImgLabel);
//        } catch (Exception e) {
//            JPanel imgPanel = new JPanel();
//            imgPanel.setBackground(Color.RED);
//            imgPanel.setPreferredSize(new Dimension(offerImgWidth, bottomPanelHeight));
//            imgPanel.setMaximumSize(new Dimension(offerImgWidth, bottomPanelHeight));
//            bottomPanel.add(imgPanel);
//        }
//
//        bottomPanel.add(Box.createRigidArea(new Dimension(10,0)));
//        JPanel detailsPanel = new JPanel();
//        detailsPanel.setBackground(bgColor);
//        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.PAGE_AXIS));
//        detailsPanel.setPreferredSize(new Dimension(panelWidth - offerImgWidth, bottomPanelHeight));
//        detailsPanel.setMaximumSize(new Dimension(panelWidth - offerImgWidth, bottomPanelHeight));
//        populateDetailsPanel(detailsPanel, detailsInfo);
//        bottomPanel.add(detailsPanel);
//        add(bottomPanel);
    }

    void addJLabel(String text, Color color, Font font, JPanel panel) {
        JLabel textLabel = new JLabel(text, JLabel.LEFT);
//        textLabel.setPreferredSize(new Dimension(panelWidth, panelHeight));
//        textLabel.setMaximumSize(new Dimension(panelWidth, panelHeight));
        textLabel.setFont(font);
        textLabel.setForeground(color);
        panel.add(textLabel);
    }

}
