package server.models;

public class Seller {

   private int sellerId;
   private String companyName;
   private String cvr;
   private String mail;
   private String number;
   private int type;
   private String password;

    public Seller(String companyName, String cvr, String mail, String number, String password) {
        this.companyName = companyName;
        this.cvr = cvr;
        this.mail = mail;
        this.number = number;
        this.password = password;


    }

    public Seller(){

    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
