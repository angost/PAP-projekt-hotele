package pap.logic.login;
import pap.db.dao.ClientDAO;
import pap.db.entities.Client;
import java.util.*;

public class UserLogin {
    private final String username;
    private final String password;
    private List <Integer> codes;
    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
        this.codes = new ArrayList<>();
    }

    public Client getUserAccount() {
        try {
            Client user = new ClientDAO().findByUsername(username);
            if (password.equals(user.getPassword())) {
                if (user.isActive()) {
                    return user;
                } else {
                    codes.add(203);
                }
            } else {
                codes.add(202);
            }
            return new Client();
        } catch (Exception e) {
            codes.add(201);
            return new Client();
        }
    }

    public List <Integer> getErrorCodes() {
        return codes;
    }
}
