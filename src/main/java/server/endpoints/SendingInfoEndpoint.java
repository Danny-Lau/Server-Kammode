package server.endpoints;

import com.google.gson.Gson;
import server.controller.MainController;
import server.dbmanager.DbManager;
import server.models.SendingInfo;
import server.utility.Crypter;
import server.utility.Globals;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/sendingInfo")
    public class SendingInfoEndpoint {
        MainController mainController = new MainController();
        DbManager db = new DbManager();

        Crypter crypter = new Crypter();

        @POST
        public javax.ws.rs.core.Response createSendingInfo (String sendingInfo) throws IllegalArgumentException{
            SendingInfo createdSendingInfo = mainController.createSendingInfo(new Gson().fromJson(sendingInfo, SendingInfo.class));
            String newSendingInfo = new Gson().toJson(createdSendingInfo);
            newSendingInfo = crypter.encryptAndDecryptXor(newSendingInfo);

            if(createdSendingInfo != null){
                Globals.log.writeLog(this.getClass().getName(), this, "Sending information created", 2);
                return javax.ws.rs.core.Response.status(200).type("application/json").entity(new Gson().toJson(newSendingInfo)).build();
            } else {
                Globals.log.writeLog(this.getClass().getName(), this, "Failed creating Sending information", 2);
                return javax.ws.rs.core.Response.status(400).type("text/plain").entity("Error creating Sending Information").build();
            }
        }
    }

