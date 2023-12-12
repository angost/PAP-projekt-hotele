package pap.logic.login;
import pap.db.dao.OwnerDAO;
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

    public Owner getOwnerAccount(String username, String password) {
        try {
            Owner owner = new OwnerDAO().findByUsername(username);
            if (password.equals(owner.getPassword())) return owner;
            codes.add(302);
            return new Owner();
        } catch (Exception e) {
            codes.add(301);
            return new Owner();
        }
    }

    public List <Integer> getErrorCodes() {
        return codes;
    }
}
