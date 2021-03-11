package net.proselyte.customerdemo.database;

import net.proselyte.customerdemo.model.Customer;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class DBManager {

    DBConnection db;

    public DBManager() { // установка подключения к БД
        this.db = new DBConnection("jdbc:postgresql://localhost:5432/customers", "org.postgresql.Driver", "postgres", "6503");
    }

    public ArrayList<Customer> GetAllParam(String query) throws ClassNotFoundException, SQLException { // основной метод поиска
        ArrayList<Customer> allParam = new ArrayList<>();

        try {
            ResultSet rs = this.db.execute(query);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstname(rs.getString("first_name"));
                customer.setLastname(rs.getString("Last_name"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                customer.setAddress(rs.getString("address"));
                customer.setBudget(BigDecimal.valueOf(rs.getInt("budget")));
                allParam.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allParam;
    }
}



