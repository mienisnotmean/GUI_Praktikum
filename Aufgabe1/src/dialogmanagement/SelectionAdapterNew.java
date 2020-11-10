package dialogmanagement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import presentation.TextEditor;

public class SelectionAdapterNew extends SelectionAdapter {

    public SelectionAdapterNew() {
    }

    public void widgetSelected(SelectionEvent event) {
        TextEditor gui = TextEditor.getEditor();
        gui.open();
    }

}
