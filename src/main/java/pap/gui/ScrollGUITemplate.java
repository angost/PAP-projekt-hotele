package pap.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public abstract class ScrollGUITemplate extends BaseGUI{
    JPanel mainPanel, scrollPanel;
    FiltersPanel filtersPanel;
    JScrollPane scrollPanelEnabler;
    // move some new components here
    // TODO: customizowalny filters panel (ratingi - po dacie/po ocenie; historia - po dacie; ...)
    // TODO: wgle filters panel i elementy w nim

    int nrOfElements;
    Integer[] fittingElementsIds;
    int offerHeight = frameHeight/4;
    int offerWidth = frameWidth/3;
    String pageName = "";

    abstract void getElementsData();
    abstract HashMap<String, String> getElementData(int elementId);
    abstract JPanel createScrollElement(int elementId);
    abstract void createScrollButtons(int elementId, JPanel elementPanel);

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
        scrollPanelEnabler.getVerticalScrollBar().setUnitIncrement(10);

        filtersPanel = new FiltersPanel(neutralGray, frameWidth, frameHeight, frameWidth, filtersPanelHeight, scrollPanelEnabler, scrollPanelEnablerHeight, frame);
        mainPanel.add(filtersPanel);
        mainPanel.add(scrollPanelEnabler);

        for (int i = 0; i < nrOfElements; i++) {
            JPanel elementPanel = createScrollElement(fittingElementsIds[i]);
            createScrollButtons(fittingElementsIds[i], elementPanel);

            scrollPanel.add(elementPanel);
            scrollPanel.add(Box.createRigidArea(new Dimension(0,30)));
        }
        UndoPanel undoPanel = new UndoPanel(frameWidth, frameHeight/20, bgColor, e->undoBtnClickedAction(), pageName, fontMiddle);
        mainPanel.add(undoPanel);
    }

    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }

    void undoBtnClickedAction(){
        new HomePageGUI(userId, userType).createGUI();
        frame.setVisible(false);
    }

    public ScrollGUITemplate(int userId, String userType){
        super(userId, userType);
    }
}


class ScrollElementButton extends RoundedButton {
    int elementId;

    public ScrollElementButton(String text, int preferredWidth, int preferredHeight, Color fillColor, Color hoverColor, Font font, boolean squareShaped, int elementId) {
        super("<html><b>"+text+"</b></html>", preferredWidth, preferredHeight, fillColor, hoverColor, font, squareShaped);
        this.elementId = elementId;
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
