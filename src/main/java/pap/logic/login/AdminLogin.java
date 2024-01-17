package pap.logic.login;

import jakarta.persistence.NoResultException;
import pap.db.dao.AdminDAO;
import pap.db.entities.Admin;
import pap.logic.add.AddNewOwner;

import java.util.ArrayList;
import java.util.List;

public class AdminLogin {
    private final String username;
    private final String password;
    private List<Integer> codes;

    public AdminLogin(String username, String password) {
        this.username = username;
        this.password = password;
        this.codes = new ArrayList<>();
    }

    /**
     * method allowing to log in admin account,
     * method checks if such user exists and if given password is correct
     * @usage: new AdminLogin(username, password).getAdminAccount()
     * @see Admin
     * @see AdminDAO
     * @return returns Admin object
     */
    public Admin getAdminAccount() {
        try {
            Admin admin = new AdminDAO().findByUsername(username);
            if (password.equals(admin.getPassword())) {
                if (admin.isActive()) {
                    return admin;
                } else {
                    codes.add(409);
                }
            } else {
                codes.add(408);
            }
            return new Admin();
        } catch (NoResultException e) {
            codes.add(407);
            return new Admin();
        } catch (Exception exception) {
            codes.add(1);
            return new Admin();
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
