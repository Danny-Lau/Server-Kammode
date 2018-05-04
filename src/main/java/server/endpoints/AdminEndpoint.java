package server.endpoints;

import com.google.gson.Gson;
import server.dbmanager.DbManager;
import server.models.Admin;
import server.models.Seller;
import server.utility.Globals;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/admin")
public class AdminEndpoint {

    DbManager db = new DbManager();

    @POST
    @Path("/login")
    //Endpoint for authorizing a seller
    public Response logIn(String admin) {
        Admin AdminData = new Gson().fromJson(admin, Admin.class);
        Admin adminLogin = db.authorizeAdmin(AdminData);


        if (adminLogin != null) {
            Globals.log.writeLog(this.getClass().getName(), this, "Admin authorized", 2);
            return Response.status(200).type("application/json").entity(new Gson().toJson(adminLogin)).build();
        } else {
            Globals.log.writeLog(this.getClass().getName(), this, "Admin not authorized", 2);
            return Response.status(401).type("text/plain").entity("Error signing in - unauthorized").build();
        }
    }

}
