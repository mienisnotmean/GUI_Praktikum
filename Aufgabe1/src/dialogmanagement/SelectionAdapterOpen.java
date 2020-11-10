package dialogmanagement;

import application.FileIO;
import application.Flag;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SelectionAdapterOpen extends SelectionAdapter {

    private Text text;
    private Flag flag;

    public SelectionAdapterOpen(Text text, Flag flag) {
        this.text = text;
        this.flag = flag;
    }

    public void widgetSelected(SelectionEvent event) {
        Shell parent = (Shell) text.getParent();
        FileDialog fileDialog = new FileDialog(parent, SWT.OPEN);
        try {
            String fileName = fileDialog.open();
            if (fileName != null) {
                String content = FileIO.read(fileName);
                text.setText(content);
                flag.setModified(false);
                flag.setFile(fileName);
            }
        } catch (FileNotFoundException e) {
            MessageBox fileNotFound = new MessageBox(parent, SWT.ICON_INFORMATION);
            fileNotFound.setMessage("File not found.");
            fileNotFound.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
