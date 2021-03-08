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
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import static net.proselyte.customerdemo.database.DBManager.GetAllParam;

public class mySWTApplication {

    public static void SetPlaceholder(Combo field, String text){
        field.addPaintListener( new PaintListener() {
            @Override
            public void paintControl( PaintEvent event ) {
                if( field.getText().isEmpty() ) {
                    int x = 1; // indent some pixels
                    int y = 1; // center vertically
                    event.gc.drawText( text, x, y );
                    field.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY));
                }
                else field.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
            }
        } );
    }

    /**
     * Launch the application.
     *
     * @param args
     */


    public static void main(String[] args) {

        boolean operator = false;

        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("SWT System)");

        RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
        rowLayout.marginLeft = 10;
        rowLayout.marginTop = 10;
        rowLayout.spacing = 15;
        shell.setLayout(rowLayout);

        //Create a dropdown Combo
        Combo combo1 = new Combo(shell, SWT.DROP_DOWN);
        String[] items1 = new String[] {"first_name", "last_name", "date_of_birth", "budget"};
        combo1.setLayoutData(new RowData(100, SWT.DEFAULT));
        combo1.setItems(items1);
        SetPlaceholder(combo1, "выберете поле");

        // Text with border
        Text textFirstName = new Text(shell, SWT.BORDER);
        textFirstName.setText("Michelina");
        textFirstName.setLayoutData(new RowData(200, SWT.DEFAULT));

        //Create a dropdown Combo operators
        Combo comboOperator1 = new Combo(shell, SWT.DROP_DOWN);
        String[] itemsOperator1 = new String[] {"AND", "OR"};
        comboOperator1.setLayoutData(new RowData(110, SWT.DEFAULT));
        comboOperator1.setItems(itemsOperator1);
        SetPlaceholder(comboOperator1, "выберете оператор");

        //Create a dropdown Combo
        Combo combo2 = new Combo(shell, SWT.DROP_DOWN);
        String[] items2 = new String[] {"first_name", "last_name", "date_of_birth", "budget"};
        combo2.setLayoutData(new RowData(100, SWT.DEFAULT));
        combo2.setItems(items2);
        SetPlaceholder(combo2, "выберете поле");


        // Text with border
        Text textLastName = new Text(shell, SWT.BORDER);
        textLastName.setText("Schubert");
        textLastName.setLayoutData(new RowData(200, SWT.DEFAULT));

        //Create a dropdown Combo operators
        Combo comboOperator2 = new Combo(shell, SWT.DROP_DOWN);
        String[] itemsOperator2 = new String[] {"AND", "OR"};
        comboOperator2.setLayoutData(new RowData(110, SWT.DEFAULT));
        comboOperator2.setItems(itemsOperator2);
        SetPlaceholder(comboOperator2, "выберете оператор");

        //Create a dropdown Combo
        Combo combo3 = new Combo(shell, SWT.DROP_DOWN);
        String[] items3 = new String[] {"first_name", "last_name", "date_of_birth", "budget"};
        combo3.setLayoutData(new RowData(100, SWT.DEFAULT));
        combo3.setItems(items3);
        SetPlaceholder(combo3, "выберете поле");

        // Text with border
        Text textBudget = new Text(shell, SWT.BORDER);
        textBudget.setText("233639");
        textBudget.setLayoutData(new RowData(200, SWT.DEFAULT));

//-----------------------------------------------------------

//----------------------------------------------------------

        // Text with multi lines and show vertiacal scroll.
        Text textTableResult = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        textTableResult.setText("вывод результата поиска");

        Composite parent = new Composite(shell, SWT.NONE);
        FillLayout fillLayout = new FillLayout();
        fillLayout.type = SWT.VERTICAL;
        parent.setLayout(fillLayout);
        fillLayout.marginHeight = 50;
        fillLayout.marginWidth = 50;
        fillLayout.spacing = 15;
        Button b1 = new Button(parent, SWT.NONE);
        b1.setText("поиск");
        // Handling when users click the button.

        Button b2 = new Button(parent, SWT.NONE);
        b2.setText("показать всех");

        Button b3 = new Button(parent, SWT.NONE);
        b3.setText("очистить");


        // Handling when users click the button.
        b1.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                DBManager dbm = new DBManager();
                int idx = combo1.getSelectionIndex(); // index of select combo
                String searchItem1 = combo1.getItem(idx); // search criteria
                int idx2 = combo2.getSelectionIndex(); // index of select combo
                String searchItem2 = combo2.getItem(idx2); // search criteria
                int idx3 = combo3.getSelectionIndex(); // index of select combo
                String searchItem3 = combo3.getItem(idx3); // search criteria
                int idx4 = comboOperator1.getSelectionIndex(); // index of select combo
                String searchItem4 = comboOperator1.getItem(idx4); // search criteria operator
                int idx5 = comboOperator2.getSelectionIndex(); // index of select combo
                String searchItem5 = comboOperator2.getItem(idx5); // search criteria operator
                String [] conditioins = new String[8];
                conditioins[0] = searchItem1;
                conditioins[1] = textFirstName.getText();
                conditioins[2] = searchItem4;
                conditioins[3] = searchItem2;
                conditioins[4] = textLastName.getText();
                conditioins[5] = searchItem5;
                conditioins[6] = searchItem3;
                conditioins[7] = textBudget.getText();
                for (int i =0; i < conditioins.length; i++) System.out.println(conditioins[i]);//testing
                StringBuilder query = new StringBuilder("SELECT * FROM customers WHERE " + conditioins[0] + " = '" + conditioins[1] +
                        "' " + conditioins[2] + " " + conditioins[3] + " = '" + conditioins[4] + "' " + conditioins[5] +
                        " " + conditioins[6] + " = '" + conditioins[7] + "'");
                System.out.println(query);

                try {
                    textTableResult.setText(String.valueOf(GetAllParam(query.toString())));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        });
        // Handling when users click the button.
        b2.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                DBManager dbm = new DBManager();
                textTableResult.setText(dbm.getAllCustomers().toString());
                // Causes the receiver to be resized to its preferred size.
            }

        });
        // Handling when users click the button.
        b3.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                textTableResult.setText("");
                // Causes the receiver to be resized to its preferred size.
            }

        });
        textTableResult.setLayoutData(new RowData(1100, 500));

        shell.setSize(1500, 800);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

}

