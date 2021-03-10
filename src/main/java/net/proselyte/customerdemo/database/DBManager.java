package net.proselyte.customerdemo.database;

import net.proselyte.customerdemo.model.Customer;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class DBManager implements DBManagerInterface {

    DBConnection db;

    public DBManager() {
        this.db = new DBConnection("jdbc:postgresql://localhost:5432/customers", "org.postgresql.Driver", "postgres", "6503");
    }


    public ArrayList<Customer> getAllCustomers() { // отображение всех работников
        ArrayList<Customer> allCustomers = new ArrayList<>();

        try {
            ResultSet rs = this.db.execute("SELECT * FROM customers");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstname(rs.getString("first_name"));
                customer.setLastname(rs.getString("Last_name"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                customer.setAddress(rs.getString("address"));
                customer.setBudget(BigDecimal.valueOf(rs.getInt("budget")));
                allCustomers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allCustomers;
    }

    public ArrayList<Customer> getAllFirstNames(String textFirstName) { // отображение всех имён
        ArrayList<Customer> allFirstNames = new ArrayList<>();

        try {
            ResultSet rs = this.db.execute("SELECT * FROM customers WHERE first_name = '" + textFirstName + "'");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstname(rs.getString("first_name"));
                customer.setLastname(rs.getString("Last_name"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                customer.setAddress(rs.getString("address"));
                customer.setBudget(BigDecimal.valueOf(rs.getInt("budget")));
                allFirstNames.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allFirstNames;
    }

    public ArrayList<Customer> getAllLastNames(String textLastName) { // отображение всех фамилий
        ArrayList<Customer> allLastNames = new ArrayList<>();

        try {
            ResultSet rs = this.db.execute("SELECT * FROM customers.public.customers WHERE last_name = '" + textLastName + "'");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstname(rs.getString("first_name"));
                customer.setLastname(rs.getString("Last_name"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                customer.setAddress(rs.getString("address"));
                customer.setBudget(BigDecimal.valueOf(rs.getInt("budget")));
                allLastNames.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLastNames;

    }

    public ArrayList<Customer> getAlldateOfBirth(String date) { // отображение всх дат рождений
        ArrayList<Customer> alldateOfBirth = new ArrayList<>();


        try {
            ResultSet rs = this.db.execute("SELECT * FROM customers WHERE date_of_birth = '" + date + "'");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstname(rs.getString("first_name"));
                customer.setLastname(rs.getString("Last_name"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                customer.setAddress(rs.getString("address"));
                customer.setBudget(BigDecimal.valueOf(rs.getInt("budget")));
                alldateOfBirth.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alldateOfBirth;
    }


    public ArrayList<Customer> getAllBudget(String textBudget) { // отображение всх зарплат
        ArrayList<Customer> allBudget = new ArrayList<>();

        try {
            ResultSet rs = this.db.execute("SELECT * FROM customers where budget = '" + textBudget + "'");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstname(rs.getString("first_name"));
                customer.setLastname(rs.getString("Last_name"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                customer.setAddress(rs.getString("address"));
                customer.setBudget(BigDecimal.valueOf(rs.getInt("budget")));
                allBudget.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allBudget;

    }

    public void createCustomer(String first_name, String last_name, String date_of_birth, String address, int budget) {

        try {
            ResultSet rs = this.db.execute("INSERT INTO `customers` (`first_name`, `last_name`, `date_of_birth`, `address`, `budget`) VALUES ('" + first_name + "', '" + last_name + "', '" + date_of_birth + "', '" + address + "', '" + budget + "')");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteCustomer(String id) { // удалить сотрудника
        try {
            ResultSet rs = this.db.execute("UPDATE `customers` WHERE (`id` = '" + id + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> GetAllParam(String query) throws ClassNotFoundException, SQLException { // поиск по всем параметрам с учётом оператора "и" "или"
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



