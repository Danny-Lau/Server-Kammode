package server.endpoints;

import com.google.gson.Gson;
import server.controller.ProductController;
import server.controller.TokenController;
import server.dbmanager.DbManager;
import server.models.Product;
import server.utility.CurrentUserContext;
import server.utility.Globals;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/Product")
public class ProductEndpoint {
    DbManager db = new DbManager();
    TokenController tokenController = new TokenController();
    ProductController productController = new ProductController();

    @GET
    public Response loadAllProduct() throws SQLException {
        ArrayList<Product> product = db.loadAlleVare();
        String loadedProduct = new Gson().toJson(product);

        if(product != null){
            Globals.log.writeLog(this.getClass().getName(), this, "products loaded",2);
            return Response.status(200).type("application/json").entity(loadedProduct).build();

        }else {
            return Response.status(204).type("text/plain").entity("No product found").build();
        }
    }

    //Method for creating a product
    @POST
    public Response createProduct(@HeaderParam("authorization") String token, String product) throws SQLException{
        CurrentUserContext currentUser = tokenController.getUserFromTokens(token);

        if(currentUser.getCurrentUser() != null && currentUser.isSeller()){
            Product productCreated = productController.createProduct(new Gson().fromJson(product, Product.class));
            String newProduct = new Gson().toJson(productCreated);

            if(productCreated != null){
                Globals.log.writeLog(this.getClass().getName(), this, "Product created", 2);
                return Response.status(200).type("application/json").entity(new Gson().toJson(newProduct)).build();
            } else {
                Globals.log.writeLog(this.getClass().getName(), this, "No input to new Product", 2);
                return Response.status(400).type("text/plain").entity("Failed creating Product").build();
            }
        } else {
            Globals.log.writeLog(this.getClass().getName(), this, "Unauthorized - create Product", 2);
            return Response.status(401).type("text/plain").entity("Unauthorized").build();

            }

        }


    @Path("{SellerId}")
    @GET
    public Response getProductsFromSellerID ( @PathParam("SellerId") int sellerId) throws  SQLException{
        ArrayList<Product> productsFromSellerID = db.getProductsFromSellerID(sellerId);
        String getProductsFromSellerID = new Gson().toJson(productsFromSellerID);

        if(productsFromSellerID != null){
            Globals.log.writeLog(this.getClass().getName(), this, "Seller´s products loaded",2);
            return Response.status(200).type("application/json").entity(getProductsFromSellerID).build();

        }else {
            return Response.status(204).type("text/plain").entity("This Seller have no products").build();
        }
    }

}
