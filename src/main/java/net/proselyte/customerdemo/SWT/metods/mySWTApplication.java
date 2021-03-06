package net.proselyte.customerdemo.SWT.metods;

import lombok.ToString;
import net.proselyte.customerdemo.database.DBManager;
import net.proselyte.customerdemo.model.Customer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.Locale;

public class mySWTApplication {



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
        combo1.setItems(items1);

        // Text with border
        Text textFirstName = new Text(shell, SWT.BORDER);
        textFirstName.setText("Michelina");
        textFirstName.setLayoutData(new RowData(250, SWT.DEFAULT));

        // Group
        Group titleGroup = new Group(shell, SWT.NONE);
        titleGroup.setLayout(new RowLayout(SWT.VERTICAL));
        titleGroup.setText("Select operator");

        Button buttonAND = new Button(titleGroup, SWT.RADIO);
        buttonAND.setText("AND");
        Button buttonOR = new Button(titleGroup, SWT.RADIO);
        buttonOR.setText("OR");

        //Create a dropdown Combo
        Combo combo2 = new Combo(shell, SWT.DROP_DOWN);
        String[] items2 = new String[] {"first_name", "last_name", "date_of_birth", "budget"};
        combo2.setItems(items2);

        // Text with border
        Text textLastName = new Text(shell, SWT.BORDER);
        textLastName.setText("Schubert");
        textLastName.setLayoutData(new RowData(250, SWT.DEFAULT));

        // Group
        Group titleGroup2 = new Group(shell, SWT.NONE);
        titleGroup2.setLayout(new RowLayout(SWT.VERTICAL));
        titleGroup2.setText("Select operator");

        Button buttonAND2 = new Button(titleGroup2, SWT.RADIO);
        buttonAND2.setText("AND");
        Button buttonOR2 = new Button(titleGroup2, SWT.RADIO);
        buttonOR2.setText("OR");

        //Create a dropdown Combo
        Combo combo3 = new Combo(shell, SWT.DROP_DOWN);
        String[] items3 = new String[] {"first_name", "last_name", "date_of_birth", "budget"};
        combo3.setItems(items3);

        // Text with border
        Text textBudget = new Text(shell, SWT.BORDER);
        textBudget.setText("233639");
        textBudget.setLayoutData(new RowData(250, SWT.DEFAULT));


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
                String a = textFirstName.getText();
                String b = textLastName.getText();
                String c = textBudget.getText();
                Boolean d = operator;
                textTableResult.setText(String.valueOf(dbm.GetAllParam(a, b, c, d)));
                //textTableResult.setText(dbm.getAllFirstNames(textFirstName.getText()).toString() + "\n" + dbm.getAllFirstNames(textLastName.getText()).toString());
                // Causes the receiver to be resized to its preferred size.
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

