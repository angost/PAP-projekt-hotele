package pap.gui;

import pap.gui.components.FiltersPanel;
import pap.gui.components.LogoPanel;
import pap.gui.components.UndoPanel;

import javax.swing.*;
import java.awt.*;
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
    int scrollButtonSize = frameHeight/7;
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
