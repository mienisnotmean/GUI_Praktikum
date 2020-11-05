package dialogmanagement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SelectionAdapterQuit extends SelectionAdapter {

    private Shell shell;

    public SelectionAdapterQuit(Shell shell) {
        this.shell = shell;
    }

    public void widgetSelected(SelectionEvent e) {
        MessageBox quitConfirm = new MessageBox(shell, SWT.YES | SWT.NO);
        quitConfirm.setMessage("Quit?");
        quitConfirm.open();
        int result = quitConfirm.open();
        switch (result) {
            case SWT.YES:
                shell.dispose();
                break;
            case SWT.NO:
                break;
        }
    }

}
