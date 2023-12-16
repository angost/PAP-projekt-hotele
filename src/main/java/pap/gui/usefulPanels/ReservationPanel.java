package pap.gui.usefulPanels;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ReservationPanel extends OfferReservationPanel {

    public ReservationPanel(Color bgColor, Font fontBigger, Font fontMiddle, Font fontMiddleBold, int offerWidth, int offerHeight,
                     String topPanelText, String imgPath, HashMap<String, String> detailsInfo) {
        super(bgColor, fontBigger, fontMiddle, fontMiddleBold, offerWidth, offerHeight, topPanelText, imgPath, detailsInfo);
    }

    @Override
    void populateDetailsPanel(JPanel detailsPanel, HashMap<String, String> detailsInfo) {
        addJLabel(detailsInfo.get("name"), Color.BLACK, fontMiddle, detailsPanel);
        addJLabel(detailsInfo.get("people") + " people", Color.BLACK, fontMiddle, detailsPanel);
        addJLabel(detailsInfo.get("paid_amount"), Color.BLACK, fontMiddleBold, detailsPanel);
        Color statusColor; String statusText = detailsInfo.get("status");
        if (statusText.equals("Cancelled")) {
            statusColor = Color.RED;
        } else if (statusText.equals("Active")) {
            statusColor = Color.ORANGE;
        } else if (statusText.equals("Finished")) {
            statusColor = Color.GREEN;
        } else {
            statusColor = Color.BLACK;
        }
        addJLabel(statusText, statusColor, fontMiddleBold, detailsPanel);

    }
}
