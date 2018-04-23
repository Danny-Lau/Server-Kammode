package server.dbmanager;

import com.mysql.cj.x.protobuf.MysqlxCrud;
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
    public Bruger authorizeUser(String username, String password) throws IllegalArgumentException {
        //ResultSet and User to temporary contain values from SQL statement
        ResultSet resultSet = null;
        Bruger bruger = null;
        //Try-catch method to avoid the program crashing on exceptions
        try {
            //SQL statement
            PreparedStatement authorizeUser = connection
                    .prepareStatement("SELECT * from Bruger where user_name = ? AND password = ?");
            //Setting parameters for user object
            authorizeUser.setString(1, username);
            authorizeUser.setString(2, password);

            //ResultSet consisting parameters from SQL statement
            resultSet = authorizeUser.executeQuery();

            //Method will run as long as there is content in the next line of the resultSet
            while (resultSet.next()) {
                bruger = new Bruger();
                bruger.setBrugerId(resultSet.getInt("bruger_id"));
                bruger.setUserName(resultSet.getString("user_name"));
                bruger.setPassword(resultSet.getString("password"));
                bruger.setMail(resultSet.getString("mail"));
                bruger.setType(resultSet.getInt("type"));
                bruger.setTimeCreated(resultSet.getLong("time_created"));

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
        return bruger;
    }

    // Method for creating a user - Boolean returned, which decides if the user is created or not
    public Bruger createUser(Bruger bruger) throws IllegalArgumentException {
        //Try-catch method to avoid the program crashing on exceptions
        try {
            PreparedStatement createUser = connection.prepareStatement("INSERT INTO User (username, password, time_created, mail) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            createUser.setString(1, bruger.getUserName());
            createUser.setString(2, bruger.getPassword());
            createUser.setLong(3, bruger.getTimeCreated());

            //rowsAffected
            int rowsAffected = createUser.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = createUser.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    int autoIncrementedUserId = rs.getInt(1);
                    bruger.setBrugerId(autoIncrementedUserId);
                } else {
                    bruger = null;
                }
                bruger.setType(2);
                return bruger;
            }
            //Exception to avoid crashing
        } catch (SQLException exception) {
            exception.printStackTrace();
            close();
        }
        return null;
    }


    public Sælger creatSælger(Sælger sælger) throws IllegalArgumentException{
        //Try-catch
        try {
            //SQL statement
            PreparedStatement creatSælger = connection
                    .prepareStatement("INSERT INTO `sælger` (`type`, nummer, mail, cvr, firma_navn, sælger_id) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            //Setting parameters for user object
            creatSælger.setInt( 1, sælger.getType());
            creatSælger.setString( 2, sælger.getNummer());
            creatSælger.setString( 3, sælger.getMail());
            creatSælger.setString( 4, sælger.getCvr());
            creatSælger.setString( 5, sælger.getFirmaNavn());
            creatSælger.setInt( 6, sælger.getSælgerId());

            int rowsAffected = creatSælger.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet rs = creatSælger.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    int autoIncrementedID = rs.getInt(1);
                    sælger.setSælgerId(autoIncrementedID);
                } else {
                    sælger = null;
                }
                return sælger;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            close();
        }
        return null;
    }
    public ArrayList<Sælger> loadSælger(){
        ResultSet resultSet = null;
        ArrayList<Sælger> sælgere = new ArrayList<Sælger>();

        try{
            PreparedStatement loadSælger = connection.prepareStatement("SELECT * FROM sælger");
            resultSet = loadSælger.executeQuery();
            while (resultSet.next()){
                Sælger sælger = new Sælger();
                sælger.setSælgerId(resultSet.getInt( "sælger_id"));
                sælger.setCvr(resultSet.getString("cvr"));
                sælger.setFirmaNavn(resultSet.getString("firma_navn"));
                sælger.setMail(resultSet.getString("mail"));
                sælger.setNummer(resultSet.getString("nummer"));
                sælger.setPassword(resultSet.getString("password"));
                sælger.setType(resultSet.getInt("type"));
                sælgere.add(sælger);
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
            } }return sælgere;
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

    public Bruger getUserFromToken(String token) throws SQLException {
        ResultSet resultSet = null;
        Bruger userFromToken = null;

        try {
            PreparedStatement getUserFromToken = connection
                    .prepareStatement("SELECT user_name, bruger_id, mail, `type`, time_created FROM `Bruger` u INNER JOIN Tokens t ON t.tokens_bruger_id = u.bruger_id WHERE t.token = ?");

            getUserFromToken.setString(1, token);
            resultSet = getUserFromToken.executeQuery();

            while (resultSet.next()) {
                userFromToken = new Bruger();
                userFromToken.setBrugerId(resultSet.getInt("user_id"));
                userFromToken.setUserName(resultSet.getString("username"));
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

    public ArrayList<Ordre> loadOrdre(){
        ResultSet resultSet = null;
        ArrayList<Ordre> ordrer = new ArrayList<Ordre>();
        try{
            PreparedStatement loadOrdre = connection.prepareStatement("SELECT * FROM ordre");
            resultSet = loadOrdre.executeQuery();
            while (resultSet.next()){
                Ordre ordre = new Ordre();
                ordre.setOrdreID(resultSet.getInt("ordre_id"));
                ordre.setOrdreDato(resultSet.getString("ordre_date"));
                ordre.setOrdrePris(resultSet.getString("total_ordre_pris"));
                ordrer.add(ordre);
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

    public Ordre getOrderFromId(int ordreID) throws SQLException{
        ResultSet resultSet = null;
        Ordre ordre = null;

        try {
            PreparedStatement getOrderFromId = connection
                    .prepareStatement("SELECT * ordre WHERE ordre_Id = ?");

            getOrderFromId.setInt(1, ordreID);

            resultSet = getOrderFromId.executeQuery();

            while (resultSet.next()) {
                ordre = new Ordre();
                ordre.setOrdreID(resultSet.getInt("ordre_Id"));
                ordre.setOrdreDato(resultSet.getString("ordre_date"));
                ordre.setOrdrePris(resultSet.getString("total_ordre_pris"));

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
        return ordre;



    }

    public Bruger getTimeCreatedByUsername(String username) {
        Bruger bruger = null;
        ResultSet resultSet = null;

        try {
            //SQL statement
            PreparedStatement getTimeCreatedByUsername = connection.prepareStatement("SELECT * FROM User WHERE username = ?");
            getTimeCreatedByUsername.setString(1, username);
            resultSet = getTimeCreatedByUsername.executeQuery();
            while (resultSet.next()) {
                bruger = new Bruger();
                bruger.setTimeCreated(resultSet.getLong("time_created"));
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
        return bruger;
    }

}