package kg.acsid.mitapp.other;

/**
 * Created by Админ on 12.06.2017.
 */

public class Group {
    public int Id;
    public String Name;

    public int KursId;

    public int KuratorId;
    public  Group(){

    }

    public Group(int id, String name, int kursId, int kuratorId) {
        Id = id;
        Name = name;
        KursId = kursId;
        KuratorId = kuratorId;
    }

    @Override
    public String toString() {
        return  Name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getKursId() {
        return KursId;
    }

    public void setKursId(int kursId) {
        KursId = kursId;
    }

    public int getKuratorId() {
        return KuratorId;
    }

    public void setKuratorId(int kuratorId) {
        KuratorId = kuratorId;
    }
}
