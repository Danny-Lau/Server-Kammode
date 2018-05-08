package server.models;

public class Product {
   private int productID;
   private String productDescription;
   private int stock;
   private int sellerID;
   private String price;
   private String variant;
   private String gender;
   private String url;
   private String category;


    public Product(String productDescription, int stock, int sellerID, String price, String variant, String gender, String url, String category) {
        this.productDescription = productDescription;
        this.stock = stock;
        this.sellerID = sellerID;
        this.price = price;
        this.variant = variant;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
