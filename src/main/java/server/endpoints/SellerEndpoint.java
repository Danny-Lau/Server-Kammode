package server.endpoints;

import com.google.gson.Gson;
        import server.controller.SellerController;
import server.dbmanager.DbManager;
        import server.models.Seller;
import server.utility.Crypter;
        import server.utility.CurrentUserContext;
        import server.utility.Globals;

        import javax.ws.rs.*;
        import javax.ws.rs.core.Response;
        import java.sql.SQLException;
        import java.util.ArrayList;

@Path("/seller")
public class SellerEndpoint {

        SellerController sellerController = new SellerController();
        Crypter crypter = new Crypter();
        DbManager db = new DbManager();

        @GET
        public Response loadSellers() throws SQLException{
                        ArrayList<Seller> sellers = db.loadSeller();
                        String loadedSeller = new Gson().toJson(sellers);

                        if (sellers != null) {
                                Globals.log.writeLog(this.getClass().getName(), this, "seller loaded", 2);
                                return Response.status(200).type("application/json").entity(loadedSeller).build();

                        } else {
                                return Response.status(204).type("text/plain").entity("No Sellere").build();
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
                       Seller sellerOut = null;
                        Seller sellerCreated = new Gson().fromJson(seller, Seller.class);
                        sellerOut = db.createSeller(sellerCreated);
                        String newSeller = new Gson().toJson(sellerOut);
                        newSeller = crypter.encryptAndDecryptXor(newSeller);

                        if (sellerOut != null) {
                                Globals.log.writeLog(this.getClass().getName(), this, "Seller created", 2);
                                return Response.status(200).type("application/json").entity(new Gson().toJson(newSeller)).build();
                        } else {
                                Globals.log.writeLog(this.getClass().getName(), this, "Failed creating seller", 2);
                                return Response.status(400).type("text/plain").entity("Error creating seller").build();
                        }
        }


        @DELETE
        @Path("{deleteId}")
        public Response deleteSeller(@HeaderParam("authorization") String token, @PathParam("deleteId") int sellerId) throws SQLException{
                        Boolean sellerDeleted = sellerController.deleteSeller(sellerId);
                        if(sellerDeleted == true){
                                Globals.log.writeLog(this.getClass().getName(), this, "Seller deleted", 2);
                                return Response.status(200).type("text/plain").entity("Seller deleted").build();
                        } else {
                                Globals.log.writeLog(this.getClass().getName(), this, "Delete seller attempt failed", 2);
                                return Response.status(400).type("text/plain").entity("Error deleting seller").build();
                        }
        }


                        }



