package pap.gui;

import pap.gui.components.OwnersOfferPanel;

public class OwnersOfferDetailsGUI extends OfferDetailsGUI{

    @Override
    void undoBtnClickedAction(){
        new OwnerOffersGUI(userId, userType).createGUI();
        frame.setVisible(false);
    }

    public OwnersOfferDetailsGUI(int userId, String userType, int offerId) {
        super(userId, userType, offerId);
    }

    public static void main(String[] args) {
        new OwnersOfferDetailsGUI(-1, "None", 1).createGUI();
    }
}

