package pap.gui.components;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class OfferPanel extends OfferReservationPanel {

    public OfferPanel(Color bgColor, Font fontBigger, Font fontMiddle, Font fontMiddleBold, int offerWidth, int offerHeight,
                      String topPanelText, String imgPath, HashMap<String, String> detailsInfo) {
        super(bgColor, fontBigger, fontMiddle, fontMiddleBold, offerWidth, offerHeight, topPanelText, imgPath, detailsInfo);
    }

    @Override
    void populateDetailsPanel(JPanel detailsPanel, HashMap<String, String> detailsInfo) {
        addJLabel("Room type: " + detailsInfo.get("room_type"), Color.BLACK, fontMiddle, detailsPanel);
        addJLabel("Rooms: " + detailsInfo.get("rooms"), Color.BLACK, fontMiddle, detailsPanel);
        addJLabel("Bathrooms: " + detailsInfo.get("bathrooms"), Color.BLACK, fontMiddle, detailsPanel);
        addJLabel(detailsInfo.get("price"), Color.decode("#9E2A2B"), fontMiddleBold, detailsPanel);
    }
}
