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
    private Text text;
    private Text text_1;
    private Text text_2;
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

        Label label = new Label(composite, SWT.NONE);
        label.setBounds(10, 10, 95, 15);
        label.setText("поиск по имени");

        Label label_1 = new Label(composite, SWT.NONE);
        label_1.setText("поиск по фамилии");
        label_1.setBounds(252, 10, 110, 15);

        Label label_2 = new Label(composite, SWT.NONE);
        label_2.setText("поиск по дате рождения");
        label_2.setBounds(10, 47, 141, 21);

        Label label_3 = new Label(composite, SWT.NONE);
        label_3.setText("поиск по зарплате");
        label_3.setBounds(252, 47, 110, 15);

        txtSearchOfName = new Text(composite, SWT.BORDER);
        txtSearchOfName.setText("введите имя");
        txtSearchOfName.setBounds(113, 10, 123, 21);

        text = new Text(composite, SWT.BORDER);
        text.setText("введите фамилию");
        text.setBounds(378, 10, 141, 21);

        text_1 = new Text(composite, SWT.BORDER);
        text_1.setText("введите дату");
        text_1.setBounds(376, 47, 143, 21);

        text_2 = new Text(composite, SWT.BORDER);
        text_2.setText("введите число");
        text_2.setBounds(152, 47, 84, 21);

        Button button = new Button(composite, SWT.RADIO);
        button.setBounds(23, 98, 40, 16);
        button.setText("и");

        Button button_1 = new Button(composite, SWT.RADIO);
        button_1.setBounds(69, 98, 48, 16);
        button_1.setText("или");

        Label label_4 = new Label(composite, SWT.NONE);
        label_4.setText("выберете вид поиска");
        label_4.setBounds(10, 77, 123, 15);

        Button btnNewButton = new Button(composite, SWT.NONE);
        btnNewButton.setBounds(378, 87, 75, 25);
        btnNewButton.setText("поиск");

        Button btnNewButton_1 = new Button(composite, SWT.NONE);
        btnNewButton_1.setBounds(179, 109, 129, 21);
        btnNewButton_1.setText("показать всех");

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
