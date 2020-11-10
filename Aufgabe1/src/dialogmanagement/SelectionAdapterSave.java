package dialogmanagement;

import application.FileIO;
import application.Flag;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.IOException;

public class SelectionAdapterSave extends SelectionAdapter {

    private Text text;
    private Flag flag;

    public SelectionAdapterSave (Text text, Flag flag) {
        this.text = text;
        this.flag = flag;
    }

    public void widgetSelected(SelectionEvent event) {
        saveFile(flag, text);
    }

    public static void saveFile(Flag flag, Text text) {
        String fileName = flag.getFile();
        if (fileName == null) {
            Shell parent = (Shell)text.getParent();
            FileDialog fileDialog = new FileDialog(parent, SWT.SAVE);
            fileName = fileDialog.open();
            flag.setFile(fileName);
            writeFile(flag, text);
        } else {
            writeFile(flag, text);
        }
    }

    public static void writeFile(Flag flag, Text text) {
        String fileName = flag.getFile();
        try {
            if (fileName != null) {
                String content = text.getText();
                FileIO.write(fileName, content);
                flag.setFile(fileName);
                flag.setModified(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
