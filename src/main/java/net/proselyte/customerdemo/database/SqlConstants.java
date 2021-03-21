package net.proselyte.customerdemo.database;

/**
 * This class encapsulates a database query
 * @author Maxim Eliseev
 * @version 1.0
 */
public class SqlConstants {

    /** field SQL inquiry query to find all customers*/
    public static String ALLCustomers = "SELECT * FROM customers"; //основной SQL  запрос на всех работников

    //операторы
    final static String AND = "AND";
    final static String OR = "OR";
    final static String BETWEEN = "BETWEEN";
    final static String MORE = ">";
    final static String LESS = "<";
    final static String EQUAL = "=";
    final static String LESS_OR_EQUAL = "<=";
    final static String MORE_OR_EQUAL = ">=";
    final static String LIKE = "LIKE";
    final static String LOWER = "LOVER";


}
