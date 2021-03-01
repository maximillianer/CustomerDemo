package net.proselyte.customerdemo.SWT.metods;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;

import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;

import org.eclipse.swt.widgets.*;

public class LayoutExample {
    private  Composite  header     = null;
    private  Composite  body       = null;
    private  Composite  pnlButtons = null;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Конструктор класса
     * @param shell основное окно приложения
     */
    public LayoutExample(final Shell shell)
    {
        // Менеджер расположения главной формы
        GridLayout gridLayout      = new GridLayout();
        gridLayout.numColumns      = 1;
        gridLayout.marginHeight    = 1;
        gridLayout.marginWidth     = 1;
        gridLayout.verticalSpacing = 2;
        shell.setLayout(gridLayout);

        // Верхняя панель
        createPanelTop(shell);

        // Центральная панель
        creatPanelCenter(shell);

        // Панель кнопок управления
        createPanelControls(shell);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void createPanelTop(Composite parent)
    {
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.heightHint = 30;

        header = new Composite (parent, SWT.BORDER);
        header.setLayoutData(gridData);
        header.setLayout(new FillLayout());
        new Label(header, SWT.CENTER).setText("Верхняя панель");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void createPanelControls(Composite parent)
    {
        // Панель кнопок управления
        pnlButtons = new Composite(parent, SWT.BORDER);

        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.heightHint = 36;
        pnlButtons.setLayoutData(gridData);

        // Размещение кнопки в панели pnlButtons
        pnlButtons.setLayout(new FillLayout());
        new Label(pnlButtons, SWT.CENTER)
                .setText("Панель кнопок управления");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void creatPanelCenter(Composite parent)
    {
        // Центральная панель
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL |
                GridData.FILL_VERTICAL);
        gridData.grabExcessVerticalSpace = true;
        body = new Composite (parent, SWT.NONE);
        body.setLayoutData(gridData);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns   = 2;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth  = 0;
        body.setLayout(gridLayout);

        SashForm sashForm = new SashForm(body, SWT.NONE);
        sashForm.setOrientation(SWT.HORIZONTAL);
        gridData = new GridData(GridData.FILL_HORIZONTAL |
                GridData.FILL_VERTICAL);
        gridData.horizontalSpan = 3;
        sashForm.setLayoutData(gridData);
        // Формирование интерфейса центральной панели
        createBodyLeft (sashForm);
        createBodyRight(sashForm);
        // Определение положения разделителя
        sashForm.setWeights(new int[] { 2, 5 });
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void createBodyLeft(Composite parent)
    {
        Composite composite = new Composite(parent, SWT.BORDER);
        composite.setLayout(new FillLayout());

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        composite.setLayoutData(gridData);

        composite.setLayout(new FillLayout());
        Label labelA = new Label(composite, SWT.CENTER);
        labelA.setText("Левая панель");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void createBodyRight(Composite parent)
    {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new FillLayout());

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        composite.setLayoutData(gridData);

        SashForm sashForm2 = new SashForm(composite, SWT.VERTICAL);

        Composite comp2_top = new Composite(sashForm2, SWT.BORDER);
        Composite comp2_bot = new Composite(sashForm2, SWT.BORDER);
        comp2_top.setLayout(new FillLayout());
        comp2_bot.setLayout(new FillLayout());

        Label labelA = new Label(comp2_top, SWT.CENTER);
        labelA.setText("Правая верхняя панель");
        Label labelB = new Label(comp2_bot, SWT.CENTER);
        labelB.setText("Правая нижняя панель");
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static  boolean  adapter = true;

    private static int confirmExit(Shell shell)
    {
        int style = SWT.APPLICATION_MODAL | SWT.OK | SWT.CANCEL;
        MessageBox messageBox = new MessageBox (shell, style);
        messageBox.setText ("Подтверждение выхода");
        messageBox.setMessage ("Закрыть окно?");
        return messageBox.open();
    }
    private static int confirmExit(Shell shell, ShellEvent event)
    {
        return confirmExit(shell);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args)
    {
        Display display = new Display();
        final Shell   shell = new Shell (display, SWT.SHELL_TRIM);
        shell.setText("gridLayout");
        new LayoutExample(shell);
        if (adapter) {
            shell.addListener (SWT.Close, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    event.doit = confirmExit(shell) == SWT.OK;
                }
            });
        } else {
            shell.addShellListener(new ShellAdapter()
            {
                @Override
                public void shellClosed(ShellEvent event) {
                    event.doit = confirmExit(shell, event) == SWT.OK;
                }
                @Override
                public void shellActivated(ShellEvent e) {}
                @Override
                public void shellDeactivated(ShellEvent e) {}
                @Override
                public void shellDeiconified(ShellEvent e) {}
                @Override
                public void shellIconified(ShellEvent e) {}
            });
        }

        shell.open ();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch ())
                display.sleep ();
        }
        display.dispose ();
        System.exit(0);
    }
}