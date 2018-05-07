package server.models;

public class User {

    private int brugerId;
    private String username;
    private String mail;
    private String password;
    private int type;
    private long timeCreated;

    public User(String username, String mail, String password, int type, long timeCreated) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.type = type;
        this.timeCreated = timeCreated;
    }

    public User(){

    }

    public int getBrugerId() {
        return brugerId;
    }

    public void setBrugerId(int brugerId) {
        this.brugerId = brugerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }
}
