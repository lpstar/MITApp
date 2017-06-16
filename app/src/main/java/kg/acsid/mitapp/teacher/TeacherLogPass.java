package kg.acsid.mitapp.teacher;

/**
 * Created by Админ on 08.06.2017.
 */

public class TeacherLogPass {
    int id;
    String login;
    String pass;

    public TeacherLogPass(int id, String login, String pass) {
        this.id = id;
        this.login = login;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
