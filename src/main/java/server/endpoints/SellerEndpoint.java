package server.endpoints;


        import com.google.gson.Gson;
        import server.controller.TokenController;
        import server.dbmanager.DbManager;
        import server.models.Seller;
        import server.utility.Crypter;
        import server.utility.CurrentUserContext;
        import server.utility.Globals;

        import javax.ws.rs.GET;
        import javax.ws.rs.HeaderParam;
        import javax.ws.rs.POST;
        import javax.ws.rs.Path;
        import javax.ws.rs.core.Response;
        import java.sql.SQLException;
        import java.util.ArrayList;

@Path("/sælger")
public class SellerEndpoint {

        TokenController tokenController = new TokenController();
        Crypter crypter = new Crypter();
        DbManager db = new DbManager();

        @GET
        public Response loadSælgere(@HeaderParam("authorization") String token) throws SQLException{
                CurrentUserContext currentUser = tokenController.getUserFromTokens(token);


                if(currentUser.getCurrentUser() != null) {
                        ArrayList<Seller> sellers = db.loadSælger();
                        String loadedSeller = new Gson().toJson(sellers);

                        if (sellers != null) {
                                Globals.log.writeLog(this.getClass().getName(), this, "seller loaded", 2);
                                return Response.status(200).type("application/json").entity(loadedSeller).build();

                        } else {
                                return Response.status(204).type("text/plain").entity("No Sellere").build();
                        }

                } else {
                        Globals.log.writeLog(this.getClass().getName(), this, "Unauthorized - load Sellers", 2);
                        return Response.status(401).type("text/plain").entity("Unauthorized").build();
                }
        }

        @POST
        @Path("/creatSeller")
        public Response creatNewSælger() throws SQLException{
             return  Response.status(200).type("application/json").entity("Seller created").build();

        }


}
