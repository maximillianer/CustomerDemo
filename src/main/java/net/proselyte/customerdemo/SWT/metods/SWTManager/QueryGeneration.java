package net.proselyte.customerdemo.SWT.metods.SWTManager;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import java.util.HashMap;
import java.util.Map;

public class QueryGeneration {
//
//    public static int conditionRows;
//    public static Map<Integer, Combo> conditionsAttributes = new HashMap<Integer, Combo>(); //массив атрибутов поиска имя-фамилия и т.д.
//    public static Map<Integer, Text> conditionsValues = new HashMap<Integer, Text>();      //массив значений ввода в строке поиска
//    public static Map<Integer, Combo> conditionsOperators = new HashMap<Integer, Combo>();  //массив оператовров поиска и-или
//
//    public static String GetCustomersQuery() {                                 //метод постороение запроса в базу данных в соответствии с значениями поиска
//       String where = "";
//       for (int i = 1; i <= conditionRows; i++) {
//           Combo attributeField = conditionsAttributes.get(i);
//           String attributeName = "";
//           Integer attributeIndex = attributeField.getSelectionIndex();
//           if (attributeIndex >= 0) {
//               attributeName = attributeField.getItem(attributeIndex);
//           }
//
//           Text valueField = conditionsValues.get(i);
//           String attributeValue = valueField.getText();
//
//           String attributeOperator = "";
//           if (i > 1) {
//               Combo operator = conditionsOperators.get(i);
//               Integer operatorIndex = operator.getSelectionIndex();
//               if (operatorIndex >= 0) {
//                   attributeOperator = operator.getItem(operatorIndex);
//               }
//           }
//
//           if (attributeName.length() == 0 || attributeValue.length() == 0) {
//               continue;
//           }
//
//           if (attributeName.equals("date_of_birth")  || attributeName.equals("budget") ) {
//               where = where + " " + attributeOperator + " (" + attributeName + " = '" + attributeValue + "')";                // вариант поиска по дате и бюджету
//           } else {
//               where = where + " " + attributeOperator + " ( LOWER (" + attributeName + ") LIKE '" + attributeValue + "%')";   // вариант поиска по части текста
//           }
//       }
//
//       String query = "SELECT * FROM customers";
//       if (where.length() > 0) {
//           query = query + " WHERE " + where;
//       }
//       System.out.println(query);
//
//       return query; // строка запроса SQL
//    }
}