package kg.acsid.mitapp.student;

/**
 * Created by Админ on 08.06.2017.
 */

public class Student {
    private int Id;
    private String FName;
    private String Name;
    private  String LName;
    private  int GruppaId;
    private  String Phone;
    private String Mail;
    private boolean IsMale;
    private boolean IsStarosta;
    private String Address;

    public Student() {
    }

    public Student(int id, String FName, String name, String LName, int gruppaId, String phone, String mail, boolean isMale, boolean isStarosta, String address) {
        Id = id;
        this.FName = FName;
        Name = name;
        this.LName = LName;
        GruppaId = gruppaId;
        Phone = phone;
        Mail = mail;
        IsMale = isMale;
        IsStarosta = isStarosta;
        Address = address;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public int getGruppaId() {
        return GruppaId;
    }

    public void setGruppaId(int gruppaId) {
        GruppaId = gruppaId;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public boolean isMale() {
        return IsMale;
    }

    public void setMale(boolean male) {
        IsMale = male;
    }

    public boolean isStarosta() {
        return IsStarosta;
    }

    public void setStarosta(boolean starosta) {
        IsStarosta = starosta;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
