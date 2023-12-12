package pap.logic.guiAction;

import pap.db.entities.Offer;
import pap.logic.SearchOffers;

import java.util.HashMap;
import java.util.List;

public class FindDisplayOffers {

    public final List<Offer> offers;

    public FindDisplayOffers(){
        offers = filterOffers();
    }

    public Integer[] getFittingElementsIds() {
        int offersCount = offers.size();
        Integer[] ids = new Integer[offersCount];
        for (int i = 1; i <= offersCount; i++) {
            ids[i-1] = i-1;
        }
        return ids;
    }

    public List<Offer> filterOffers(){
        //TODO: filters
        return SearchOffers.getAllOffers();
    }

    public HashMap<String, String> getElementInfo(int id){
        HashMap<String, String> elInfo = new HashMap<String, String>();

        Offer offer = offers.get(id);
        elInfo.put("name", offer.getName());
        elInfo.put("info", "room type: %s, rooms: %d, bathrooms: %d"
                .formatted(offer.getRoomType(), offer.getRoomNumber(), offer.getBathroomNumber()));
        elInfo.put("price", "" + offer.getPrice());

        return elInfo;
    }
}
