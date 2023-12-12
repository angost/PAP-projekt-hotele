package pap.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class ScrollGUITemplate extends BaseGUI{
    JPanel mainPanel, scrollPanel;
    FiltersPanel filtersPanel;
    JScrollPane scrollPanelEnabler;


    // move some new components here

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


        for (int i = 0; i<9; i++) {

            int offerHeight = frameHeight/4;

            JPanel offerPanel = new JPanel();
            offerPanel.setBackground(neutralColor);
            offerPanel.setLayout(new BoxLayout(offerPanel, BoxLayout.LINE_AXIS));
            offerPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
            offerPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
            offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            JPanel offerInfo = new JPanel();
            offerInfo.setBackground(helpingColor);
            offerInfo.setPreferredSize(new Dimension(frameWidth/3, offerHeight));
            offerInfo.setMaximumSize(new Dimension(frameWidth/3, offerHeight));
            offerPanel.add(offerInfo);
            offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            RoundedButton seeOfferBtn = new RoundedButton("See offer "+ i, frameHeight/7, frameHeight/7, secondColor, secondColorDarker, fontButtons, true);
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

    public static void main(String[] args) {
        new ScrollGUITemplate().createGUI();
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