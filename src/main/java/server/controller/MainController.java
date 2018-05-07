package server.controller;

import server.dbmanager.DbManager;
import server.models.SendingInfo;
import server.utility.Digester;


public class MainController {
    private Digester digester;


    //The constructor for instantiation
    public MainController() {
        digester = new Digester();

    }

    // Logic behind authorizing user
   /* public String authUser(User user) {
        DbManager dbManager = new DbManager();
        String token = null;
        //Use username to get the time created
        User foundUser = dbManager.getTimeCreatedByUsername(user.getUsername());

        if(foundUser != null) {
            //Add the time created to the password and hash
            user.setPassword(digester.hashWithSalt(user.getPassword() + foundUser.getTimeCreated()));
            //Authorize user
            User authorizedUser = dbManager.authorizeUser(user.getUsername(), user.getPassword());

            return authorizedUser;
        } else {
            return null;
        }
    }*/


    public SendingInfo createSendingInfo(SendingInfo sendingingfor){
        DbManager dbManager = new DbManager();
        SendingInfo createdSendinginfo = dbManager.createShippingInfo(sendingingfor);
        if(createdSendinginfo != null) {
            return createdSendinginfo;
        } else {
            return null;
        }

    }



}



