package pap.logic.login;
import jakarta.persistence.NoResultException;
import pap.db.dao.AdminDAO;
import pap.db.dao.OwnerDAO;
import pap.db.entities.Admin;
import pap.db.entities.Owner;

import java.util.ArrayList;
import java.util.List;


public class OwnerLogin {
    private final String username;
    private final String password;
    private List<Integer> codes;

    public OwnerLogin(String username, String password) {
        this.username = username;
        this.password = password;
        this.codes = new ArrayList<>();
    }

    /**
     * method allowing to log in owner account,
     * method checks if such user exists and if given password is correct
     * @usage: new OwnerLogin(username, password).getOwnerAccount()
     * @see Owner
     * @see OwnerDAO
     * @return returns Admin object
     */
    public Owner getOwnerAccount() {
        try {
            Owner owner = new OwnerDAO().findByUsername(username);
            if (password.equals(owner.getPassword())) {
                if (owner.isActive()) {
                    return owner;
                } else {
                    codes.add(406);
                }
            } else {
                codes.add(405);
            }
            return new Owner();
        } catch (NoResultException e) {
            codes.add(404);
            return new Owner();
        } catch (Exception exception) {
            codes.add(1);
            return new Owner();
        }
    }

    /**
     * method allows to get error codes to check if login process was completed correctly
     * @return returns list of Integer (codes), if the list is empty, login was successful
     * @see pap.logic.ErrorCodes
     */
    public List <Integer> getErrorCodes() {
        return codes;
    }
}
