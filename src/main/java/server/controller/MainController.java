package server.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import server.dbmanager.DbManager;
import server.models.SendingInfo;
import server.models.User;
import server.utility.Digester;

import java.io.UnsupportedEncodingException;
import java.util.Date;


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

    //Logic behind creating user.
    public User createUser(User user) {
        DbManager dbManager = new DbManager();

        return dbManager.createUser(user);
    }

    public SendingInfo createSendingInfo(SendingInfo sendingingfor){
        DbManager dbManager = new DbManager();
        SendingInfo createdSendinginfo = dbManager.createSendinngInfo(sendingingfor);
        if(createdSendinginfo != null) {
            return createdSendinginfo;
        } else {
            return null;
        }

    }



}



