package net.proselyte.customerdemo.SWT.metods;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class mySWTApplication {


    /**
     * Launch the application.
     *
     * @param args
     */


    public static void main(String[] args) {



        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("SWT System)");

        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginLeft = 10;
        rowLayout.marginTop = 10;
        rowLayout.spacing = 15;
        shell.setLayout(rowLayout);


        // Text with border
        Text textFirstName = new Text(shell, SWT.BORDER);
        textFirstName.setText("введите имя");
        textFirstName.setLayoutData(new RowData(250, SWT.DEFAULT));

        // Text with border
        Text textLastName = new Text(shell, SWT.BORDER);
        textLastName.setText("введите фамилию");
        textLastName.setLayoutData(new RowData(250, SWT.DEFAULT));

        // Text with border
        Text textBudget = new Text(shell, SWT.BORDER);
        textBudget.setText("введите размер зарплаты");
        textBudget.setLayoutData(new RowData(250, SWT.DEFAULT));

        Button buttonAND = new Button(shell, SWT.RADIO);
        buttonAND.setLayoutData(new RowData(40, SWT.DEFAULT));
        buttonAND.setText("и");

        Button buttonOR = new Button(shell, SWT.RADIO);
        buttonAND.setLayoutData(new RowData(40, SWT.DEFAULT));
        buttonOR.setText("или");

        Label label = new Label(shell, SWT.NONE);
        label.setText("Select Titles: ");

        Label labelAnswer = new Label(shell, SWT.NONE);
        labelAnswer.setForeground(display.getSystemColor(SWT.COLOR_BLUE));


        buttonAND.addSelectionListener(new SelectionAdapter()  {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Button source=  (Button) e.getSource();

                if(source.getSelection())  {
                    labelAnswer.setText("Вы выбрали поиск с оператором - "+ source.getText());
                    labelAnswer.pack();
                }
            }

        });

        buttonOR.addSelectionListener(new SelectionAdapter()  {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Button source=  (Button) e.getSource();

                if(source.getSelection())  {
                    labelAnswer.setText("Вы выбрали поиск с оператором - "+ source.getText());
                    labelAnswer.pack();
                }
            }

        });

        Composite parent = new Composite(shell, SWT.NONE);
        FillLayout fillLayout= new FillLayout();
        fillLayout.type= SWT.HORIZONTAL;
        parent.setLayout(fillLayout);
        Button b1 = new Button(parent, SWT.NONE);
        b1.setText("поиск");
        Button b2 = new Button(parent, SWT.NONE);
        b2.setText("показать всех");
        Button b3 = new Button(parent, SWT.NONE);
        b3.setText("очистить");

        // Text with multi lines and show vertiacal scroll.
        Text textTableResult = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        textTableResult.setText("вывод результата поиска");

        textTableResult.setLayoutData(new RowData(920, 500));



        shell.setSize(1500, 800);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

}

