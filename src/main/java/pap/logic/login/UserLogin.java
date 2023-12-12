package pap.logic.login;
import pap.db.dao.ClientDAO;
import pap.db.entities.Client;

public class UserLogin {
    public static Client getUserAccount(String username, String password) {
        try {
            Client user = new ClientDAO().findByUsername(username);
            if (password.equals(user.getPassword())) return user;
            return new Client();
        } catch (Exception e) {
            return new Client();
        }
    }
}
