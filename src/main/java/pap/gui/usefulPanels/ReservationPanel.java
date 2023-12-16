package pap.gui.usefulPanels;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ReservationPanel extends OfferReservationPanel {

    public ReservationPanel(Color bgColor, Font fontBigger, Font fontMiddle, int offerWidth, int offerHeight,
                     String topPanelText, String imgPath, HashMap<String, String> detailsInfo) {
        super(bgColor, fontBigger, fontMiddle, offerWidth, offerHeight, topPanelText, imgPath, detailsInfo);
    }

    @Override
    void populateDetailsPanel(JPanel detailsPanel, HashMap<String, String> detailsInfo) {
        addJLabel("Date from: " + detailsInfo.get("date_from"), Color.BLACK, fontMiddle, detailsPanel);
        addJLabel("Date to: " + detailsInfo.get("date_to"), Color.BLACK, fontMiddle, detailsPanel);
        addJLabel(detailsInfo.get("description"), Color.BLACK, fontMiddle, detailsPanel);
        addJLabel(detailsInfo.get("paid_amount"), Color.RED, fontMiddle, detailsPanel);
    }
}
