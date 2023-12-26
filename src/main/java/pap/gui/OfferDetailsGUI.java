package pap.gui;

import pap.db.dao.OfferDAO;
import pap.db.entities.Offer;
import pap.gui.components.LogoPanel;
import pap.gui.components.OfferDetailsPanel;
import pap.gui.components.RoundedButtonDefault;
import pap.gui.components.UndoButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class OfferDetailsGUI extends BaseGUI {

    JPanel mainPanel;
    int offerId;

    void createCustomGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        int logoPanelHeight = frameHeight / 10; int footerHeight = frameHeight/10;
        int gap = frameHeight/30; int gap2 = frameHeight/60;
        LogoPanel logoPanel = new LogoPanel(logoColor, frameHeight, frameWidth, logoPanelHeight);
        mainPanel.add(logoPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, gap)));
        HashMap<String, String> offerInfo = getOfferInfo();
        // Should be info passed to this class's constructor - hashmap<String,String>, which will be later passed to payment view
        HashMap<String, String> reservationInfo = new HashMap<>();
        OfferDetailsPanel offerPanel = new OfferDetailsPanel(neutralGray, fontBigger, fontBiggerBold, fontMiddle,
                fontMiddleBold, fontSmaller, fontSmallerBold, frameWidth, frameHeight - logoPanelHeight - footerHeight - gap - gap2*2, offerInfo, reservationInfo);
        mainPanel.add(offerPanel);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.LINE_AXIS));
        footerPanel.setBackground(bgColor);
        footerPanel.setPreferredSize(new Dimension(frameWidth, footerHeight));
        footerPanel.setMaximumSize(new Dimension(frameWidth, footerHeight));

        int undoButtonSize = footerHeight/2;
        footerPanel.add(Box.createRigidArea(new Dimension(undoButtonSize/2, 0)));
        UndoButton undoButton = new UndoButton(undoButtonSize, undoButtonSize, undoButtonSize, undoButtonSize);
        undoButton.addActionListener(e->undoBtnClickedAction());
        footerPanel.add(undoButton);
        footerPanel.add(Box.createHorizontalGlue());

        RoundedButtonDefault reserveButton = new RoundedButtonDefault("Reserve", frameWidth*3/20, frameHeight/10, false, false);
        reserveButton.addActionListener(e-> reserveBtnClickedAction());
        footerPanel.add(reserveButton);
        footerPanel.add(Box.createRigidArea(new Dimension(undoButtonSize/2, 0)));

        mainPanel.add(Box.createRigidArea(new Dimension(0,gap2)));
        mainPanel.add(footerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,gap2)));
    }

    //mock function
    String getImgPath() {
        HashMap<Integer, String> imgPathMap = new HashMap<Integer, String>();
        imgPathMap.put(1, "/room1.jpg"); imgPathMap.put(2, "/room2.jpg");
        imgPathMap.put(3, "/room3.jpg"); imgPathMap.put(4, "/room4.jpg");
        imgPathMap.put(5, "/room5.jpg"); imgPathMap.put(6, "/room6.jpeg");
        imgPathMap.put(7, "/room7.jpg"); imgPathMap.put(8, "/room8.jpg");
        imgPathMap.put(9, "/room9.jpeg"); imgPathMap.put(10, "/room10.jpg");
        return imgPathMap.get(offerId);
    }

    // mock function
    HashMap<String, String> getOfferInfo() {
        HashMap<String, String> offerInfo = new HashMap<String, String>();
        OfferDAO od = new OfferDAO();
        Offer offer = od.findById(offerId);

        offerInfo.put("name", offer.getName());
        offerInfo.put("hotel", offer.getHotel().getName());
        offerInfo.put("price", String.format("%.2f", offer.getPrice()) + " PLN");
        offerInfo.put("description", "Welcome to our exquisite Luxury Suite located in the heart of Wrocław, offering a refined and indulgent experience for the discerning traveler. Immerse yourself in the lap of luxury with our meticulously designed suite that combines sophistication, comfort, and modern convenience.");
        offerInfo.put("img_path", getImgPath());
        offerInfo.put("street", offer.getHotel().getAddress().getStreet());
        offerInfo.put("street_nr", offer.getHotel().getAddress().getStreetNumber());
        offerInfo.put("city", offer.getHotel().getAddress().getCity());
        offerInfo.put("country", offer.getHotel().getAddress().getCountry());
        offerInfo.put("room_type", offer.getRoomType());
        offerInfo.put("rooms_nr", String.valueOf(offer.getRoomNumber()));
        offerInfo.put("bathrooms_nr", String.valueOf(offer.getBathroomNumber()));
        offerInfo.put("people_nr", "4");
        offerInfo.put("review_score", "4.57");
        offerInfo.put("reviews_nr", "30");
        offerInfo.put("facilities_yes", "Free Wi-Fi:  ✔    Air conditioning:  ✔    TV in room:  ✔    Breakfast included:  ✔    24/7 reception:  ✔    Laundry services:  ✔   Airport transfer:  ✔    Room service:  ✔    Meeting/conference rooms:  ✔    Family-friendly:  ✔    Close to city center:  ✔");
        offerInfo.put("facilities_no", "Parking availability:  ✘    Disability access:  ✘    Balcony:  ✘    Pet-friendly:  ✘    Gym access:  ✘    Security features:  ✘        Kitchen:  ✘    Private bathroom:  ✘    Smoking allowed:  ✘    Pool access:  ✘");
        //✘✖
        return offerInfo;
    }

    void undoBtnClickedAction(){
        // przechowywac jakos filtry jakie byly ustawione
        new SearchOffersGUI(userId, userType).createGUI();
        frame.setVisible(false);
    }

    void reserveBtnClickedAction(){
        new PaymentPageGUI(userId, userType, offerId).createGUI();
        frame.setVisible(false);
    }

    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }

    public OfferDetailsGUI(int userId, String userType, int offerId){
        super(userId, userType);
        this.offerId = offerId;
    }

    public static void main(String[] args) {
        new OfferDetailsGUI(-1, "None", 1).createGUI();
    }
}
