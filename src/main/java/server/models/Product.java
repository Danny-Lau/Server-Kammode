package server.models;

public class Product {
   private int productID;
   private String productDescription;
   private int stock;
   private int sellerID;
   private String price;
   private String variant;
   private String gender;
   private Long url;



    public Product(String productDescription, int sellerID, String price, String variant, String gender, Long url) {
        this.productDescription = productDescription;
        this.stock = stock;
        this.url = url;
        this.sellerID = sellerID;
        this.price = price;
        this.variant = variant;
        this.gender = gender;
    }


    public Product(){

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getUrl() {
        return url;
    }

    public void setUrl(Long url) {
        this.url = url;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int numbers) {
        this.stock = numbers;
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

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

}
