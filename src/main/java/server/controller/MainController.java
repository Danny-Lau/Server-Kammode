package server.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import server.dbmanager.DbManager;
import server.models.Bruger;
import server.utility.Digester;

import java.io.UnsupportedEncodingException;
import java.util.Date;


public class MainController {
    private Digester digester;


    //The constructor for instantiation
    public MainController() {
        digester = new Digester();

    }

    // Logic behind authorizing bruger
    public String authUser(Bruger bruger) {
        DbManager dbManager = new DbManager();
        String token = null;
        //Use username to get the time created
        Bruger foundUser = dbManager.getTimeCreatedByUsername(bruger.getUserName());

        if(foundUser != null) {
            //Add the time created to the password and hash
            bruger.setPassword(digester.hashWithSalt(bruger.getPassword() + foundUser.getTimeCreated()));
            //Authorize bruger
            Bruger authorizedUser = dbManager.authorizeUser(bruger.getUserName(), bruger.getPassword());

            //Generate token at login
            try {
                Algorithm algorithm = Algorithm.HMAC256("Secret");
                long timeValue = (System.currentTimeMillis() * 1000) + 20000205238L;
                Date expDate = new Date(timeValue);

                token = JWT.create().withClaim("User", authorizedUser.getUserName()).withExpiresAt(expDate).withIssuer("IMHO").sign(algorithm);
                //Add token to database
                dbManager.createToken(token, authorizedUser.getBrugerId());

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (token != null) {
                return token;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    //Logic behind creating user.
    public Bruger createUser(Bruger bruger) {
        DbManager dbManager = new DbManager();
        //Add the time created to user
        long unixTime = (long) Math.floor(System.currentTimeMillis() / 10000);
        bruger.setTimeCreated(unixTime);
        //Add the time created to the password and hash
        bruger.setPassword(digester.hashWithSalt(bruger.getPassword()+bruger.getTimeCreated()));

        return dbManager.createUser(bruger);
    }



}



