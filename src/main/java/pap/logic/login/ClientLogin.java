package pap.logic.login;
import jakarta.persistence.NoResultException;
import pap.db.dao.AdminDAO;
import pap.db.dao.ClientDAO;
import pap.db.entities.Admin;
import pap.db.entities.Client;
import java.util.*;

public class ClientLogin {
    private final String username;
    private final String password;
    private List <Integer> codes;
    public ClientLogin(String username, String password) {
        this.username = username;
        this.password = password;
        this.codes = new ArrayList<>();
    }

    /**
     * method allowing to log in client account,
     * method checks if such user exists and if given password is correct
     * @usage: new ClientLogin(username, password).getUserAccount()
     * @see Client
     * @see ClientDAO
     * @return returns Client object
     */
    public Client getUserAccount() {
        try {
            Client user = new ClientDAO().findByUsername(username);
            if (password.equals(user.getPassword())) {
                if (user.isActive()) {
                    return user;
                } else {
                    codes.add(403);
                }
            } else {
                codes.add(402);
            }
            return new Client();
        } catch (NoResultException e) {
            codes.add(401);
            return new Client();
        } catch (Exception exception) {
            codes.add(1);
            return new Client();
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
