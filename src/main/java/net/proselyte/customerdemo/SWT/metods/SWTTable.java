package net.proselyte.customerdemo.SWT.metods;

import lombok.ToString;
import net.proselyte.customerdemo.database.DBManager;
import net.proselyte.customerdemo.model.Customer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import javax.naming.directory.SearchResult;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class SWTTable {

    DBManager dbManager;

    SWTTable(){
        this.dbManager = new DBManager();
    }

    public static int conditionRows;
    public static Map<Integer, Combo> conditionsAttributes = new HashMap<Integer, Combo>(); //массив атрибутов поиска имя-фамилия и т.д.
    public static Map<Integer, Text> conditionsValues = new HashMap<Integer, Text>();      //массив значений ввода в строке поиска
    public static Map<Integer, Combo> conditionsOperators = new HashMap<Integer, Combo>();  //массив оператовров поиска и-или

    public static String GetCustomersQuery() {                                 //метод постороение запроса в базу данных в соответствии с значениями поиска
        String where = "";
        for (int i = 1; i <= conditionRows; i++) {
            Combo attributeField = conditionsAttributes.get(i);
            String attributeName = "";
            Integer attributeIndex = attributeField.getSelectionIndex();
            if (attributeIndex >= 0) {
                attributeName = attributeField.getItem(attributeIndex);
            }

            Text valueField = conditionsValues.get(i);
            String attributeValue = valueField.getText();

            String attributeOperator = "";
            if (i > 1) {
                Combo operator = conditionsOperators.get(i);
                Integer operatorIndex = operator.getSelectionIndex();
                if (operatorIndex >= 0) {
                    attributeOperator = operator.getItem(operatorIndex);
                }
            }

            if (attributeName.length() == 0 || attributeValue.length() == 0) {
                continue;
            }

            if (attributeName.equals("date_of_birth")  || attributeName.equals("budget") ) {
                where = where + " " + attributeOperator + " (" + attributeName + " = '" + attributeValue + "')";                // вариант поиска по дате и бюджету
            } else {
                where = where + " " + attributeOperator + " ( LOWER (" + attributeName + ") LIKE '" + attributeValue + "%')";   // вариант поиска по части текста
            }
        }

        String query = "SELECT * FROM customers";
        if (where.length() > 0) {
            query = query + " WHERE " + where;
        }
        System.out.println(query);

        return query; // строка запроса SQL
    }

    public static void SetPlaceholder(Combo field, String text) { // метод для подстановки текста по умолчанию (placeholder)
        field.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent event) {
                if (field.getText().isEmpty()) {
                    int x = 1;
                    int y = 1;
                    event.gc.drawText(text, x, y);
                    field.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY));
                } else field.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
            }
        });
    }

    public static void SetTableRows(Table table, ArrayList<Customer> customers) {  // метод подстановки значений в таблицу
        table.removeAll();
        for (Customer customer : customers) {
            TableItem item = new TableItem(table, SWT.NULL);

            item.setText(0, "" + customer.getId());
            item.setText(1, "" + customer.getFirstname());
            item.setText(2, "" + customer.getLastname());
            item.setText(3, "" + customer.getDate_of_birth());
            item.setText(4, "" + customer.getAddress());
            item.setText(5, "" + customer.getBudget());
        }
    }

    public void initFilters(Group group, Table table) {  // метод обработки нажатия кнопки поиска
        Button find = new Button(group, SWT.NONE);
        find.setText("поиск");
        find.setBounds(10, 20, 110, 30);
        find.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                String query = GetCustomersQuery();
                try {
                    SetTableRows(table, dbManager.GetAllParam(query));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        Button add = new Button(group, SWT.NONE);   //кнопка добавления полей и условия поиска
        add.setText("добавить условие");
        add.setBounds(300, 20, 140, 30);
        add.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                addConditionRow(group);
            }
        });
    }

    public static Table initTable(Group group) {    //метод инициализации таблицы вывода
        Table table = new Table(group, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        String[] titles = {"Номер", "Имя", "Фамилия", "Дата рождения", "Адрес", "Бюджет"};

        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            TableColumn column = new TableColumn(table, SWT.NULL);
            column.setText(titles[loopIndex]);
            column.setResizable(true);
            column.setWidth(300);

        }
        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            table.getColumn(loopIndex).pack();
        }
        table.setBounds(10, 20, 980, 700);
        return table;
    }

    public static void addConditionRow(Group group) { // метод добавления полей и условия поиска
        conditionRows += 1;
        Group rowGroup = new Group(group, SWT.SHADOW_ETCHED_IN);
        rowGroup.setLocation(10, 60 + (conditionRows - 1) * 60);
        rowGroup.setSize(430, 55);
        rowGroup.setText("Условие: " + conditionRows);

        if (conditionRows > 1) {
            Combo attributeOperator = new Combo(rowGroup, SWT.DROP_DOWN);
            attributeOperator.setBounds(10, 20, 60, 20);
            String[] itemsOperator1 = new String[]{"AND", "OR",};
            attributeOperator.setItems(itemsOperator1);
            attributeOperator.select(0);
            conditionsOperators.put(conditionRows, attributeOperator);
        }

        Combo attributeName = new Combo(rowGroup, SWT.DROP_DOWN);
        attributeName.setBounds(100, 20, 120, 20);
        String[] items1 = new String[]{"first_name", "last_name", "date_of_birth", "budget"};
        attributeName.setItems(items1);
        SetPlaceholder(attributeName, "Выберете поле...");
        conditionsAttributes.put(conditionRows, attributeName);

        Text attributeValue = new Text(rowGroup, SWT.BORDER);
        attributeValue.setBounds(230, 20, 120, 23);
        conditionsValues.put(conditionRows, attributeValue);

        Button remove = new Button(rowGroup, SWT.NONE);
        remove.setText("X");
        remove.setBounds(390, 20, 25, 25);
        remove.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                removeConditionRow(group, conditionRows);
            }
        });
    }

    public static void removeConditionRow(Group group, Integer conditionRows) { // тут будет метод удаления полей и условия поиска

    }


    public static void main(String[] args) {

        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("SWT System)");

        Group resultGroup = new Group(shell, SWT.SHADOW_ETCHED_IN);
        resultGroup.setLocation(10, 0);
        resultGroup.setSize(1000, 730);
        resultGroup.setText("Результат");
        Table table = initTable(resultGroup);

        SWTTable swtTable = new SWTTable();
        Group filtersGroup = new Group(shell, SWT.SHADOW_ETCHED_IN);
        filtersGroup.setLocation(1020, 0);
        filtersGroup.setSize(455, 730);
        filtersGroup.setText("Фильтры");
        swtTable.initFilters(filtersGroup, table);
        //       addConditionsFields(filtersGroup);

        shell.setSize(1500, 800);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

}