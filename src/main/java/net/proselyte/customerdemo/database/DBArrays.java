package net.proselyte.customerdemo.database;

/**
 * @version 1.0
 * @autor Eliseev Maxim
 * This Class stores data arrays for substitution in the search fields of the program window
 */

public class DBArrays {

    /**
     * it is an array of search attribute values "and" & "or"
     */
    public static String[] SQLOperators = new String[]{
            "AND", "OR"
    };

    /**
     * it is an array of search operators values
     */
    public static String[] SQLMathematicOperators = new String[]{
            ">", "<", ">=", "<=", "=", "!=", "LIKE", "BETWEEN"
    };

    /**
     * it is an array of search condition attributes and SQL values
     */
    public static String[] SQLValues = new String[]{
            "first_name", "last_name", "date_of_birth", "budget"
    };

    /**
     * it is an array of titles for Table
     */
    public static String[] titles = new String[]{
            "Номер / Id", "Имя / First name", "Фамилия / Last name", "Дата рождения / Date of birth", "Адрес места жительства / Address", "Бюджет / Budget"
    };

    /**
     * it is an array of search on date of birth containing the <strong>years</strong>
     */
    public static String[] Years = new String[]{
            "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995",
            "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
            "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"
    };

    /**
     * it is an array of search on date of birth containing the <strong>months</strong>
     */
    public static String[] Months = new String[]{
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"
    };

    /**
     * it is an array of search on date of birth containing the <strong>days</strong>
     */
    public static String[] Days = new String[]{
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "24", "25", "26", "27", "28", "29", "30", "31"
    };
}
