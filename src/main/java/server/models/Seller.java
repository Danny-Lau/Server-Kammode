package server.models;

public class Seller {

   private int sælgerId;
   private String firmanavn;
   private String cvr;
   private String mail;
   private String nummer;
   private int type;
   private String password;

    public Seller(String firmanavn, String cvr, String mail, String nummer, int type, String password) {
        this.firmanavn = firmanavn;
        this.cvr = cvr;
        this.mail = mail;
        this.nummer = nummer;
        this.type = type;
        this.password = password;

    }

    public Seller(){

    }

    public int getSælgerId() {
        return sælgerId;
    }

    public void setSælgerId(int sælgerId) {
        this.sælgerId = sælgerId;
    }

    public String getFirmanavn() {
        return firmanavn;
    }

    public void setFirmanavn(String firmanavn) {
        this.firmanavn = firmanavn;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
