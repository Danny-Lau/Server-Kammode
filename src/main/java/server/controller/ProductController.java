package server.controller;

import server.dbmanager.DbManager;
import server.models.Product;

public class ProductController {

    //Method for creating a question
    public Product createProduct(Product product) {
        DbManager dbManager = new DbManager();
        Product createdProduct = dbManager.createProduct(product);
        if(createdProduct != null){
            return createdProduct;
        } else {
            return null;
        }
    }

    public boolean deleteProduct (int productId){
        DbManager dbManager = new DbManager();
        Boolean ifDeleted = dbManager.deleteProduct(productId);
        return ifDeleted;
    }


}
