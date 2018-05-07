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
        ArrayList<Product> product = db.loadAllProduct();
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
    public Response createProduct(String product) throws SQLException{

            Product productCreated = new Gson().fromJson(product, Product.class);
            String newProduct = new Gson().toJson(db.createProduct(productCreated));

            if(newProduct != null){
                Globals.log.writeLog(this.getClass().getName(), this, "Product created", 2);
                return Response.status(200).type("application/json").entity(new Gson().toJson(newProduct)).build();
            } else {
                Globals.log.writeLog(this.getClass().getName(), this, "No input to new Product", 2);
                return Response.status(400).type("text/plain").entity("Failed creating Product").build();
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

    @DELETE
    @Path("{deleteId}")

    public Response deleteProduct(@HeaderParam("authorization") String token, @PathParam("deleteId") int productId) throws SQLException{
        CurrentUserContext currentUser = tokenController.getUserFromTokens(token);

        if(currentUser.getCurrentUser() != null && currentUser.isSeller()){
            Boolean productDeleted = productController.deleteProduct(productId);
            if(productDeleted == true) {
                Globals.log.writeLog(this.getClass().getName(), this, "Product deleted", 2);
                return Response.status(200).type("text/plain").entity("Product deleted").build();
            } else {
                Globals.log.writeLog(this.getClass().getName(), this, "Delete product attempt failed", 2);
                return Response.status(400).type("text/plain").entity("Error deleting product").build();
            }
        } else {
            Globals.log.writeLog(this.getClass().getName(), this, "Unauthorized - delete product", 2);
            return Response.status(401).type("text/plain").entity("Unauthorized").build();
        }
    }
}

