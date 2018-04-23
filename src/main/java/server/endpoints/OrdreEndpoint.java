package server.endpoints;


import com.google.gson.Gson;
import server.dbmanager.DbManager;
import server.models.Ordre;
import server.utility.Globals;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/ordre")
public class OrdreEndpoint {
    DbManager db = new DbManager();
    int id;
    @GET
    public Response loadOrdre() throws SQLException {
        ArrayList<Ordre> ordrer = db.loadOrdre();
        String loadedOrdre = new Gson().toJson(ordrer);

        if(ordrer != null){
            Globals.log.writeLog(this.getClass().getName(), this, "sælger loaded",2);
            return Response.status(200).type("application/json").entity(loadedOrdre).build();

        }else {
            return Response.status(204).type("text/plain").entity("ingen ordre").build();
        }
    }

    @Path("/spesifik")
    @GET
    public Response getSpesifkOrdre(int ordrerId) throws SQLException {

        String ordre = new Gson().toJson( db.getOrderFromId(ordrerId));

        if(ordre != null){
            Globals.log.writeLog(this.getClass().getName(), this, "sælger loaded",2);
            return Response.status(200).type("application/json").entity(ordre).build();

        }else {
            return Response.status(204).type("text/plain").entity("ingen ordre der mather det vlagte id").build();
        }
    }
}
