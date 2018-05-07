package server.dbmanager;

import server.models.*;
import server.utility.Crypter;
import server.utility.Globals;

import java.sql.*;
import java.util.ArrayList;

public class DbManager {

    // Creating the connection for the database
    private static final String URL = "jdbc:mysql://"
            + Globals.config.getDatabaseHost() + ":"
            + Globals.config.getDatabasePort() + "/"
            + Globals.config.getDatabaseName() + "?useSSL=false&serverTimezone=GMT";
    private static final String USERNAME = Globals.config.getDatabaseUsername();
    private static final String PASSWORD = Globals.config.getDatabasePassword();
    private static Connection connection = null;

    Crypter crypter = new Crypter();

    public DbManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    //Method for closing the connection
    private void close() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    //Method for authorizing the user. Prepared statement are used, and a user object is returned.
    public User authorizeUser(User tempUser) throws IllegalArgumentException {
        //ResultSet and User to temporary contain values from SQL statement
        ResultSet resultSet = null;
        User user = null;
        //Try-catch method to avoid the program crashing on exceptions
        try {
            //SQL statement
            PreparedStatement authorizeUser = connection
                    .prepareStatement("SELECT * from bruger where user_name = ? AND password = ?");
            //Setting parameters for user object
            authorizeUser.setString(1, tempUser.getUsername());
            authorizeUser.setString(2, tempUser.getPassword());

            //ResultSet consisting parameters from SQL statement
            resultSet = authorizeUser.executeQuery();

            //Method will run as long as there is content in the next line of the resultSet
            while (resultSet.next()) {
                user = new User();
                user.setBrugerId(resultSet.getInt("bruger_id"));
                user.setUsername(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setMail(resultSet.getString("mail"));
                user.setType(resultSet.getInt("type"));
                user.setTimeCreated(resultSet.getLong("time_created"));

            }
            //Exception to avoid crashing
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                //closing the resultSet, because its a temporary table of content
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                close();
            }
        }
        return user;
    }

