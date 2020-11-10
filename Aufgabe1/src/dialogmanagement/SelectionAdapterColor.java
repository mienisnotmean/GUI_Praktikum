package dialogmanagement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterColor extends SelectionAdapter {

    private Text text;
    private Color color;

    public SelectionAdapterColor(Text text) {
        this.text = text;
    }

    public void widgetSelected(SelectionEvent event) {
        Shell parent = (Shell)text.getParent();
        ColorDialog colorDialog = new ColorDialog(parent, SWT.OPEN);
        RGB rgb = colorDialog.open();
        color = text.getForeground();
        if (rgb != null) {
            color.dispose();
            color = new Color(parent.getDisplay(), rgb);
            text.setForeground(color);
        }
    }

}
