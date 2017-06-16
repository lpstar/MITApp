package kg.acsid.mitapp.teacher;

import java.util.Date;

/**
 * Created by Админ on 08.06.2017.
 */

public class Teacher {
    public int ID;
    public String fName;
    public String name;
    public String lName;
    public String phone;
    public String address;
    public String mail;
    boolean isKurator;
    Date burnDate;
    boolean isMale;
    int kafedraId;
    int doljnostId;

    public static String nameTag = "name";
    public static String idTag = "ID";
    public static String fnameTag = "fname";
    public static String lnameTag = "lname";
    public static String phoneTag = "phone";
    public static String addressTag = "address";
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isKurator() {
        return isKurator;
    }

    public void setKurator(boolean kurator) {
        isKurator = kurator;
    }

    public Date getBurnDate() {
        return burnDate;
    }

    public void setBurnDate(Date burnDate) {
        this.burnDate = burnDate;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public int getKafedraId() {
        return kafedraId;
    }

    public void setKafedraId(int kafedraId) {
        this.kafedraId = kafedraId;
    }

    public int getDoljnostId() {
        return doljnostId;
    }

    public void setDoljnostId(int doljnostId) {
        this.doljnostId = doljnostId;
    }
}
