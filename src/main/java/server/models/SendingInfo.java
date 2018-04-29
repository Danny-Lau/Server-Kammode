package server.models;

public class SendingInfo {

    private int SendinginfoId;
    private String adress;
    private int postNumber;
    private String city;

    public SendingInfo(String adress, int postNumber, String city) {
        this.adress = adress;
        this.postNumber = postNumber;
        this.city = city;
    }

    public int getSendinginfoId() {
        return SendinginfoId;
    }

    public void setSendinginfoId(int sendinginfoId) {
        SendinginfoId = sendinginfoId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
