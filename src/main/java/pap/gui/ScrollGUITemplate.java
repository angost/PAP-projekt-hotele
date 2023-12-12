package pap.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import pap.logic.SearchOffers;
import pap.db.entities.Offer;
import java.util.List;

class FindDisplayOffers {

    public Integer[] getfittingElementsIds() {
        Integer[] ids = {0,1,2};
        return ids;
    }

    public List <Offer> filterOffers(){
        List <Offer> allOffers = SearchOffers.getAllOffers();
        //TODO: filters
        return allOffers;
    }

    public HashMap<String, String> getElementInfo(int id){
        HashMap<String, String> elInfo = new HashMap<String, String>();

        List <Offer> offers = filterOffers();
        //TODO: Można potem dodać 3 kolejne id+page*3 jeśli przechodzi sie na kolejne strony wyszukiwań
        Offer offer = offers.get(id);
        elInfo.put("name", offer.getName());
        elInfo.put("info", "room type: %s, rooms: %d, bathrooms: %d"
                .formatted(offer.getRoomType(), offer.getRoomNumber(), offer.getBathroomNumber()));
        elInfo.put("price", "" + offer.getPrice());

        return elInfo;
    }
}

public class ScrollGUITemplate extends BaseGUI{
    JPanel mainPanel, scrollPanel;
    FiltersPanel filtersPanel;
    JScrollPane scrollPanelEnabler;
    // move some new components here

    int nrOfElements;
    Integer[] fittingElementsIds;

    void createCustomGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        int logoPanelHeight = frameHeight/10;
        int filtersPanelHeight = frameHeight/15;
        int scrollPanelEnablerHeight = frameHeight - logoPanelHeight - filtersPanelHeight;

        LogoPanel logoPanel = new LogoPanel(logoColor, frameHeight, frameWidth, logoPanelHeight);
        mainPanel.add(logoPanel);

        scrollPanel = new JPanel();
        scrollPanel.setBackground(bgColor);
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
        scrollPanelEnabler = new JScrollPane(scrollPanel);
        scrollPanelEnabler.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelEnabler.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanelEnabler.setPreferredSize(new Dimension(frameWidth, scrollPanelEnablerHeight));
        scrollPanelEnabler.setMaximumSize(new Dimension(frameWidth, scrollPanelEnablerHeight));

        filtersPanel = new FiltersPanel(helpingColor, frameWidth, frameHeight, frameWidth, filtersPanelHeight, scrollPanelEnabler, scrollPanelEnablerHeight, frame);
        mainPanel.add(filtersPanel);
        mainPanel.add(scrollPanelEnabler);

        int offerHeight = frameHeight/4;
        int offerWidth = frameWidth/3;

        for (int i = 0; i < nrOfElements; i++) {

            JPanel offerPanel = new JPanel();
            offerPanel.setBackground(neutralColor);
            offerPanel.setLayout(new BoxLayout(offerPanel, BoxLayout.LINE_AXIS));
            offerPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
            offerPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
            offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            JPanel offerInfoPanel = new JPanel();
            offerInfoPanel.setBackground(helpingColor);
            offerInfoPanel.setLayout(new BoxLayout(offerInfoPanel, BoxLayout.PAGE_AXIS));
            offerInfoPanel.setPreferredSize(new Dimension(offerWidth, offerHeight));
            offerInfoPanel.setMaximumSize(new Dimension(offerWidth, offerHeight));

            HashMap<String, String> offerInfo = new FindDisplayOffers().getElementInfo(fittingElementsIds[i]);
            JLabel offerNameLabel = new JLabel(offerInfo.get("name"), JLabel.CENTER);
            offerNameLabel.setPreferredSize(new Dimension(offerWidth, offerHeight));
            offerNameLabel.setMaximumSize(new Dimension(offerWidth, offerHeight));
            offerNameLabel.setFont(fontMiddle);
            offerInfoPanel.add(offerNameLabel);

            JLabel offerInfoLabel = new JLabel(offerInfo.get("info"), JLabel.CENTER);
            offerInfoLabel.setPreferredSize(new Dimension(offerWidth, offerHeight));
            offerInfoLabel.setMaximumSize(new Dimension(offerWidth, offerHeight));
            offerInfoLabel.setFont(fontSmaller);
            offerInfoPanel.add(offerInfoLabel);

            JLabel offerPriceLabel = new JLabel(offerInfo.get("price"), JLabel.CENTER);
            offerPriceLabel.setPreferredSize(new Dimension(offerWidth, offerHeight));
            offerPriceLabel.setMaximumSize(new Dimension(offerWidth, offerHeight));
            offerPriceLabel.setFont(fontMiddle);
            offerPriceLabel.setForeground(Color.RED);
            offerInfoPanel.add(offerPriceLabel);

            offerPanel.add(offerInfoPanel);
            offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            SeeOfferButton seeOfferBtn = new SeeOfferButton("See offer", frameHeight/7, frameHeight/7,secondColor, secondColorDarker, fontButtons, true, fittingElementsIds[i]);
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    SeeOfferButton button = (SeeOfferButton)actionEvent.getSource();
                    System.out.println(button.offerId);
                }
            };
            seeOfferBtn.addActionListener(actionListener);
            offerPanel.add(seeOfferBtn);

            scrollPanel.add(offerPanel);
            scrollPanel.add(Box.createRigidArea(new Dimension(0,30)));

        }
    }


    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }

    public ScrollGUITemplate(){
        fittingElementsIds = new FindDisplayOffers().getfittingElementsIds();
        nrOfElements = fittingElementsIds.length;
    }

    public static void main(String[] args) {
        new ScrollGUITemplate().createGUI();
    }

}

