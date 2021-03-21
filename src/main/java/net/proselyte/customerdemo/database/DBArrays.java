package net.proselyte.customerdemo.database;

public class DBArrays {
    //   public static Map<String, String> sqlCommands = new HashMap<>();// команды поиска
    //
    public static String[] SQLOperators = new String[]{
            "AND", "OR"
    };

    public static String[] SQLMathematicOperators = new String[]{
            ">", "<", ">=", "<=", "=", "!=", "LIKE", "BETWEEN"
    };

    public static String[] SQLValues = new String[]{
            "first_name", "last_name", "date_of_birth", "budget"
    };

    public static String[] titles = new String[]{
            "Номер / Id", "Имя / First name", "Фамилия / Last name", "Дата рождения / Date of birth", "Адрес места жительства / Address", "Бюджет / Budget"
    };

    public static String[] Years = new String[]{
            "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995",
            "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
            "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"
    };

    public static String[] Months = new String[]{
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"
    };

    public static String[] Days = new String[]{
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "24", "25", "26", "27", "28", "29", "30", "31"
    };
}
