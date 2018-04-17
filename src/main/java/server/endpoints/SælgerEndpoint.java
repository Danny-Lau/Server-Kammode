package server.endpoints;


        import com.google.gson.Gson;
        import server.controller.TokenController;
        import server.dbmanager.DbManager;
        import server.models.Sælger;
        import server.utility.Crypter;
        import server.utility.Globals;

        import javax.ws.rs.GET;
        import javax.ws.rs.POST;
        import javax.ws.rs.Path;
        import javax.ws.rs.core.Response;
        import java.sql.SQLException;
        import java.util.ArrayList;

@Path("/sælger")
public class SælgerEndpoint {

        TokenController tokenController = new TokenController();
        Crypter crypter = new Crypter();
        DbManager db = new DbManager();

        @POST
        @Path("/creatNewSælger")
        public Response creatNewSælger() throws SQLException{
             return  Response.status(200).type("application/json").entity("Alt gik som det skulle").build();

        }

        @GET
        public Response loadSælgere() throws SQLException{
                ArrayList<Sælger> sælgere = db.loadSælger();
                String loadedSælgere = new Gson().toJson(sælgere);

                if(sælgere != null){
                        Globals.log.writeLog(this.getClass().getName(), this, "sælger loaded",2);
                        return Response.status(200).type("application/json").entity(loadedSælgere).build();

                }else {
                        return Response.status(204).type("text/plain").entity("ingen sælgere").build();
                }
        }

}
