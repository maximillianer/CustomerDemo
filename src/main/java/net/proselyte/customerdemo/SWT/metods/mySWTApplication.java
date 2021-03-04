package net.proselyte.customerdemo.SWT.metods;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.custom.ScrolledComposite;

public class mySWTApplication {
    protected Shell shell;
    private Text txtSearchOfName;
    private Text textSearchOfLastname;
    private Text textSearchOfDate;
    private Text textSearchOfBuget;
    private Table table;

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            mySWTApplication window = new mySWTApplication();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the window.
     */
    public void open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        shell = new Shell();
        shell.setModified(true);
        shell.setEnabled(false);
        shell.setSize(567, 579);
        shell.setText("SWT Application");
        shell.setLayout(new FillLayout(SWT.HORIZONTAL));

        Composite composite = new Composite(shell, SWT.NONE);

        Label SearchOfName = new Label(composite, SWT.NONE);
        SearchOfName.setBounds(10, 10, 95, 15);
        SearchOfName.setText("поиск по имени");

        Label SearchOfLastname = new Label(composite, SWT.NONE);
        SearchOfLastname.setText("поиск по фамилии");
        SearchOfLastname.setBounds(252, 10, 110, 15);

        Label SearchOfDate = new Label(composite, SWT.NONE);
        SearchOfDate.setText("поиск по дате рождения");
        SearchOfDate.setBounds(10, 47, 141, 21);

        Label SearchOfBuget = new Label(composite, SWT.NONE);
        SearchOfBuget.setText("поиск по зарплате");
        SearchOfBuget.setBounds(252, 47, 110, 15);

        txtSearchOfName = new Text(composite, SWT.BORDER);
        txtSearchOfName.setText("введите имя");
        txtSearchOfName.setBounds(113, 10, 123, 21);

        textSearchOfLastname = new Text(composite, SWT.BORDER);
        textSearchOfLastname.setText("введите фамилию");
        textSearchOfLastname.setBounds(378, 10, 141, 21);

        textSearchOfDate = new Text(composite, SWT.BORDER);
        textSearchOfDate.setText("введите дату");
        textSearchOfDate.setBounds(376, 47, 143, 21);

        textSearchOfBuget = new Text(composite, SWT.BORDER);
        textSearchOfBuget.setText("введите число");
        textSearchOfBuget.setBounds(152, 47, 84, 21);

        Button buttonAND = new Button(composite, SWT.RADIO);
        buttonAND.setBounds(23, 98, 40, 16);
        buttonAND.setText("и");

        Button buttonOR = new Button(composite, SWT.RADIO);
        buttonOR.setBounds(69, 98, 48, 16);
        buttonOR.setText("или");

        Label AND_OR = new Label(composite, SWT.NONE);
        AND_OR.setText("выберете вид поиска");
        AND_OR.setBounds(10, 77, 123, 15);

        Button SearchButton = new Button(composite, SWT.NONE);
        SearchButton.setBounds(378, 87, 75, 25);
        SearchButton.setText("поиск");

        Button AllSearchButton = new Button(composite, SWT.NONE);
        AllSearchButton.setBounds(179, 109, 129, 21);
        AllSearchButton.setText("показать всех");

        ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        scrolledComposite.setToolTipText("");
        scrolledComposite.setAlwaysShowScrollBars(true);
        scrolledComposite.setBounds(10, 136, 548, 398);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);

        table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 136, 524, 398);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
    }
}
