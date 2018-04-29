package server.controller;

import server.dbmanager.DbManager;
import server.models.Seller;
import server.utility.Digester;

public class SellerController {
    private Digester digester;

    public SellerController(){
        digester = new Digester();
    }

    public boolean deleteSeller (int sellerId){
        DbManager dbManager = new DbManager();
        Boolean ifDeleted = dbManager.deleteSeller(sellerId);
        return ifDeleted;
    }

    public Seller createSeller(Seller seller) {
        DbManager dbManager = new DbManager();

        long unixTime = (long) Math.floor(System.currentTimeMillis() / 10000);
        seller.setTimeCreated(unixTime);

        seller.setPassword(digester.hashWithSalt(seller.getPassword() + seller.getTimeCreated()));

        return dbManager.createSeller(seller);
    }


}
