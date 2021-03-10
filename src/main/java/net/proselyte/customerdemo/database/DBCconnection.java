package net.proselyte.customerdemo.database;

import java.sql.*;

class DBConnection {

    private String url; //"jdbc:postgresql://localhost:5432/customers";
    private String driver; // "org.postgresql.Driver";
    private String user; // "postgres";
    private String password;//"6503";
    private Statement stmt;

    private boolean isOpen = false;

    public DBConnection(String url, String driver, String user, String password) {
        this.url = url;
        this.driver = driver;
        this.user = user;
        this.password = password;

    }

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

    public ResultSet execute (String query) throws SQLException, ClassNotFoundException {
        this.open();
        return stmt.executeQuery(query);
    }
}
