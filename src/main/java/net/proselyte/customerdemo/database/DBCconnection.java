package net.proselyte.customerdemo.database;

import java.sql.*;

/**
 * This class stores database connection parameters
 * @author Maxim Eliseev
 * @version 1.0
 */
class DBConnection {

    /**field <strong>url</strong>*/
    private String url; //"jdbc:postgresql://localhost:5432/customers";
    /**field <strong>SQL driver</strong>*/
    private String driver; // "org.postgresql.Driver";
    /**field <strong>user</strong>*/
    private String user; // "postgres";
    /**field <strong>password</strong>*/
    private String password;//"6503";
    /**field <strong>Statement</strong>*/
    private Statement stmt;

    /**boolean field <strong>Open connection to DataBase</strong>*/
    private boolean isOpen = false;

    /**
     * This method connected to Database
     * @param url
     * @param driver
     * @param user
     * @param password
     */
    public DBConnection(String url, String driver, String user, String password) {
        this.url = url;
        this.driver = driver;
        this.user = user;
        this.password = password;

    }

    /**
     * Openning connection to DataBase
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void open () throws SQLException, ClassNotFoundException {
        if (!this.isOpen) {
            try {
               Class.forName(this.driver);
               Connection con = DriverManager.getConnection(this.url, this.user, this.password);
               this.stmt = con.createStatement();
               this.isOpen = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method execute SQL (query)
     * @param query
     * @return stmt.executeQuery(query)
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet execute (String query) throws SQLException, ClassNotFoundException {
        this.open();
        return stmt.executeQuery(query);
    }
}
