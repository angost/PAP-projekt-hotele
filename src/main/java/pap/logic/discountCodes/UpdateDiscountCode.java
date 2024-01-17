package pap.logic.discountCodes;

import pap.db.dao.DiscountDAO;
import pap.db.entities.Discount;

public class UpdateDiscountCode {
    private final Discount discountCode;
    public UpdateDiscountCode(Discount discount) {
        this.discountCode = discount;
    }

    /**
     * method allowing deactivating discount code,
     * sets is_active = false for given code in discounts table
     * @usage: new UpdateDiscountCode(Discount).deactivate()
     * @see Discount
     * @see pap.db.dao.DiscountDAO
     */
    public void deactivate() {
        discountCode.setActive(false);
        new DiscountDAO().update(discountCode);
    }

    /**
     * method allowing activating discount code,
     * sets is_active = true for given code in discounts table
     * @usage: new UpdateDiscountCode(Discount).deactivate()
     * @see Discount
     * @see pap.db.dao.DiscountDAO
     */
    public void activate() {
        discountCode.setActive(true);
        new DiscountDAO().update(discountCode);
    }
}
