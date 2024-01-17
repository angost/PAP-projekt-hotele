package pap.gui;

import pap.db.dao.DiscountDAO;
import pap.db.dao.OfferDAO;
import pap.db.entities.Discount;
import pap.gui.components.*;
import pap.logic.ErrorCodes;
import pap.logic.discountCodes.UpdateDiscountCode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScrollDiscountsGUI extends ScrollGUITemplate {
    public ScrollDiscountsGUI(int userId, String userType){
        super(userId, userType);
        getElementsData();
        offerHeight = frameHeight/4;
        offerWidth = frameWidth/2;
        pageName = "Manage discounts";
    }

    void getElementsData() {
        List<Discount> discounts = new ArrayList<>();
        if (userType.equals("Admin")) {
            discounts = new DiscountDAO().findAll();
        }
        else if (userType.equals("Owner")) {
            // @TODO change to get discounts for owner
            discounts = new DiscountDAO().findAll();
        }
        else {
            System.out.println("Error: invalid user type: " + userType);
        }

        this.nrOfElements = discounts.size();
        this.fittingElementsIds = new Integer[nrOfElements];
        for (int i = 0; i < nrOfElements; i++) {
            fittingElementsIds[i] = discounts.get(i).getDiscountId();
        }
    }

    HashMap<String, String> getElementData(int elementId) {
        Discount discount = new DiscountDAO().findById(elementId);
        HashMap<String, String> elInfo = new HashMap<>();
        elInfo.put("code", discount.getCode());
        elInfo.put("value", String.valueOf(discount.getValue()));
        elInfo.put("value_type", String.valueOf(discount.getValueType()));
        elInfo.put("hotel", discount.getHotel().getName());
        return elInfo;
    }

    JPanel createScrollElement(int elementId) {

        HashMap<String, String> discountInfo = getElementData(elementId);

        JPanel discountPanel = new JPanel();
        discountPanel.setBackground(neutralBlue);
        discountPanel.setLayout(new BoxLayout(discountPanel, BoxLayout.LINE_AXIS));
        discountPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
        discountPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
        discountPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));
        Image image = new OfferDAO().getImageById(elementId);
        fontBigger = new Font(fontBigger.getFontName(), Font.BOLD, fontBigger.getSize());
        DiscountPanel discountInfoPanel = new DiscountPanel(neutralGray, fontBigger, fontMiddle, fontMiddleBold, offerWidth,
                offerHeight, discountInfo.get("code"), discountInfo);

        discountPanel.add(discountInfoPanel);
        discountPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

        return discountPanel;

    }

    void createScrollButtons(int elementId, JPanel offerPanel) {
        ScrollElementButton deactivateDiscountBtn = new ScrollElementButton("Deactivate discount", scrollButtonSize*2, scrollButtonSize, statusWrongLighter, statusWrong, fontButtons, true, elementId);
        deactivateDiscountBtn.addActionListener(actionEvent -> {
            ScrollElementButton button = (ScrollElementButton) actionEvent.getSource();
            String[] options = {"No", "Yes"};
            int pickedOption = JOptionPane.showOptionDialog(null, "This action is irreversible. Are you sure you want to continue?",
                    "Confirm action", 0, 0, null, options, "No");

            if (pickedOption == 1) {
                List<Integer> errorCodes = new ArrayList<>();
                Discount discount = new DiscountDAO().findById(button.elementId);
                if (userType.equals("Admin")) {
                    new UpdateDiscountCode(discount).deactivate();
                } else if (userType.equals("Owner")) {
                    if (userId != discount.getHotel().getOwner().getOwnerId())
                        errorCodes.add(-1);
                    else
                        new UpdateDiscountCode(discount).deactivate();
                } else {
                    System.out.println("Error: invalid user type"+userType);
                    errorCodes.add(-1);
                }

                if (errorCodes.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Code deactivated");
                    new ScrollDiscountsGUI(userId, userType).createGUI();
                    frame.setVisible(false);
                } else {
                    String errorText = "";
                    for (Integer errorCode : errorCodes) {
                        errorText = errorText + ErrorCodes.getErrorDescription(errorCode) + " ";
                    }

                    JOptionPane.showMessageDialog(frame, errorText);
                }
            }
        });
        offerPanel.add(deactivateDiscountBtn);
    }

    public static void main(String[] args) {
        new ScrollDiscountsGUI(1, "Client").createGUI();
    }
}
