package net.proselyte.customerdemo.filters;


import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import java.util.HashMap;
import java.util.Map;

import static net.proselyte.customerdemo.database.SqlConstants.ALLCustomers;
import static net.proselyte.customerdemo.filters.FiltersForm.conditionGroup;
import static net.proselyte.customerdemo.filters.FiltersForm.subConditionGroup;


public class FilterText {

    public static String getSql() { // полуение строки поиска
        String where = "";
        for (Map.Entry<Integer, Group> entry : conditionGroup.entrySet()) {
            Integer rowId = entry.getKey();
            HashMap<Integer, Group>  subConditions = subConditionGroup.get(rowId);

            String sqlGroup  = "";
            if(subConditions != null){
                for (Map.Entry<Integer, Group> entry2 : subConditions.entrySet()) {
                    String subSql = prepareCondition(entry2, "");
                    sqlGroup  += subSql;
                }
            }


            where += prepareCondition(entry, sqlGroup);
            System.out.println("получаемое условие из окна поиска: " + where);
        }

        String query = ALLCustomers;
        if (where.length() > 0) {
            query = query + " WHERE " + where;
        }
        System.out.println(query);

        return query; // строка запроса SQL
    }

    public static Map<String, String> getCondition(Group group) { // получение условия
        Map<String, String> condition = new HashMap<>();
        for (Control ctrl : group.getChildren()) {
            String key = "";
            String value = "";
            if (ctrl instanceof Combo) {
                key = (String) ctrl.getData("ID");
                Combo field = ((Combo) ctrl);
                Integer selected = field.getSelectionIndex();
                if (selected >= 0) {
                    value = field.getItem(selected);
                }
            } else if (ctrl instanceof Text) {
                key = (String) ctrl.getData("ID");
                value = ((Text) ctrl).getText();
            }

            if (!key.isEmpty() && !value.isEmpty()) {
                condition.put(key, value);
            }
        }

        return condition;
    }

    public static String prepareCondition(Map.Entry<Integer, Group> entry, String subQuery) { // построение строки поиска
        Group group = entry.getValue();
        Map<String, String> condition = getCondition(group);
        String sql = sqlBuilder(condition);

        if(!subQuery.isEmpty()){
            sql =" ( "+sql+")"+subQuery;
        }
        if(sql.isEmpty()){
            return  "";
        }

        String con2 = (String) condition.get("condition");
        if(con2 != null){
            return " "+con2 + " (" + sql + ") ";
        }
        return  " (" + sql + ") ";
    }

    public static String sqlBuilder(Map<String, String> map) { // получение значений для построения строки поиска
        String attribute = map.get("attribute");
        String operator = map.get("operator");
        String value = map.get("value");

        if(attribute == null || operator == null || value == null){
            return  "";
        }

        if(attribute.isEmpty() || operator.isEmpty() || value.isEmpty()){
            return  "";
        }

        return attribute+" "+operator+" '"+value+"'";
    }
}
