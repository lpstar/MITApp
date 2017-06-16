package kg.acsid.mitapp.other;

/**
 * Created by Админ on 16.06.2017.
 */

public class Kafedra {
    public int Id;
    public String Name;
    public String JetekchiId;
    public String PlaceRoomId;

    public Kafedra() {
    }

    public Kafedra(int id, String name, String jetekchiId, String placeRoomId) {
        Id = id;
        Name = name;
        JetekchiId = jetekchiId;
        PlaceRoomId = placeRoomId;
    }

    @Override
    public String toString() {
        return Name;
    }
}

