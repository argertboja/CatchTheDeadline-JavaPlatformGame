package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBInterface {

    // Variables
    private static Connection con;

    // Getting connection to the database and create it
    private void getConnection() throws ClassNotFoundException, SQLException {
        DriverManager.registerDriver (new com.mysql.jdbc.Driver());
        con = DriverManager.getConnection("jdbc:mysql://160.153.75.104:3306/ctd", "catchthedeadline", "MustFindABetter1");
        initializeAccounts();
    }

    // Initialize Accounts' Table
    private void initializeAccounts() throws SQLException {
        Statement statement = con.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS ctdtable ( id integer,"
                +"username varchar(28), pasw varchar(28), scores integer, coins integer, eraser integer, paintSpray integer,"
                + "primary key (id));");
        //Statement state3 = con.createStatement();
        //state3.execute("INSERT INTO accounts (id, username, pasw) VALUES (1, 'admin', 'admin1')");
    }

    // Add Account
    public void createUser (String username, String pasw) throws ClassNotFoundException, SQLException{
        if (con == null) {
            getConnection();
        }
        PreparedStatement prep = con.prepareStatement("INSERT INTO ctdtable (username, pasw) VALUES ('"+username+"' , '" + pasw +"');");
        prep.executeUpdate();
        prep.close();
    }

    // Password checker
    public ResultSet checkPasw(String username) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT username, pasw FROM ctdtable WHERE username = '" + username + "';");
        return res;
    }

    // Get scores and coins
    // Password checker
    public ResultSet getScoresAndCoins(String username) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT scores, coins FROM ctdtable WHERE username = '" + username + "';");
        return res;
    }

    // Weapon checker
    public ResultSet getWeapons(String username) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT eraser, paintSpray FROM ctdtable WHERE username = '" + username + "';");
        return res;
    }

    // Save High Scores
    public void saveHighScoresAndCoins (String username, int scores, int coins) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        String query = "UPDATE ctdtable set scores = " + scores + ", coins = " + coins + " WHERE username = '" + username + "';";
        try (PreparedStatement prep = con.prepareStatement( query)) {
            prep.executeUpdate();
            prep.close();
        }
    }
    // Save Eraser
    public void saveEraser (String username, int eraser) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        String query = "UPDATE ctdtable set eraser = " + eraser + " WHERE username = '" + username + "';";
        try (PreparedStatement prep = con.prepareStatement( query)) {
            prep.executeUpdate();
            prep.close();
        }
    }

    // Save Paint Spray
    public void savePaintSpray (String username, int ps) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        String query = "UPDATE ctdtable set paintSpray = " + ps + " WHERE username = '" + username + "';";
        try (PreparedStatement prep = con.prepareStatement( query)) {
            prep.executeUpdate();
            prep.close();
        }
    }

    // Get Scores
    public ResultSet search(String username) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT scores FROM ctdtable WHERE (username = '" + username + "' COLLATE NOCASE);");
        return res;
    }

    // Update Guest account
    public void updateGuestAccount (String username) throws ClassNotFoundException, SQLException {
        if (con == null) {
            getConnection();
        }
        String query = "UPDATE ctdtable set scores = " + 0 + " WHERE username = '" + username + "';";
        try (PreparedStatement prep = con.prepareStatement( query)) {
            prep.executeUpdate();
            prep.close();
        }
    }
}