class SeeOfferButton extends RoundedButton {
    int offerId;

    public SeeOfferButton(String text, int preferredWidth, int preferredHeight, Color fillColor, Color hoverColor, Font font, boolean squareShaped, int offerId) {
        super(text, preferredWidth, preferredHeight, fillColor, hoverColor, font, squareShaped);
        this.offerId = offerId;
    }
}

class FiltersPanel extends JPanel {

    TwoImgsButton ShowHideFilterButton;
    JScrollPane otherPanel;
    JFrame frame;
    int frameHeight, frameWidth, otherPanelHeight, panelWidth, panelHeight;

    public FiltersPanel(Color panelColor, int frameWidth, int frameHeight, int panelWidth, int panelHeight, JScrollPane otherPanel, int otherPanelHeight, JFrame frame) {

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBackground(panelColor);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setMaximumSize(new Dimension(panelWidth, panelHeight));

        this.add(Box.createRigidArea(new Dimension(frameHeight/20,0)));

        ShowHideFilterButton = new TwoImgsButton(panelHeight, panelHeight, panelHeight*2, panelHeight, "/show_more_128.png", "/show_less_128.png");
        ShowHideFilterButton.addActionListener(e->showHideFiltersClicked());
        this.add(ShowHideFilterButton);

        this.otherPanel = otherPanel;
        this.frameHeight = frameHeight; this.frameWidth = frameWidth;
        this.otherPanelHeight = otherPanelHeight;
        this.panelHeight = panelHeight; this.panelWidth = panelWidth;
        this.frame = frame;

    }

    void showHideFiltersClicked() {
        // Showing filters
        if (ShowHideFilterButton.state.equals("base_state")) {
            ShowHideFilterButton.changeState();
            changePanelSizes(panelWidth, panelHeight + 100, frameWidth, otherPanelHeight - 100);
        } else {
            // Hiding filters
            ShowHideFilterButton.changeState();
            changePanelSizes(panelWidth, panelHeight, frameWidth, otherPanelHeight);
        }
    }

    void changePanelSizes(int filterPanelWidth, int filterPanelHeight, int otherPanelWidth, int otherPanelHeight) {
        this.setPreferredSize(new Dimension(filterPanelWidth, filterPanelHeight));
        this.setMaximumSize(new Dimension(filterPanelWidth, filterPanelHeight));
        otherPanel.setPreferredSize(new Dimension(otherPanelWidth, otherPanelHeight));
        otherPanel.setMaximumSize(new Dimension(otherPanelWidth, otherPanelHeight));
        frame.revalidate();
        frame.repaint();
        this.revalidate();
        this.repaint();
    }

}

class TwoImgsButton extends JButton{

    Image baseImg, secondImg;
    String state;
    boolean imgUploadSuccess = false;

    public TwoImgsButton(int buttonWidth, int buttonHeight, int imgWidth, int imgHeight, String baseImgPath, String secondImgPath) {
        setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        try {
            setContentAreaFilled(false); // Make the button transparent
            Image baseImg = ImageIO.read(new File(getClass().getResource(baseImgPath).getPath()));
            baseImg = baseImg.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            Image secondImg = ImageIO.read(new File(getClass().getResource(secondImgPath).getPath()));
            secondImg = secondImg.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(baseImg));
            this.baseImg = baseImg;
            this.secondImg = secondImg;
            imgUploadSuccess = true;
            state = "base_state";
        } catch (Exception ex) {
            setContentAreaFilled(true);
        }
    }

    public void changeState() {
        if (state.equals("base_state")) {
            setIcon(new ImageIcon(secondImg));
            state = "second_state";
        } else {
            setIcon(new ImageIcon(baseImg));
            state = "base_state";
        }
    }
}