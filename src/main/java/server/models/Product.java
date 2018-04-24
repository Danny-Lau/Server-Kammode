package server.models;

public class Product {
   private int productID;
   private String productDescription;
   private int numbers;
   private int sellerID;
   private String price;
   private String variant;


    public Product(String productDescription, int numbers, int sellerID, String price, String variant) {
        this.productDescription = productDescription;
        this.numbers = numbers;
        this.sellerID = sellerID;
        this.price = price;
        this.variant = variant;
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

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
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
