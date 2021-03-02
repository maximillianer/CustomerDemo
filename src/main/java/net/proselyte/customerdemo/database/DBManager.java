package net.proselyte.customerdemo.database;

import net.proselyte.customerdemo.model.Customer;
import org.eclipse.swt.internal.C;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// ТЕСТОВЫЙ ВАРИАНТ

public class DBManager {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static ArrayList<Customer> getAllCustomers(){ // отображение всех работников
        ArrayList<Customer> allCustomers = new ArrayList<>();

        try{
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con= DriverManager.getConnection(
                    url,"postgres","6503");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstname(rs.getString("first_name"));
                customer.setLastname(rs.getString("Last_name"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                customer.setAddress(rs.getString("address"));
                customer.setBudget(BigDecimal.valueOf(rs.getInt("budget")));
                allCustomers.add(customer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return allCustomers;
    }

    public static ArrayList<Customer> getAllFirstNames(){ // отображение всех имён
        ArrayList<Customer> allFirstNames = new ArrayList<>();

        try{
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con= DriverManager.getConnection(
                    url,"postgres","6503");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers where first_name = '"+br+"'");
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirstname(rs.getString("first_name"));
                allFirstNames.add(customer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allFirstNames;
    }

    public static ArrayList<Customer> getAllLastNames(){ // отображение всех фамилий
        ArrayList<Customer> allLastNames = new ArrayList<>();

        try{
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url,"postgres","6503");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers.public.customers where last_name = '"+br+"'");
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setLastname(rs.getString("last_name"));
                allLastNames.add(customer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allLastNames;
    }

    public static ArrayList<Customer> getAlldateOfBirth(){ // отображение всх дат рождений
        ArrayList<Customer> alldateOfBirth = new ArrayList<>();

        try{
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url,"postgres","6503");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers where date_of_birth = '"+br+"'");
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setDate_of_birth(rs.getString("date_of_birth"));
                alldateOfBirth.add(customer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return alldateOfBirth;
    }


    public static ArrayList<Customer> getAllBudget(){ // отображение всх зарплат
        ArrayList<Customer> allBudget = new ArrayList<>();

        try{
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url,"postgres","6503");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers where budget = '"+br+"'");
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setBudget(rs.getInt("budget"));
                allBudget.add(customer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allBudget;
    }

    public static void createCustomer(String first_name, String last_name, String date_of_birth, String address, int budget){

        try{
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url,"postgres","6503");
            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO `customers` (`first_name`, `last_name`, `date_of_birth`, `address`, `budget`) VALUES ('"+first_name+"', '"+last_name+"', '"+date_of_birth+"', '"+address+"', '"+budget+"')");

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void deleteCustomer(String id) {
        try{
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url,"postgres","6503");
            Statement stmt = con.createStatement();
            stmt.execute("UPDATE `customers` WHERE (`id` = '"+id+"');");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
