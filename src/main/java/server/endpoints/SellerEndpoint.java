package server.endpoints;

import com.google.gson.Gson;
        import server.controller.SellerController;
        import server.controller.TokenController;
        import server.dbmanager.DbManager;
        import server.models.Seller;
import server.models.User;
import server.utility.Crypter;
        import server.utility.CurrentUserContext;
        import server.utility.Globals;

        import javax.ws.rs.*;
        import javax.ws.rs.core.Response;
        import java.sql.SQLException;
        import java.util.ArrayList;

@Path("/seller")
public class SellerEndpoint {

        TokenController tokenController = new TokenController();
        SellerController sellerController = new SellerController();
        Crypter crypter = new Crypter();
        DbManager db = new DbManager();

        @GET
        public Response loadSellers(@HeaderParam("authorization") String token) throws SQLException{
                CurrentUserContext currentUser = tokenController.getUserFromTokens(token);


                if(currentUser.getCurrentUser() != null) {
                        ArrayList<Seller> sellers = db.loadSeller();
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
        @Path("/login")
        //Endpoint for authorizing a seller
        public Response logIn(String seller) {
                Seller sellerData = new Gson().fromJson(seller, Seller.class);
                Seller sellerLogin = db.authorizeSeller(sellerData);


                if (sellerLogin != null) {
                        Globals.log.writeLog(this.getClass().getName(), this, "Seller authorized", 2);
                        return Response.status(200).type("application/json").entity(new Gson().toJson(sellerLogin)).build();
                } else {
                        Globals.log.writeLog(this.getClass().getName(), this, "Seller not authorized", 2);
                        return Response.status(401).type("text/plain").entity("Error signing in - unauthorized").build();
                }
        }

        @POST
        @Path("/creatSeller")
        public Response creatSeller(@HeaderParam("authorization") String token, String seller) throws SQLException {
                CurrentUserContext currentUSer = tokenController.getUserFromTokens(token);

                if (currentUSer.getCurrentUser() != null && currentUSer.isAdmin()) {
                        Seller sellerCreated = sellerController.createSeller(new Gson().fromJson(seller, Seller.class));
                        String newSeller = new Gson().toJson(sellerCreated);
                        newSeller = crypter.encryptAndDecryptXor(newSeller);

                        if (sellerCreated != null) {
                                Globals.log.writeLog(this.getClass().getName(), this, "Seller created", 2);
                                return Response.status(200).type("application/json").entity(new Gson().toJson(newSeller)).build();
                        } else {
                                Globals.log.writeLog(this.getClass().getName(), this, "Failed creating seller", 2);
                                return Response.status(400).type("text/plain").entity("Error creating seller").build();
                        }

                } else {
                        Globals.log.writeLog(this.getClass().getName(), this, "Unauthorized - create Seller", 2);
                        return Response.status(401).type("application/json").entity("Unauthorized").build();
                }
        }


        @DELETE
        @Path("{deleteId}")
        public Response deleteSeller(@HeaderParam("authorization") String token, @PathParam("deleteId") int sellerId) throws SQLException{
                CurrentUserContext currentUser = tokenController.getUserFromTokens(token);

                if(currentUser.getCurrentUser() != null && currentUser.isAdmin()){
                        Boolean sellerDeleted = sellerController.deleteSeller(sellerId);
                        if(sellerDeleted == true){
                                Globals.log.writeLog(this.getClass().getName(), this, "Seller deleted", 2);
                                return Response.status(200).type("text/plain").entity("Seller deleted").build();
                        } else {
                                Globals.log.writeLog(this.getClass().getName(), this, "Delete seller attempt failed", 2);
                                return Response.status(400).type("text/plain").entity("Error deleting seller").build();
                        }
                } else {
                        Globals.log.writeLog(this.getClass().getName(), this, "Unauthorized - delete seller", 2);
                        return Response.status(401).type("text/plain").entity("Unauthorized").build();
                }
        }


                        }



