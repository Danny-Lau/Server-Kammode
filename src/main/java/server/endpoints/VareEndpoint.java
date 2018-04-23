package server.endpoints;

import com.google.gson.Gson;
import server.dbmanager.DbManager;
import server.models.Ordre;
import server.models.Vare;
import server.utility.Globals;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/Vare")
public class VareEndpoint {
    DbManager db = new DbManager();

    @GET
    public Response loadAlleVare() throws SQLException {
        ArrayList<Vare> vare = db.loadAlleVare();
        String loadedVare = new Gson().toJson(vare);

        if(vare != null){
            Globals.log.writeLog(this.getClass().getName(), this, "sælger loaded",2);
            return Response.status(200).type("application/json").entity(loadedVare).build();

        }else {
            return Response.status(204).type("text/plain").entity("ingen vare").build();
        }
    }

    @Path("/bySælgerID")
    @GET
    public Response loadVareBySælgerID (int sælgerId) throws  SQLException{
        ArrayList<Vare> vareBySælgerID = db.loadVareBySælgerID(sælgerId);
        String vareBySælgerIDJason = new Gson().toJson(vareBySælgerID);

        if(vareBySælgerID != null){
            Globals.log.writeLog(this.getClass().getName(), this, "sælger loaded",2);
            return Response.status(200).type("application/json").entity(vareBySælgerIDJason).build();

        }else {
            return Response.status(204).type("text/plain").entity("ingen vare").build();
        }
    }

}
