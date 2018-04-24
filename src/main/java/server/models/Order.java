package server.models;

public class Order {

    private int orderID;
    private String orderDate;
    private String orderPrice;


    public Order(String orderDate, String orderPrice) {
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;

    }

    public Order() {
    }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String ordeeDate) {
        this.orderDate = ordeeDate;
    }

    public String getOrdrePrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

}
