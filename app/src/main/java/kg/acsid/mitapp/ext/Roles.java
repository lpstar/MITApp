package kg.acsid.mitapp.ext;

/**
 * Created by Админ on 08.06.2017.
 */

public class Roles {
    int key;
    String name;

    public Roles(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
