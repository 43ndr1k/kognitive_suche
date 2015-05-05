package de.cpdfjarrtm.pdfborx.db.model;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.cpdfjarrtm.pdfborx.pdf.load.LoadController;

/**
 * Class to create and handle the connection to the database.
 * 
 * @author Julian Goetz
 */
public class DatabaseLink {
    
    private Connection connect;
    private String path;
    private boolean connected;
    
    public DatabaseLink(){
        connected = false;
    } 
    
    /**
     * Establishes the connection. The database will be created if it doesn't exist yet.
     * @return The connection object for further use of the database.
     */
    public Connection establishConnection(){
        try {
            if (checkPath() == true){
                Class.forName("org.h2.Driver");
                connect = DriverManager.getConnection("jdbc:h2:"+path+"database", "sa", "");
                connected = true;
                return connect;
            }
            //@TODO: Logger einbinden
        } catch (ClassNotFoundException e) {
            System.out.println("H2 driver not found.");
            Logger.getLogger(DatabaseLink.class.getName())
            .log(Level.SEVERE, null, e);
            return null;
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            Logger.getLogger(DatabaseLink.class.getName())
            .log(Level.SEVERE, null, e);
            return null;
        } catch (NullPointerException e) {
            Logger.getLogger(DatabaseLink.class.getName())
            .log(Level.SEVERE, null, e);
            return null;
        }
        return null;
    }
    
    /**
     * Closes the connection to the database.
     * @return True, if the connection was closed successfully. False, if not.
     */
    public boolean closeConnection(){
        if (connected == true){
            try {
                connect.close();
            } catch (SQLException e) {
                Logger.getLogger(DatabaseLink.class.getName())
                .log(Level.SEVERE, null, e);
                return false;
            }
            System.out.println("Connection to database was closed.");
            return true;
        }
        else{
            System.out.println("Connection to database can not be closed, if there is none.");
            return false;
        }
    }

    /**
     * Sets the path where the database file will be stored.
     * 
     * @param path The path of the database file.
     */
    public void setPath(String path) {
        this.path = path;
    }
    
    /**
     * Returns the path of the database file.
     * 
     * @return Path of the database file.
     */
    public String getPath(){
        return path;
    }

    private boolean checkPath(){
        if (path != null){
            File db = new File(path);
            try {
                db.getCanonicalPath();
                return true;
            } catch (IOException e) {
                System.out.println("Path can not be accessed.");
                Logger.getLogger(DatabaseLink.class.getName())
                .log(Level.SEVERE, null, e);
            }
        }
        else{
            System.out.println("Path can not be null.");
        }
        return false;
    }
    
    
}
