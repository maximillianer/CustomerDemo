package net.proselyte.customerdemo.database;

import java.util.HashMap;
import java.util.Map;

public class DBArrays {
    public static Map<String, String> sqlCommands = new HashMap<>();// команды поиска

    public static String[] SQLOperators = new String[] {
            "AND", "OR",
    };

    public static String[] SQLMathematicOperators = new String[]{
            ">", "<", ">=", "<=", "=", "!=", "BETWEEN"
    };

}
