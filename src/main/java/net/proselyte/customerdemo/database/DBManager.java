package net.proselyte.customerdemo.database;

import net.proselyte.customerdemo.SWT.metods.mySWTApplication;
import net.proselyte.customerdemo.model.Customer;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


// ТЕСТОВЫЙ ВАРИАНТ

public class DBManager {


    public static ArrayList<Customer> getAllCustomers() { // отображение всех работников
        ArrayList<Customer> allCustomers = new ArrayList<>();

        try {
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url, "postgres", "6503");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
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

    public static ArrayList<Customer> getAllFirstNames(String textFirstName) { // отображение всех имён
        ArrayList<Customer> allFirstNames = new ArrayList<>();

        try {
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url, "postgres", "6503");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE first_name = '" + textFirstName + "'");
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

    public static ArrayList<Customer> getAllLastNames(String textLastName) { // отображение всех фамилий
        ArrayList<Customer> allLastNames = new ArrayList<>();

        try {
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url, "postgres", "6503");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers.public.customers WHERE last_name = '" + textLastName + "'");
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

    public static ArrayList<Customer> getAlldateOfBirth(String date) { // отображение всх дат рождений
        ArrayList<Customer> alldateOfBirth = new ArrayList<>();


        try {
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url, "postgres", "6503");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE date_of_birth = '" + date + "'");
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


    public static ArrayList<Customer> getAllBudget(String textBudget) { // отображение всх зарплат
        ArrayList<Customer> allBudget = new ArrayList<>();

        try {
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url, "postgres", "6503");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers where budget = '" + textBudget + "'");
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

    public static void createCustomer(String first_name, String last_name, String date_of_birth, String address, int budget) {

        try {
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url, "postgres", "6503");
            Statement stmt = con.createStatement();
            stmt.execute("INSERT INTO `customers` (`first_name`, `last_name`, `date_of_birth`, `address`, `budget`) VALUES ('" + first_name + "', '" + last_name + "', '" + date_of_birth + "', '" + address + "', '" + budget + "')");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void deleteCustomer(String id) { // удалить сотрудника
        try {
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url, "postgres", "6503");
            Statement stmt = con.createStatement();
            stmt.execute("UPDATE `customers` WHERE (`id` = '" + id + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Customer> GetAllParam(String textFirstName, String textLastName, String textBudget, Boolean operator) { // поиск по всем параметрам с учётом оператора "и" "или"
        ArrayList<Customer> allParam = new ArrayList<>();

        try {
            String url = "jdbc:postgresql://localhost:5432/customers";
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    url, "postgres", "6503");
            Statement stmt = con.createStatement();
            if (operator && textFirstName != null || textLastName != null || textBudget != null) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE first_name = '" + textFirstName + "' AND last_name = '"
                        + textLastName + "' AND budget = '" + textBudget + "' ");
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
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allParam;
    }
}


//это тестовый метод который я создал только для проверки работы подключения каждого метода.  потом удалю

//    public static void main(String[] args) {
//
//        ArrayList<Customer> allLastNames = new ArrayList<>();
//        Scanner in = new Scanner(System.in);
//        System.out.print("Input a last_name: ");
//        String inName = in.nextLine();
//        in.close();
//
//
//        try{
//            String url = "jdbc:postgresql://localhost:5432/customers";
//            Class.forName("org.postgresql.Driver");
//            Connection con = DriverManager.getConnection(
//                    url,"postgres","6503");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM customers.public.customers where last_name = '"+inName+"'");
//            while (rs.next()){
//                Customer customer = new Customer();
//                customer.setId(rs.getInt("id"));
//                customer.setFirstname(rs.getString("first_name"));
//                customer.setLastname(rs.getString("Last_name"));
//                customer.setDate_of_birth(rs.getString("date_of_birth"));
//                customer.setAddress(rs.getString("address"));
//                customer.setBudget(BigDecimal.valueOf(rs.getInt("budget")));
//                allLastNames.add(customer);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("value:" + allLastNames);
//
//    }
//}
