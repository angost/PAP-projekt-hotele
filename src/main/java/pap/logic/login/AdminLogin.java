package pap.logic.login;

import pap.db.dao.AdminDAO;
import pap.db.entities.Admin;

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

    public Admin getAdminAccount() {
        try {
            Admin admin = new AdminDAO().findByUsername(username);
            if (password.equals(admin.getPassword())) {
                if (admin.isActive()) {
                    return admin;
                } else {
                    codes.add(503);
                }
            } else {
                codes.add(502);
            }
            return new Admin();
        } catch (Exception e) {
            codes.add(501);
            return new Admin();
        }
    }

    public List <Integer> getErrorCodes() {
        return codes;
    }
}
