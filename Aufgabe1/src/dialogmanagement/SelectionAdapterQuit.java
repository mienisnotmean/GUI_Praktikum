package dialogmanagement;

import application.Flag;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterQuit extends SelectionAdapter {

    private Text text;
    private Shell parent;
    private Flag flag;

    public SelectionAdapterQuit(Text text, Flag flag) {
        this.text = text;
        this.parent = (Shell) text.getParent();
        this.flag = flag;
    }

    public void widgetSelected(SelectionEvent e) {
        if (flag.isModified()) {
            MessageBox saveConfirm = new MessageBox(parent, SWT.YES | SWT.NO | SWT.CANCEL | SWT.ICON_QUESTION);
            saveConfirm.setMessage("Do you want to save the changes?");
            int result = saveConfirm.open();
            switch (result) {
                case SWT.YES:
                    SelectionAdapterSave.saveFile(flag, text);
                    confirmQuit();
                    break;
                case SWT.NO:
                    confirmQuit();
                    break;
                case SWT.CANCEL:
                    break;
            }
        } else {
            confirmQuit();
        }
    }

    public void confirmQuit() {
        MessageBox quitConfirm = new MessageBox(parent, SWT.YES | SWT.NO | SWT.ICON_QUESTION);
        quitConfirm.setMessage("Quit?");
        int result = quitConfirm.open();
        switch (result) {
            case SWT.YES:
                parent.dispose();
                break;
            case SWT.NO:
                break;
        }
    }

}
