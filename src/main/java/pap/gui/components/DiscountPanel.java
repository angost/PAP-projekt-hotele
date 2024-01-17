package pap.gui.components;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class DiscountPanel extends JPanel {
    int discountWidth, discountHeight;
    Color bgColor; Font fontBigger, fontMiddle, fontMiddleBold;

    void addJLabel(String text, Color color, Font font, JPanel panel) {
        JLabel textLabel = new JLabel(text, JLabel.LEFT);
        textLabel.setPreferredSize(new Dimension(discountWidth, discountHeight));
        textLabel.setMaximumSize(new Dimension(discountWidth, discountHeight));
        textLabel.setFont(font);
        textLabel.setForeground(color);
        panel.add(textLabel);
    }

    public DiscountPanel(Color bgColor, Font fontBigger, Font fontMiddle, Font fontMiddleBold, int width, int height,
                                 String topPanelText, HashMap<String, String> detailsInfo) {
        this.discountWidth = width; this.discountHeight = height;
        this.bgColor = bgColor; this.fontBigger = fontBigger; this.fontMiddle = fontMiddle; this.fontMiddleBold = fontMiddleBold;
        int bottomPanelHeight = height*3/4;

        setBackground(bgColor);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(bgColor);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.setPreferredSize(new Dimension(width, height - bottomPanelHeight));
        topPanel.setMaximumSize(new Dimension(width, height - bottomPanelHeight));
        topPanel.add(Box.createRigidArea(new Dimension(10,0)));
        JLabel topPanelTextLabel = new JLabel(topPanelText, JLabel.LEFT);
        topPanelTextLabel.setFont(fontBigger);
        topPanelTextLabel.setPreferredSize(new Dimension(width, height - bottomPanelHeight-10));
        topPanelTextLabel.setMaximumSize(new Dimension(width, height - bottomPanelHeight-10));
        topPanel.add(topPanelTextLabel);
        add(topPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(bgColor);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.setPreferredSize(new Dimension(width, bottomPanelHeight));
        bottomPanel.setMaximumSize(new Dimension(width, bottomPanelHeight));

        bottomPanel.add(Box.createRigidArea(new Dimension(10,0)));
        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(bgColor);
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.PAGE_AXIS));
        detailsPanel.setPreferredSize(new Dimension(width, bottomPanelHeight));
        detailsPanel.setMaximumSize(new Dimension(width, bottomPanelHeight));
        String value_type = detailsInfo.get("value_type").equals("1") ? "%" : " PLN";
        addJLabel("Code: " + detailsInfo.get("code"), Color.BLACK, fontMiddle, detailsPanel);
        addJLabel("Value: " + detailsInfo.get("value") + value_type, Color.BLACK, fontMiddle, detailsPanel);
        addJLabel("Hotel: " + detailsInfo.get("hotel"), Color.BLACK, fontMiddle, detailsPanel);
        bottomPanel.add(detailsPanel);
        add(bottomPanel);
    }
}
