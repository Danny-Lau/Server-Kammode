package server.models;

public class ShippingInfo {

    private int shippingInforId;
    private String adress;
    private int postNumber;
    private String city;

    public ShippingInfo(String adress, int postNumber, String city) {
        this.adress = adress;
        this.postNumber = postNumber;
        this.city = city;
    }

    public int getShippingInforId() {
        return shippingInforId;
    }

    public void setShippingInforId(int shippingInforId) {
        this.shippingInforId = shippingInforId;
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
