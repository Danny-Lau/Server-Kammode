package server.endpoints;

import com.google.gson.Gson;
import server.controller.MainController;
import server.dbmanager.DbManager;
import server.models.User;
import server.utility.Crypter;
import server.utility.CurrentUserContext;
import server.utility.Globals;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


    @Path("/user")
    public class UserEndpoint {
        MainController mainController = new MainController();

        DbManager db = new DbManager();
        Crypter crypter = new Crypter();

        @POST
        @Path("/login")
        //Endpoint for authorizing a User
        public Response logIn(String userIn) {
            User user = new Gson().fromJson(userIn, User.class);
            User userOut = db.authorizeUser(user);

            if (userOut != null) {
                Globals.log.writeLog(this.getClass().getName(), this, "User authorized", 2);
                return Response.status(200).type("application/json").entity(new Gson().toJson(userOut)).build();
            } else {
                Globals.log.writeLog(this.getClass().getName(), this, "User not authorized", 2);
                return Response.status(401).type("text/plain").entity("Error signing in - unauthorized").build();
            }
        }

        @POST
        @Path("/signup")
        //Creating a new user
        public Response signUp(String user) {
            User createdUser = new Gson().fromJson(user, User.class);
            String newUser = new Gson().toJson(db.createUser(createdUser));
            newUser = crypter.encryptAndDecryptXor(newUser);


            if (createdUser != null) {
                Globals.log.writeLog(this.getClass().getName(), this, "User created", 2);
                return Response.status(200).type("application/json").entity(new Gson().toJson(newUser)).build();
            } else {
                Globals.log.writeLog(this.getClass().getName(), this, "Failed creating user", 2);
                return Response.status(400).type("text/plain").entity("Error creating user").build();
            }
        }
    }

