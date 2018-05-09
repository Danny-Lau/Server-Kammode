package server.endpoints;

import com.google.gson.Gson;
import server.controller.ProductController;
import server.dbmanager.DbManager;
import server.models.Product;
import server.models.Size;
import server.utility.CurrentUserContext;
import server.utility.Globals;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/Product")
public class ProductEndpoint {

    DbManager db = new DbManager();

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
    @Path("/New")
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
    @POST
    @Path("/New/size")
    public Response createProductSize(String size) throws SQLException{


        Size ProductSize = new Gson().fromJson(size, Size.class);
        String newProductSize = new Gson().toJson(db.createSize(ProductSize));

        if(newProductSize != null){
            Globals.log.writeLog(this.getClass().getName(), this, "Product created", 2);
            return Response.status(200).type("application/json").entity(new Gson().toJson(newProductSize)).build();
        } else {
            Globals.log.writeLog(this.getClass().getName(), this, "No input to new Product", 2);
            return Response.status(400).type("text/plain").entity("Failed creating Product size").build();
        }
    }
    @POST
    @Path("/Update/Size/Stock")
    public Response UpdateProductSize(String size) throws SQLException{


        Size ProductSize = new Gson().fromJson(size, Size.class);
        String newProductSize = new Gson().toJson(db.updateSize(ProductSize));

        if(newProductSize != null){
            Globals.log.writeLog(this.getClass().getName(), this, "Product created", 2);
            return Response.status(200).type("application/json").entity(new Gson().toJson(newProductSize)).build();
        } else {
            Globals.log.writeLog(this.getClass().getName(), this, "No input to new Product", 2);
            return Response.status(400).type("text/plain").entity("Failed creating Product size").build();
        }
    }

    @Path("id/{ProductId}/")
    @GET
    public Response getProductsFromProductId ( @PathParam("ProductId") int productId) throws  SQLException{
        ArrayList<Product> productsFromProductsId = db.getProductsFromProductId(productId);
        String getProductsFromSellerID = new Gson().toJson(productsFromProductsId);

        if(productsFromProductsId != null){
            Globals.log.writeLog(this.getClass().getName(), this, "Products from specific product ID loaded",2);
            return Response.status(200).type("application/json").entity(getProductsFromSellerID).build();

        }else {
            return Response.status(204).type("text/plain").entity("No products").build();
        }
    }

    @Path("sizeID/{ProductId}/")
    @GET
    public Response getSizeFromProductId ( @PathParam("ProductId") int productId) throws  SQLException{
        ArrayList<Size> sizeFromProductsId = db.getSizeFromProductId(productId);
        String SizeFromIDOout = new Gson().toJson(sizeFromProductsId);

        if(sizeFromProductsId != null){
            Globals.log.writeLog(this.getClass().getName(), this, "Products from specific product ID loaded",2);
            return Response.status(200).type("application/json").entity(SizeFromIDOout).build();

        }else {
            return Response.status(204).type("text/plain").entity("No products").build();
        }
    }

    @Path("/{SellerId}")
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

    @Path("/category/{Category}")
    @GET
    public Response getProductsFromCategory ( @PathParam("Category") String category) throws SQLException{
        ArrayList<Product> productsFromCategory = db.getProductsFromCategory(category);
        String getProductsFromCategory = new Gson().toJson(productsFromCategory);

        if(productsFromCategory != null){
            Globals.log.writeLog(this.getClass().getName(), this, "Products from specific category loaded",2);
            return Response.status(200).type("application/json").entity(getProductsFromCategory).build();

        }else {
            return Response.status(204).type("text/plain").entity("No product in this category").build();
        }
    }
    @Path("/gender/{Gender}")
    @GET
    public Response getProductsFromGender ( @PathParam("Gender") String gender) throws SQLException{
        ArrayList<Product> productsFromGender = db.getProductsFromGender(gender);
        String getProductsFromCategory = new Gson().toJson(productsFromGender);

        if(productsFromGender != null){
            Globals.log.writeLog(this.getClass().getName(), this, "Products from specific gender loaded",2);
            return Response.status(200).type("application/json").entity(getProductsFromCategory).build();

        }else {
            return Response.status(204).type("text/plain").entity("No product for this gender").build();
        }
    }

    @DELETE
    @Path("{deleteId}")

    public Response deleteProduct(@PathParam("deleteId") int productId) throws SQLException{
            Boolean productDeleted = productController.deleteProduct(productId);
            if(productDeleted == true) {
                Globals.log.writeLog(this.getClass().getName(), this, "Product deleted", 2);
                return Response.status(200).type("text/plain").entity("Product deleted").build();
            } else {
                Globals.log.writeLog(this.getClass().getName(), this, "Delete product attempt failed", 2);
                return Response.status(400).type("text/plain").entity("Error deleting product").build();
            }
    }
}

