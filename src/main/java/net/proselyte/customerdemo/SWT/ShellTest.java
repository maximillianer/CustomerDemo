package net.proselyte.customerdemo.SWT;

import org.eclipse.swt.*;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.*;

public class ShellTest
{
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
    public static void main (String [] args)
    {
        Display display = new Display ();
        final Shell shell = new Shell (display, SWT.SHELL_TRIM);
        shell.setText("Пример Shell");
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
        // shell.pack();
        shell.open();
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ())
                display.sleep ();
        }
        display.dispose ();
        System.exit(0);
    }
}