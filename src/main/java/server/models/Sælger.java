package server.models;

public class Sælger {

   private int sælgerId, type;
   private String firmaNavn, cvr, mail, nummer, password;

    public Sælger(int sælgerId, int type, String firmaNavn, String cvr, String mail, String nummer, String password) {

        this.sælgerId = sælgerId;
        this.type = type;
        this.firmaNavn = firmaNavn;
        this.cvr = cvr;
        this.mail = mail;
        this.nummer = nummer;
        this.password = password;
    }

    public Sælger() {
    }

    public int getSælgerId() {
        return sælgerId;
    }

    public void setSælgerId(int sælgerId) {
        this.sælgerId = sælgerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFirmaNavn() {
        return firmaNavn;
    }

    public void setFirmaNavn(String firmaNavn) {
        this.firmaNavn = firmaNavn;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
