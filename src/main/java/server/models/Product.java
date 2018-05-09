package server.models;

public class Product {
   private int productID;
   private String productDescription;
   private int sellerID;
   private String price;
   private String gender;
   private String url;
   private String category;


    public Product(String productDescription, int sellerID, String price,  String gender, String url, String category) {
        this.productDescription = productDescription;
        this.sellerID = sellerID;
        this.price = price;
        this.gender = gender;
        this.url = url;
        this.category = category;
    }


    public Product(){

    }



    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }



    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
