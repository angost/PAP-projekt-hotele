package pap.gui.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class OfferDetailsPanel extends JPanel {

    int panelWidth, panelHeight;
    Color bgColor; Font fontBigger, fontMiddle, fontMiddleBold, fontSmaller, fontSmallerBold;
    RoundedButtonDefault seeHotelButton, seeReviewsButton;

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
        contentPanelLeft.add(Box.createRigidArea(new Dimension(0, contentPanelsHeight/30)));
        addJLabel("<html><p style='font-size:10px; font-family:verdana;'>" + offerInfo.get("facilities_yes") + "</p></html>", Color.BLACK, fontSmaller, contentPanelLeft, -1, -1);
        contentPanelLeft.add(Box.createRigidArea(new Dimension(0, contentPanelsHeight/30)));
        addJLabel("<html><p style='font-size:10px; font-family:verdana;'>" + offerInfo.get("facilities_no") + "</p></html>", Color.BLACK, fontSmaller, contentPanelLeft,-1, -1);
//        addJLabel("<html><p style='font-family:verdana;'>" + offerInfo.get("facilities_no") + "</p></html>", Color.BLACK, fontSmaller, contentPanelLeft);
//        addJLabel("<html><p style='font-family:verdana;'><font size=-0.5>" + offerInfo.get("facilities_no") + "</p></html>", Color.BLACK, fontSmaller, contentPanelLeft);

        // RIGHT PANEL
        int rightPanelGap = 40;
        JPanel groupPanel3 = new JPanel();
        groupPanel3.setBackground(bgColor);
        groupPanel3.setLayout(new BoxLayout(groupPanel3, BoxLayout.LINE_AXIS));
        groupPanel3.add(Box.createRigidArea(new Dimension(rightPanelGap, 0)));
        contentPanelRight.add(groupPanel3);
        addJLabel("<html><p style='font-family:verdana'>" + "<b>Description:</b><br/>" + offerInfo.get("description") + "</p></html>", Color.BLACK, fontSmaller, groupPanel3, -1, -1);
        //        contentPanelRight.add(Box.createVerticalGlue());

        contentPanelRight.add(Box.createRigidArea(new Dimension(0, contentPanelsHeight/30)));

        JPanel  groupPanel4 = new JPanel();
        groupPanel4.setBackground(bgColor);
        groupPanel4.setLayout(new BoxLayout( groupPanel4, BoxLayout.LINE_AXIS));
        groupPanel4.add(Box.createRigidArea(new Dimension(rightPanelGap, 0)));
        contentPanelRight.add(groupPanel4);

        String locationTitle = "<b>Location:</b><br/>";
        String locationData = offerInfo.get("street") + " " + offerInfo.get("street_nr") + ",<br/>" + offerInfo.get("city") + ", " + offerInfo.get("country");
        addJLabel("<html><p style='font-family:verdana'>" + locationTitle + locationData + "</p></html>", Color.BLACK, fontSmaller,  groupPanel4, -1, -1);

        String roomTypeText = "<b>Room type:</b><br/>" + offerInfo.get("room_type") + "<br/>";
        String priceText = "<b>Price per night:</b><br/>" + "<b style='color:#9e2a2b'>" + offerInfo.get("price")+ "</b><br/>";
        addJLabel("<html><p style='font-family:verdana'>" + roomTypeText +
                priceText + "</p></html>", Color.BLACK, fontSmaller,  groupPanel4, -1, -1);

        String roomNrText = "<b>Nr of rooms: </b>" + offerInfo.get("rooms_nr") + "<br/>";
        String bathroomNrText = "<b>Nr of bathrooms: </b>" + offerInfo.get("bathrooms_nr") + "<br/>";
        String peopleNrText = "<b>For people: </b>" + offerInfo.get("people_nr");

        addJLabel("<html><p style='font-family:verdana'>" + roomNrText + bathroomNrText +
                peopleNrText + "</p></html>", Color.BLACK, fontSmaller,  groupPanel4, -1, -1);
//        contentPanelRight.add(Box.createVerticalGlue());

        int reviewsPanelHeight = topPanelHeight/2; int reviewsPanelWidth = panelWidth - leftPanelWidth;
        JPanel  groupPanel5 = new JPanel();
        groupPanel5.setBackground(bgColor);
        groupPanel5.setLayout(new BoxLayout( groupPanel5, BoxLayout.LINE_AXIS));
        groupPanel5.setPreferredSize(new Dimension(panelWidth - leftPanelWidth, reviewsPanelHeight));
        groupPanel5.setMaximumSize(new Dimension(panelWidth - leftPanelWidth, reviewsPanelHeight));
        contentPanelRight.add(Box.createRigidArea(new Dimension(0, contentPanelsHeight/30)));
        contentPanelRight.add(groupPanel5);

        groupPanel5.add(Box.createRigidArea(new Dimension(rightPanelGap, 0)));
        int reviewCount = Integer.parseInt(offerInfo.get("reviews_nr"));
        float reviewScore = Float.parseFloat(offerInfo.get("review_score"));
        int reviewStars = (int) reviewScore;
        addJLabel("<html><p style='font-family:verdana'>" + "<b>Reviews:</b>" + "</p></html>", Color.BLACK, fontSmaller,  groupPanel5, reviewsPanelWidth/7, reviewsPanelHeight);

        try {
            Image reviewScoreImage = ImageIO.read(new File(getClass().getResource("/" + reviewStars + "-star.png").getPath()));
            reviewScoreImage = reviewScoreImage.getScaledInstance(panelWidth/10, reviewsPanelHeight/2, Image.SCALE_SMOOTH);
            ImageIcon reviewScoreIcon = new ImageIcon(reviewScoreImage);
            JLabel reviewScoreLabel = new JLabel();
            reviewScoreLabel.setIcon(reviewScoreIcon);
            groupPanel5.add(reviewScoreLabel);
        } catch (Exception e) {
            addJLabel("<html><p style='font-family:verdana'>" + "★".repeat(reviewStars) + "☆".repeat(5-reviewStars) + "</p></html>", Color.BLACK, fontSmaller,  groupPanel5, reviewsPanelWidth/9, reviewsPanelHeight);
        }
        groupPanel5.add(Box.createRigidArea(new Dimension(contentPanelsHeight/30, 0)));
        addJLabel("<html><p style='font-family:verdana'>" + reviewScore + " (" + reviewCount + ")" + "</p></html>", Color.BLACK, fontSmaller, groupPanel5, reviewsPanelWidth/7, reviewsPanelHeight);
        seeReviewsButton = new RoundedButtonDefault("See all reviews", panelWidth/9, topPanelHeight/2, false, true);
        groupPanel5.add(seeReviewsButton);
        groupPanel5.add(Box.createRigidArea(new Dimension(rightPanelGap, 0)));

        JPanel  groupPanel6 = new JPanel();
        groupPanel6.setBackground(bgColor);
        groupPanel6.setLayout(new BoxLayout( groupPanel6, BoxLayout.LINE_AXIS));
        groupPanel6.setPreferredSize(new Dimension(reviewsPanelWidth, contentPanelsHeight/3));
        groupPanel6.setMaximumSize(new Dimension(reviewsPanelWidth, contentPanelsHeight/3));
        contentPanelRight.add(Box.createRigidArea(new Dimension(0, contentPanelsHeight/30)));
        contentPanelRight.add(groupPanel6);
        contentPanelRight.add(Box.createRigidArea(new Dimension(0, contentPanelsHeight/30)));

        groupPanel6.add(Box.createRigidArea(new Dimension(rightPanelGap, 0)));
        JPanel offerReviews = new JPanel();
        offerReviews.setBackground(Color.decode("#e3e3e3"));
        offerReviews.setLayout(new BoxLayout(offerReviews, BoxLayout.LINE_AXIS));
        offerReviews.setPreferredSize(new Dimension(reviewsPanelWidth - rightPanelGap*2, contentPanelsHeight/3));
        offerReviews.setMaximumSize(new Dimension(reviewsPanelWidth - rightPanelGap*2, contentPanelsHeight/3));
        groupPanel6.add(offerReviews);
        groupPanel6.add(Box.createRigidArea(new Dimension(rightPanelGap, 0)));

    }

    void addJLabel(String text, Color color, Font font, JPanel panel, int panelWidth, int panelHeight) {
        JLabel textLabel = new JLabel(text, JLabel.LEFT);
        textLabel.setFont(font);
        textLabel.setForeground(color);
        if (panelWidth != -1 && panelHeight != -1){
            textLabel.setPreferredSize(new Dimension(panelWidth, panelHeight));
            textLabel.setMaximumSize(new Dimension(panelWidth, panelHeight));
        }
        panel.add(textLabel);
    }

}
