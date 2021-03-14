package net.proselyte.customerdemo.filters;

import net.proselyte.customerdemo.database.QueryBuilder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FiltersForm {
    int conditionGroupCount = 0;
    public static Map<Integer, Group> conditionGroup = new HashMap<>();

    public static Map<Integer, Integer> subConditionGroupCount = new HashMap<>();
    public static HashMap<Integer, HashMap<Integer, Group>> subConditionGroup = new HashMap<Integer, HashMap<Integer, Group>>();


    public void addConditionGroup(Group containerGroup) { // добавить поле условия
        conditionGroupCount++;
        int rowID = conditionGroupCount;
        int orderId = conditionGroup.size();
        Group rowGroup = new Group(containerGroup, SWT.SHADOW_ETCHED_IN);
        rowGroup.setData("ID", rowID);
        rowGroup.setLocation(10, 60 + orderId * 60);
        rowGroup.setSize(630, 55);
        rowGroup.setText("Условие: " + rowID);
        conditionGroup.put(rowID, rowGroup);

        initCondition(rowGroup, orderId);
        initAttribute(rowGroup);
        initOperator(rowGroup);
        initValue(rowGroup);
        initButtonSub(containerGroup, rowGroup, rowID, orderId);
        initButtonRemove(containerGroup, rowGroup, rowID);
    }

    public void removeConditionGroup(Group group, int rowID) {
        conditionGroup.remove(rowID);
        removeConditionGroupItemById(group, rowID);
    }

    public void removeConditionGroupItemById(Group group, int rowID) {
        for (Control ctrl : group.getChildren()) {
            if (ctrl instanceof Group) {
                int id = (int) ctrl.getData("ID");
                if (id == rowID) {
                    ctrl.dispose();
                    break;
                }
            }
        }
    }

    public void initCondition(Group rowGroup, int orderId) {
        if (orderId > 0) {
            Combo input = new Combo(rowGroup, SWT.DROP_DOWN);
            input.setBounds(10, 20, 60, 20);
            String[] itemsOperator1 = new String[]{"AND", "OR",};
            input.setItems(itemsOperator1);
            input.select(0);
            input.setData("ID", "condition");
        }
    }


    public void initAttribute(Group rowGroup) {
        Combo input = new Combo(rowGroup, SWT.DROP_DOWN);
        input.setBounds(80, 20, 120, 20);
        String[] values = new String[]{"first_name", "last_name", "date_of_birth", "budget"};
        input.setItems(values);
        input.setData("ID", "attribute");
    }

    public void initOperator(Group rowGroup) {
        Combo input = new Combo(rowGroup, SWT.DROP_DOWN);
        input.setBounds(210, 20, 60, 20);
        String[] values = new String[]{">=", "<=", "=", "!=", "between"};
        input.setItems(values);
        input.setData("ID", "operator");
    }

    public void initValue(Group rowGroup) {
        Text input = new Text(rowGroup, SWT.BORDER);
        input.setBounds(280, 20, 120, 23);
        input.setData("ID", "value");
    }

    public Button initButtonSub(Group containerGroup, Group rowGroup, int rowID, int orderID) {
        Button btn = new Button(rowGroup, SWT.NONE);
        btn.setText("+");
        btn.setBounds(570, 20, 25, 25);
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                addSubConditionGroup(containerGroup, rowGroup, rowID, orderID);
                render(containerGroup);
            }
        });
        return btn;
    }

    public Button initButtonRemove(Group containerGroup, Group rowGroup, int rowID) {
        Button btn = new Button(rowGroup, SWT.NONE);
        btn.setText("X");
        btn.setBounds(595, 20, 25, 25);
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                removeConditionGroup(containerGroup, rowID);
                render(containerGroup);
            }
        });
        return btn;
    }

    public void addSubConditionGroup(Group containerGroup, Group rowGroup, int rowID, int orderID) { // добавить поле условия
        int subID = 0;
        if (subConditionGroupCount.containsKey(rowID)) {
            subID = subConditionGroupCount.get(rowID);
        }
        subID++;

        int orderId = 0;
        if (subConditionGroup.containsKey(rowID)) {
            orderId = subConditionGroup.get(rowID).size();
        }
        Group subGroup = new Group(rowGroup, SWT.SHADOW_ETCHED_IN);
        subGroup.setData("ID", rowID + "-" + subID);
        subGroup.setLocation(80, (55 + orderId * 55));
        subGroup.setSize(540, 55);
        subGroup.setText("Под-условие: " + subID);

        if (subConditionGroup.containsKey(rowID)) {
            subConditionGroup.get(rowID).put(subID, subGroup);
        } else {
            HashMap<Integer, Group> map = new HashMap<Integer, Group>();
            map.put(subID, subGroup);
            subConditionGroup.put(rowID, map);
        }
        subConditionGroupCount.put(rowID, subID);

        initCondition(subGroup, orderId+1);
        initAttribute(subGroup);
        initOperator(subGroup);
        initValue(subGroup);
        initButtonSubRemove(containerGroup, rowGroup, rowID, subGroup, subID);
    }

    public Button initButtonSubRemove(Group containerGroup, Group rowGroup, int rowID, Group subGroup, int subID) {
        Button btn = new Button(subGroup, SWT.NONE);
        btn.setText("X");
        btn.setBounds(505, 20, 25, 25);
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                removeSubConditionGroup(rowGroup, rowID, subID);
                render(containerGroup);
            }
        });
        return btn;
    }


    public void removeSubConditionGroup(Group group, int rowID, int subID) {
        subConditionGroup.get(rowID).remove(subID);
        removeSubConditionGroupItemById(group, rowID, subID);
    }

    public void removeSubConditionGroupItemById(Group group, int rowID, int subID) {
        for (Control ctrl : group.getChildren()) {
            if (ctrl instanceof Group) {
                String id = (String) ctrl.getData("ID");
                if (id.equals(rowID + "-" + subID)) {
                    ctrl.dispose();
                    break;
                }
            }
        }
    }


    public void render(Group containerGroup) {
        int rowY = 0;
        for (Map.Entry<Integer, Group> entry : conditionGroup.entrySet()) {
            Group group = entry.getValue();

            int rowID = (int) group.getData("ID");
            int countSub = 0;

            if (subConditionGroup.containsKey(rowID)) {
                HashMap<Integer, Group> subGroups = subConditionGroup.get(rowID);
                int index = 0;
                countSub = subGroups.size();
                for (Map.Entry<Integer, Group> entry2 : subGroups.entrySet()) {
                    Group subGroup = entry2.getValue();
                    subGroup.setLocation(80, (60 + index * 60));
                    index++;
                }
            }
            int height = 55 + 60 * countSub;
            if (countSub > 0) {
                height += 10;
            }
            group.setSize(630, height);

            rowY += 60;
            group.setLocation(10, rowY);
            rowY += height - 55;
        }
    }

    public String getSql() {
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
        }

        String query = "SELECT * FROM customers";
        if (where.length() > 0) {
            query = query + " WHERE " + where;
        }
      System.out.println(query);

        return query; // строка запроса SQL
    }


    public Map<String, String> getCondition(Group group) {
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

    public String prepareCondition(Map.Entry<Integer, Group> entry, String subQuery) {
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

    public String sqlBuilder(Map<String, String> map) {
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