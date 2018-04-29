package server.endpoints;


import com.google.gson.Gson;
import server.dbmanager.DbManager;
import server.models.Order;
import server.utility.Globals;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/ordre")
public class OrdreEndpoint {
    DbManager db = new DbManager();

    @GET
    public Response loadOrdre() throws SQLException {
        ArrayList<Order> ordrer = db.loadOrdre();
        String loadedOrdre = new Gson().toJson(ordrer);

        if(ordrer != null){
            Globals.log.writeLog(this.getClass().getName(), this, "Order loaded",2);
            return Response.status(200).type("application/json").entity(loadedOrdre).build();

        }else {
            return Response.status(204).type("text/plain").entity("No order").build();
        }
    }

    @Path("/{ordreId}")
    @GET
    public Response getOrderFromId(@PathParam("ordreId") int orderId) throws SQLException {

        String ordre = new Gson().toJson( db.getOrderFromId(orderId));

        if(ordre != null){
            Globals.log.writeLog(this.getClass().getName(), this, "Order loaded",2);
            return Response.status(200).type("application/json").entity(ordre).build();

        }else {
            return Response.status(204).type("text/plain").entity("No order was found with that specific ID").build();
        }
    }
}
