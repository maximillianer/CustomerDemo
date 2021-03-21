package net.proselyte.customerdemo.SWT.metods;

import net.proselyte.customerdemo.database.DBManager;
import net.proselyte.customerdemo.filters.FilterText;
import net.proselyte.customerdemo.filters.FiltersForm;
import net.proselyte.customerdemo.model.Customer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.sql.SQLException;
import java.util.ArrayList;

import static net.proselyte.customerdemo.database.DBArrays.titles;

/**
 * This is the class building the system window
 * @author Maxim Eliseew
 * @version 3.0
 */
public class SWTTable {

    DBManager dbManager;

    /**
     * Constructor SWTTable.class
     */
    public SWTTable() {
        this.dbManager = new DBManager();
    }

    public static FiltersForm filters = new FiltersForm();

    /**
     * Method to substitute the default text (placeholder)
     * @param field
     * @param text
     */
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

    /**
     * Method of substituting values into a table
     * @param table
     * @param customers
     */
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

    /**
     * Search button click handling method
     * @param group
     * @param table
     */
    public void initFilters(Group group, Table table) {  // метод обработки нажатия кнопки поиска
        Button find = new Button(group, SWT.NONE);
        find.setText("поиск");
        find.setBounds(10, 20, 110, 30);
        find.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                find.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GREEN));
                String query = FilterText.getSql();
                try {
                    SetTableRows(table, dbManager.GetAllParam(query));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        /**
         * Method add button for adding fields and search terms
         */
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

    /**
     * Output table initialization method
     * @param group
     * @return
     */
    public static Table initTable(Group group) {     //метод инициализации таблицы вывода
        Table table = new Table(group, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        table.setHeaderVisible(true);

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

    /**
     * Main method - program entry point
     * @param args
     */
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