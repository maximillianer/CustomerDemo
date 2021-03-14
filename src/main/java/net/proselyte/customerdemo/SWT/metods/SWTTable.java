package net.proselyte.customerdemo.SWT.metods;

import lombok.ToString;
import net.proselyte.customerdemo.database.DBManager;
import net.proselyte.customerdemo.database.QueryBuilder;
import net.proselyte.customerdemo.database.QueryBuilder;
import net.proselyte.customerdemo.filters.FiltersForm;
import net.proselyte.customerdemo.model.Customer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import javax.naming.directory.SearchResult;
import java.io.InputStream;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class SWTTable {

    DBManager dbManager;

    public SWTTable() {
        this.dbManager = new DBManager();
    }

    public static FiltersForm filters = new FiltersForm();

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
                find.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
                String query = filters.getSql();
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
        add.setBounds(500, 20, 140, 30);
        add.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                find.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
             //   addConditionRow(group);
                filters.addConditionGroup(group);
                filters.render(group);
            }
        });
    }

    public static Table initTable(Group group) {     //метод инициализации таблицы вывода
        Table table = new Table(group, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);
        String[] titles = {"Номер / Id", "Имя / First name", "Фамилия / Last name", "Дата рождения / Date of birth", "Адрес места жительства / Address", "Бюджет / Budget"};

        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            TableColumn column = new TableColumn(table, SWT.NULL);
            column.setText(titles[loopIndex]);
            column.setResizable(true);
            column.setWidth(500);

        }
        for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
            table.getColumn(loopIndex).pack();
        }

        table.setBounds(10, 20, 980, 700);
        return table;
    }

    public static void addConditionRow(Group group) { // метод добавления полей и условия поиска
        QueryBuilder.conditionRows += 1;
        Group rowGroup = new Group(group, SWT.SHADOW_ETCHED_IN);
        rowGroup.setLocation(10, 60 + (QueryBuilder.conditionRows - 1) * 60);
        rowGroup.setSize(630, 55);
        rowGroup.setText("Условие: " + QueryBuilder.conditionRows);

        if (QueryBuilder.conditionRows > 1) {
            Combo attributeOperator = new Combo(rowGroup, SWT.DROP_DOWN);
            attributeOperator.setBounds(10, 20, 60, 20);
            String[] itemsOperator1 = new String[]{"AND", "OR",};
            attributeOperator.setItems(itemsOperator1);
            attributeOperator.select(0);
            QueryBuilder.conditionsOperators.put(QueryBuilder.conditionRows, attributeOperator);

            Button add2 = new Button(group, SWT.NONE);   //кнопка добавления промежуточных полей и условия поиска
            add2.setText("добавить промежуточное условие");
            add2.setBounds(300, 20, 200, 30);
            add2.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent arg0) {
                    addConditionRow(group);
                }
            });
        }

        Combo attributeName = new Combo(rowGroup, SWT.DROP_DOWN);
        attributeName.setBounds(80, 20, 100, 20);
        String[] items1 = new String[]{"first_name", "last_name", "date_of_birth", "budget"};
        attributeName.setItems(items1);
        SetPlaceholder(attributeName, "поиск по");
        QueryBuilder.conditionsAttributes.put(QueryBuilder.conditionRows, attributeName);


        Button add = new Button(rowGroup, SWT.NONE);
        add.setText("OK");
        add.setBounds(190, 20, 25, 25);
        add.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                String it = attributeName.getText();
                if (it.equals("first_name") || it.equals("last_name")) {
                    Text attributeValue = new Text(rowGroup, SWT.BORDER);
                    attributeValue.setBounds(230, 20, 120, 23);
                    QueryBuilder.conditionsValues.put(QueryBuilder.conditionRows, attributeValue);
                } else if (it.equals("date_of_birth")) {
                    Combo yearValue = new Combo(rowGroup, SWT.DROP_DOWN);
                    yearValue.setBounds(230, 20, 45, 23);
                    String[] itemsYears = new String[]{"1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995",
                            "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
                            "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
                    yearValue.setItems(itemsYears);
                    SetPlaceholder(yearValue, "Год");
                    QueryBuilder.conditionsAttributes.put(QueryBuilder.conditionRows, yearValue);


                    Combo monthValue = new Combo(rowGroup, SWT.DROP_DOWN);
                    monthValue.setBounds(280, 20, 40, 23);
                    String[] itemsMonths = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
                    monthValue.setItems(itemsMonths);
                    SetPlaceholder(monthValue, "м");
                    QueryBuilder.conditionsAttributes.put(QueryBuilder.conditionRows, monthValue);


                    Combo daysValue = new Combo(rowGroup, SWT.DROP_DOWN);
                    daysValue.setBounds(325, 20, 40, 23);
                    String[] itemsDays = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "24", "25", "26", "27", "28", "29", "30", "31"};
                    daysValue.setItems(itemsDays);
                    SetPlaceholder(daysValue, "ч");
                    QueryBuilder.conditionsAttributes.put(QueryBuilder.conditionRows, daysValue);


                    Button period = new Button(rowGroup, SWT.NONE);
                    period.setText("в период по");
                    period.setBounds(370, 20, 75, 25);
                    period.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent arg0) {
                            Combo yearValue = new Combo(rowGroup, SWT.DROP_DOWN);
                            yearValue.setBounds(450, 20, 45, 23);
                            String[] itemsYears1 = new String[]{"1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995",
                                    "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
                                    "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
                            yearValue.setItems(itemsYears1);
                            SetPlaceholder(yearValue, "Год");

                            Combo monthValue = new Combo(rowGroup, SWT.DROP_DOWN);
                            monthValue.setBounds(505, 20, 40, 23);
                            String[] itemsMonths1 = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
                            monthValue.setItems(itemsMonths1);
                            SetPlaceholder(monthValue, "м");

                            Combo daysValue = new Combo(rowGroup, SWT.DROP_DOWN);
                            daysValue.setBounds(550, 20, 40, 23);
                            String[] itemsDays1 = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                                    "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "24", "25", "26", "27", "28", "29", "30", "31"};
                            daysValue.setItems(itemsDays1);
                            SetPlaceholder(daysValue, "ч");

                            String searchOfDateOfBirthAfter = yearValue + "-" + monthValue + "-" + daysValue;
                        }
                    });

                    // QueryBuilder.conditionsValues.put(QueryBuilder.conditionRows, searchOfDateOfBirth);

                } else if (it.equals("budget")) {
                    Text attributeValuebudget = new Text(rowGroup, SWT.BORDER);
                    attributeValuebudget.setBounds(310, 20, 120, 23);
                    QueryBuilder.conditionsValues.put(QueryBuilder.conditionRows, attributeValuebudget);

                    Combo attributeOperatorMoreAndLess = new Combo(rowGroup, SWT.DROP_DOWN);
                    attributeOperatorMoreAndLess.setBounds(230, 20, 60, 20);
                    String[] itemsOperator1 = new String[]{"<=", ">=", "="};
                    attributeOperatorMoreAndLess.setItems(itemsOperator1);
                    attributeOperatorMoreAndLess.select(0);
                    QueryBuilder.conditionsOperators.put(QueryBuilder.conditionRows, attributeOperatorMoreAndLess);

                }
            }
        });

        Button remove = new Button(rowGroup, SWT.NONE);
        remove.setText("X");
        remove.setBounds(595, 20, 25, 25);
        remove.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {

            }
        });
    }

    private static void addUnderConditionRow(Group group, int conditionRows1) { // добавление подусловия
        conditionRows1 += 1;
        Group rowGroup = new Group(group, SWT.SHADOW_ETCHED_IN);
        rowGroup.setLocation(10, 60 + (conditionRows1 - 1) * 60);
        rowGroup.setSize(430, 55);
        rowGroup.setText("Условие: " + conditionRows1);

        if (conditionRows1 > 1) {
            conditionRows1 += 1;

            Combo attributeOperator = new Combo(rowGroup, SWT.DROP_DOWN);
            attributeOperator.setBounds(10, 20, 60, 20);
            String[] itemsOperator1 = new String[]{"AND", "OR",};
            attributeOperator.setItems(itemsOperator1);
            attributeOperator.select(0);
            QueryBuilder.conditionsOperators.put(conditionRows1, attributeOperator);

            Combo attributeName = new Combo(rowGroup, SWT.DROP_DOWN);
            attributeName.setBounds(100, 20, 120, 20);
            String[] items1 = new String[]{"first_name", "last_name", "date_of_birth", "budget"};
            attributeName.setItems(items1);
            SetPlaceholder(attributeName, "Выберете поле...");
            QueryBuilder.conditionsAttributes.put(conditionRows1, attributeName);

            Text attributeValue = new Text(rowGroup, SWT.BORDER);
            attributeValue.setBounds(230, 20, 120, 23);
            QueryBuilder.conditionsValues.put(conditionRows1, attributeValue);

            Button add = new Button(rowGroup, SWT.NONE);
            add.setText("+");
            add.setBounds(360, 20, 25, 25);
            int finalConditionRows1 = conditionRows1;
            add.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent arg0) {
                    addUnderConditionRow(group, finalConditionRows1 - 1);
                }
            });


            Button remove = new Button(rowGroup, SWT.NONE);
            remove.setText("X");
            remove.setBounds(390, 20, 25, 25);
            QueryBuilder.conditionsValues.put(conditionRows1, attributeValue);
            int finalConditionRows = conditionRows1;
            remove.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent arg0) {
                    removeConditionRow(group, finalConditionRows);
                }
            });

        }


    }

    public static void removeConditionRow(Group group, Integer finalConditionRows) { // тут будет метод удаления полей и условия поиска

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
        filtersGroup.setSize(655, 730);
        filtersGroup.setText("Фильтры");
        swtTable.initFilters(filtersGroup, table);
        //       addConditionsFields(filtersGroup);


        shell.setSize(1700, 800);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();

    }


}