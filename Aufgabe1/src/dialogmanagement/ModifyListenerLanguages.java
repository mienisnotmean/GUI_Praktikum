package dialogmanagement;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.MenuItem;

import java.util.Locale;
import java.util.ResourceBundle;

public class ModifyListenerLanguages implements ModifyListener {

    private ResourceBundle messages;

    private MenuItem fileItem;
    private MenuItem fileNew;
    private MenuItem fileOpen;
    private MenuItem fileSave;
    private MenuItem fileQuit;
    private MenuItem editItem;
    private MenuItem editTextColor;

    public ModifyListenerLanguages(ResourceBundle messages, MenuItem fileItem, MenuItem fileNew, MenuItem fileOpen,
                                   MenuItem fileSave, MenuItem fileQuit, MenuItem editItem, MenuItem editTextColor) {
        this.messages = messages;
        this.fileItem = fileItem;
        this.fileNew = fileNew;
        this.fileOpen = fileOpen;
        this.fileSave = fileSave;
        this.fileQuit = fileQuit;
        this.editItem = editItem;
        this.editTextColor = editTextColor;
    }

    @Override
    public void modifyText(ModifyEvent modifyEvent) {
        Combo source = (Combo) modifyEvent.getSource();
        String lang = source.getText();
        Locale newLocale = changeLanguage(lang);
        messages = ResourceBundle.getBundle("MessageBundle", newLocale);
        fileItem.setText(messages.getString("file"));
        fileNew.setText(messages.getString("new"));
        fileOpen.setText(messages.getString("open"));
        fileSave.setText(messages.getString("save"));
        fileQuit.setText(messages.getString("quit"));
        editItem.setText(messages.getString("edit"));
        editTextColor.setText(messages.getString("textcolor"));
    }

    private Locale changeLanguage(String lang) {
        return switch (lang) {
            case "Deutsch" -> new Locale("de", "DE");
            case "English" -> new Locale("en", "US");
            default -> null;
        };
    }
}
