package dialogmanagement;

import application.FileIO;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterOpen extends SelectionAdapter{

    private Text text;

    public SelectionAdapterOpen (Text text) {
        this.text = text;
    }

    public void widgetSelected(SelectionEvent e) {
        Shell parent = (Shell)text.getParent();
        FileDialog fileDialog = new FileDialog(parent, SWT.OPEN);
        String fileName = fileDialog.open();
        if (fileName != null) {
            String content = FileIO.read(fileName);
            text.setText(content);
        }
    }
}