    //Method for authorizing the user. Prepared statement are used, and a user object is returned.
    public Seller authorizeSeller(Seller tempSeller) throws IllegalArgumentException {
        //ResultSet and User to temporary contain values from SQL statement
        ResultSet resultSet = null;
        Seller seller = null;
        //Try-catch method to avoid the program crashing on exceptions
        try {
            //SQL statement
            PreparedStatement authorizeSeller = connection
                    .prepareStatement("SELECT * from sælger where firma_navn = ? AND password = ?");
            //Setting parameters for user object
            authorizeSeller.setString(1, tempSeller.getCompanyName());
            authorizeSeller.setString(2, tempSeller.getPassword());

            //ResultSet consisting parameters from SQL statement
            resultSet = authorizeSeller.executeQuery();

            //Method will run as long as there is content in the next line of the resultSet
            while (resultSet.next()) {
                seller = new Seller();
                seller.setSellerId(resultSet.getInt("sælger_id"));
                seller.setCompanyName(resultSet.getString("firma_navn"));
                seller.setPassword(resultSet.getString("password"));
                seller.setCvr(resultSet.getString("cvr"));
                seller.setType(resultSet.getInt("type"));
                seller.setNumber(resultSet.getString("nummer"));

            }
            //Exception to avoid crashing
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                //closing the resultSet, because its a temporary table of content
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                close();
            }
        }
        return seller;
    }

    //Method for authorizing the user. Prepared statement are used, and a user object is returned.
    public Admin authorizeAdmin(Admin tempAdmin) throws IllegalArgumentException {
        //ResultSet and User to temporary contain values from SQL statement
        ResultSet resultSet = null;
        Admin admin = null;
        //Try-catch method to avoid the program crashing on exceptions
        try {
            //SQL statement
            PreparedStatement authorizeAdmin = connection
                    .prepareStatement("SELECT * from admin where admin_name = ? AND password = ?");
            //Setting parameters for user object
            authorizeAdmin.setString(1, tempAdmin.getUsername());
            authorizeAdmin.setString(2, tempAdmin.getPassword());

            //ResultSet consisting parameters from SQL statement
            resultSet = authorizeAdmin.executeQuery();

            //Method will run as long as there is content in the next line of the resultSet
            while (resultSet.next()) {
                admin = new Admin();
                admin.setAdminId(resultSet.getInt("admin_id"));
                admin.setUsername(resultSet.getString("admin_name"));
                admin.setPassword(resultSet.getString("password"));
                admin.setType(resultSet.getInt("type"));


            }
            //Exception to avoid crashing
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                //closing the resultSet, because its a temporary table of content
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                close();
            }
        }
        return admin;
    }


    // Method for creating a user - Boolean returned, which decides if the user is created or not
    public User createUser(User user) throws IllegalArgumentException {
        //Try-catch method to avoid the program crashing on exceptions
        try {
            PreparedStatement createUser = connection.prepareStatement("INSERT INTO bruger (user_name, password, mail) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            createUser.setString(1, user.getUsername());
            createUser.setString(2, user.getPassword());
            createUser.setString(3, user.getMail());

            //rowsAffected
            int rowsAffected = createUser.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = createUser.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    int autoIncrementedUserId = rs.getInt(1);
                    user.setBrugerId(autoIncrementedUserId);
                } else {
                    user = null;
                }
                return user;
            }
            //Exception to avoid crashing
        } catch (SQLException exception) {
            exception.printStackTrace();
            close();
        }
        return null;
    }


    public Seller createSeller(Seller seller) throws IllegalArgumentException{
        //Try-catch
        try {
            //SQL statement
            PreparedStatement createSeller = connection
                    .prepareStatement("INSERT INTO sælger (firma_navn, cvr, mail, password) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            //Setting parameters for user object
            createSeller.setString( 1, seller.getCompanyName());
            createSeller.setString( 2, seller.getCvr());
            createSeller.setString( 3, seller.getMail());
            createSeller.setString( 4, seller.getPassword());


            int rowsAffected = createSeller.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = createSeller.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    int autoIncrementedId = rs.getInt(1);
                    seller.setSellerId(autoIncrementedId);
                } else {
                    seller = null;
                }
                seller.setType(1);
                return seller;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            close();
        }
        return null;
    }

    public ArrayList<Seller> loadSeller(){
        ResultSet resultSet = null;
        ArrayList<Seller> sellers = new ArrayList<Seller>();

        try{
            PreparedStatement loadSælger = connection.prepareStatement("SELECT * FROM sælger");
            resultSet = loadSælger.executeQuery();
            while (resultSet.next()){
                Seller seller = new Seller();
                seller.setSellerId(resultSet.getInt( "sælger_id"));
                seller.setCvr(resultSet.getString("cvr"));
                seller.setCompanyName(resultSet.getString("firma_navn"));
                seller.setMail(resultSet.getString("mail"));
                seller.setNumber(resultSet.getString("nummer"));
                seller.setPassword(resultSet.getString("password"));
                seller.setType(resultSet.getInt("type"));
                sellers.add(seller);
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                //closing the resultSet, because its a temporary table of content
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                close();
            } }return sellers;
    }


    public void createToken(String token, int userId) {
        try {
            PreparedStatement addTokenStatement = connection.prepareStatement("INSERT INTO Tokens (token, token_user_id) VALUES (?,?)");
            addTokenStatement.setString(1, token);
            addTokenStatement.setInt(2, userId);
            addTokenStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
            close();
        }
    }

    public boolean deleteToken(int userId) throws SQLException {
        try {
            PreparedStatement deleteTokenStatement = connection.prepareStatement("DELETE FROM Tokens WHERE token_user_id = ?");
            deleteTokenStatement.setInt(1, userId);

            int rowsAffected = deleteTokenStatement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            close();
        }
        return false;
    }


    public User getUserFromToken(String token) throws SQLException {
        ResultSet resultSet = null;
        User userFromToken = null;

        try {
            PreparedStatement getUserFromToken = connection
                    .prepareStatement("SELECT user_name, bruger_id, mail, `type`, time_created FROM `Bruger` u INNER JOIN Tokens t ON t.tokens_bruger_id = u.bruger_id WHERE t.token = ?");

            getUserFromToken.setString(1, token);
            resultSet = getUserFromToken.executeQuery();

            while (resultSet.next()) {
                userFromToken = new User();
                userFromToken.setBrugerId(resultSet.getInt("user_id"));
                userFromToken.setUsername(resultSet.getString("username"));
                userFromToken.setType(resultSet.getInt("type"));
                userFromToken.setTimeCreated(resultSet.getLong("time_created"));

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                close();
            }
        }
        return userFromToken;

    }

    //Method for deleting a product and it´ sub-tables
    public boolean deleteProduct(int productId) throws IllegalArgumentException{
        try {
            PreparedStatement deleteProduct = connection
                    .prepareStatement("DELETE FROM vare WHERE vare_id = ?");
            deleteProduct.setInt( 1, productId);
            int rowsaffected = deleteProduct.executeUpdate();
            if(rowsaffected == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return false;
    }

    //Method for deleting a seller and it´ sub-tables
    public boolean deleteSeller(int sellerId) throws IllegalArgumentException {
        try {
            PreparedStatement deleteSeller = connection
                    .prepareStatement("DELETE FROM sælger WHERE sælger_id = ?");
            deleteSeller.setInt(1, sellerId);
            int rowsaffected = deleteSeller.executeUpdate();
            if (rowsaffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return false;
    }


    public ArrayList<Product> loadAllProduct(){
        ResultSet resultSet = null;
        ArrayList<Product> allProduct = new ArrayList<Product>();
        try{
            PreparedStatement loadVare = connection.prepareStatement("SELECT * FROM vare");
            resultSet = loadVare.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductID(resultSet.getInt("vare_id"));
                product.setStock(resultSet.getInt("antal"));
                product.setPrice(resultSet.getString("pris"));
                product.setSellerID(resultSet.getInt("sælger_sælger_id"));
                product.setProductDescription(resultSet.getString("vare_beskrivelse"));
                product.setVariant(resultSet.getString("variant_1"));
                allProduct.add(product);
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                //closing the resultSet, because its a temporary table of content
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                close();
            } }return allProduct;
    }

    public ArrayList<Product> getProductsFromSellerID(int sellerId){
        ResultSet resultSet = null;
        ArrayList<Product> allProductFromSellerId = new ArrayList<Product>();
        try{
            PreparedStatement getProductsFromSellerID = connection.prepareStatement("SELECT * FROM vare WHERE sælger_sælger_id = ? ");

            getProductsFromSellerID.setInt(1, sellerId);
            resultSet = getProductsFromSellerID.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductID(resultSet.getInt("vare_id"));
                product.setStock(resultSet.getInt("antal"));
                product.setPrice(resultSet.getString("pris"));
                product.setSellerID(resultSet.getInt("sælger_sælger_id"));
                product.setProductDescription(resultSet.getString("vare_beskrivelse"));
                product.setVariant(resultSet.getString("variant_1"));
                allProductFromSellerId.add(product);
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                //closing the resultSet, because its a temporary table of content
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                close();
            } }return allProductFromSellerId;
    }


    public ArrayList<Order> loadOrdre(){
        ResultSet resultSet = null;
        ArrayList<Order> ordrer = new ArrayList<Order>();
        try{
            PreparedStatement loadOrdre = connection.prepareStatement("SELECT * FROM ordre");
            resultSet = loadOrdre.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setOrderID(resultSet.getInt("ordre_id"));
                order.setOrderDate(resultSet.getString("ordre_date"));
                order.setOrderPrice(resultSet.getString("total_ordre_pris"));
                ordrer.add(order);
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                //closing the resultSet, because its a temporary table of content
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                close();
            } }return ordrer;
    }

    public Order getOrderFromId(int ordreID) throws SQLException{
        ResultSet resultSet = null;
        Order order = null;

        try {
            PreparedStatement getOrderFromId = connection
                    .prepareStatement("SELECT * ordre WHERE ordre_id = ?");

            getOrderFromId.setInt(1, ordreID);

            resultSet = getOrderFromId.executeQuery();

            while (resultSet.next()) {
                order = new Order();
                order.setOrderID(resultSet.getInt("ordre_id"));
                order.setOrderDate(resultSet.getString("ordre_date"));
                order.setOrderPrice(resultSet.getString("total_ordre_pris"));

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
                close();
            }
        }
        return order;



    }


    // Method for creating sending information - Boolean returned, which decides if the information is created or not
    public SendingInfo createSendinngInfo(SendingInfo sendingInfo) throws IllegalArgumentException{

        try {
            PreparedStatement createSendingInfo = connection.prepareStatement("INSERT INTO forsendelse_informationer (adresse, postnummer, by) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            createSendingInfo.setString(1, sendingInfo.getAdress());
            createSendingInfo.setInt(2, sendingInfo.getPostNumber());
            createSendingInfo.setString(3, sendingInfo.getCity());

            int rowsaffected = createSendingInfo.executeUpdate();
            if(rowsaffected == 1){
                ResultSet resultSet = createSendingInfo.getGeneratedKeys();
                if(resultSet != null && resultSet.next()){
                    int autoIncrementedSendingInfoId = resultSet.getInt(1);
                    sendingInfo.setSendinginfoId(autoIncrementedSendingInfoId);
                } else {
                    sendingInfo = null;
                }
                return sendingInfo;
            }
        } catch (SQLException exception){
            exception.printStackTrace();
            close();
        }
        return null;
    }

    // Method for creating a product - Boolean returned, which decides if the product is created or not
    public Product createProduct(Product product) throws IllegalArgumentException{
        //Try-catch method to avoid the program crashing on exceptions
        try{
            PreparedStatement createProduct = connection.prepareStatement("INSERT INTO vare (vare_beskrivelse, antal, sælger_sælger_id, pris, variant_1) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            createProduct.setString(1, product.getProductDescription());
            createProduct.setInt(2, product.getStock());
            createProduct.setInt(3, product.getSellerID());
            createProduct.setString(4, product.getPrice());
            createProduct.setString(5, product.getVariant());

            //rowsAffected
            int rowsaffected = createProduct.executeUpdate();
            if(rowsaffected ==1){
                ResultSet resultSet = createProduct.getGeneratedKeys();
                if(resultSet != null && resultSet.next()){
                    int autoIncrementedProductId = resultSet.getInt(1);
                    product.setProductID(autoIncrementedProductId);
                } else {
                    product = null;
                }
                return product;
            }

        } catch (SQLException exception){
            exception.printStackTrace();
            close();
        }
        return null;
    }

}