package pap.logic.discountCodes;

import pap.db.dao.DiscountDAO;
import pap.db.dao.HotelDAO;
import pap.db.entities.Discount;
import pap.db.entities.Hotel;

public class AddNewDiscountCode {
    private final Discount discount;

    public AddNewDiscountCode(String code, Integer valueType, String description,
                              Float value, Hotel hotel, boolean isActive) {
        this.discount = new Discount();
        this.discount.setCode(code);
        this.discount.setValueType(valueType);
        this.discount.setType(0);
        this.discount.setDescription(description);
        this.discount.setValue(value);
        this.discount.setHotel(hotel);
        this.discount.setActive(isActive);
    }

    /**
     * method adds discount code for given hotel,
     * inserts row into discounts
     * @usage: new AddNewDiscountCode(...discount code params).insertIntoDatabase()
     * @see Discount
     * @see pap.db.dao.DiscountDAO
     * @see pap.logic.validators.DiscountCodeValidator
     * @info (recommended validating new code before inserting into database)
     * @info useful in owner panel
     */
    public void insertIntoDatabase() {
        new HotelDAO().update(discount.getHotel());
        new DiscountDAO().create(discount);
    }
}
