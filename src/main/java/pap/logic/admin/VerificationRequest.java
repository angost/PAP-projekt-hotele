package pap.logic.admin;

import pap.db.entities.Owner;
import pap.db.dao.OwnerDAO;

public class VerificationRequest {
    private Owner owner;

    public VerificationRequest(Owner owner) {
        this.owner = owner;
    }

    public void accept() {
        owner.setVerified(true);
        new OwnerDAO().update(owner);
    }

    /**
     * method allowing admin to verify owner
     * @usage: new VerificationRequest(Owner).accept() / decline()
     * @see Owner
     * @see OwnerDAO
     * @see GetNotVerifiedOwners
     * @info if accepted -> is_verified = true
     * @info if declined -> owner is removed from database
     */
    public void decline() {
        new OwnerDAO().delete(owner);
    }
}
