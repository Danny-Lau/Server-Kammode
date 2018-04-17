package server.models;

public class Bruger {
    String  userName, mail, password ;
    int type, brugerId;
    Long timeCreated;


    public Bruger(int brugerId, String userName, String mail, String password, Long timeCreated, int type) {
        this.brugerId = brugerId;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.type = type;
        this.timeCreated = timeCreated;
    }

    public Bruger() {
    }

    public Long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getBrugerId() {
        return brugerId;
    }

    public void setBrugerId(int brugerId) {
        this.brugerId = brugerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
