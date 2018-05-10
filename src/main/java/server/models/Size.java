package server.models;

public class Size {
    int sizeId, stock, productId;
    String size;
    public Size(int sizeId, String size, int stock, int productId) {
        this.sizeId = sizeId;
        this.size = size;
        this.stock = stock;
        this.productId = productId;
    }

    public Size() {
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
