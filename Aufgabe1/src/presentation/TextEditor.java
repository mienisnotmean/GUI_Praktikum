package presentation;

import application.Flag;
import dialogmanagement.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.util.Locale;
import java.util.ResourceBundle;

public class TextEditor {
    private Shell shell;
    private static Display display;

    private Menu menuBar;
    private MenuItem fileItem;
    private MenuItem editItem;

    private Text text;
    private Menu fileMenu;
    private MenuItem fileNew;
    private MenuItem fileOpen;
    private MenuItem fileSave;
    private MenuItem fileQuit;

    private Menu editMenu;
    private MenuItem editTextColor;

    private ToolBar toolBar;
    private ToolItem toolItemOpen;
    private ToolItem toolItemSave;
    private final String[] ITEMS = {"English", "Deutsch"};
    private Combo comboBox;

    private Flag flag = new Flag();

    private String language = "en";
    private String country = "US";
    private Locale currentLocale = new Locale(language, country);
    private ResourceBundle messages;

    private void createShell() {
        int numOfCols = 1;
        GridLayout layout = new GridLayout(numOfCols, false);
        shell = new Shell(display);
        shell.setLayout(layout);
    }

    private void createMenuBar() {
        menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);

        fileItem = new MenuItem(menuBar, SWT.CASCADE);
        fileItem.setText(messages.getString("file"));

        fileMenu = new Menu(shell, SWT.DROP_DOWN);
        fileNew = new MenuItem(fileMenu, SWT.PUSH);
        fileNew.setText(messages.getString("new"));
        fileOpen = new MenuItem(fileMenu, SWT.PUSH);
        fileOpen.setText(messages.getString("open"));
        fileSave = new MenuItem(fileMenu, SWT.PUSH);
        fileSave.setText(messages.getString("save"));
        fileQuit = new MenuItem(fileMenu, SWT.PUSH);
        fileQuit.setText(messages.getString("quit"));

        fileItem.setMenu(fileMenu);

        editItem = new MenuItem(menuBar, SWT.CASCADE);
        editItem.setText(messages.getString("edit"));

        editMenu = new Menu(shell, SWT.DROP_DOWN);
        editTextColor = new MenuItem(editMenu, SWT.PUSH);
        editTextColor.setText(messages.getString("textcolor"));

        editItem.setMenu(editMenu);
    }

    private void createToolBar() {
        toolBar = new ToolBar(shell, SWT.NONE);

        toolItemOpen = new ToolItem(toolBar, SWT.PUSH);
        Image openImage = new Image(display, "src/icons/full/obj16/fldr_obj.png");
        toolItemOpen.setImage(openImage);

        toolItemSave = new ToolItem(toolBar, SWT.PUSH);
        Image saveImage = new Image(display, "src/icons/full/dtool16/save_edit.png");
        toolItemSave.setImage(saveImage);

        ToolItem sep = new ToolItem(toolBar, SWT.SEPARATOR);

        comboBox = new Combo(toolBar, SWT.DROP_DOWN);
        for (String s: ITEMS) {
            comboBox.add(s);
        }
        comboBox.setText(ITEMS[0]);
        comboBox.addModifyListener(new ModifyListenerLanguages(messages, fileItem, fileNew, fileOpen, fileSave, fileQuit, editItem, editTextColor));
        comboBox.pack();

        sep.setWidth(comboBox.getSize().x);
        sep.setControl(comboBox);
        toolBar.pack();
    }

    private void createText() {
        text = new Text(shell, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        text.setBackground(new Color(255, 255, 255));
        text.setForeground(new Color(0, 0, 0));

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        text.setLayoutData(gridData);
    }

    private void createListener() {
        text.addModifyListener(modifyEvent -> {
            flag.setModified(true);
        });

        fileNew.addSelectionListener(new SelectionAdapterNew());
        fileOpen.addSelectionListener(new SelectionAdapterOpen(text, flag));
        fileSave.addSelectionListener(new SelectionAdapterSave(text, flag));
        fileQuit.addSelectionListener(new SelectionAdapterQuit(text, flag));

        editTextColor.addSelectionListener(new SelectionAdapterColor(text));

        toolItemOpen.addSelectionListener(new SelectionAdapterOpen(text, flag));
        toolItemSave.addSelectionListener(new SelectionAdapterSave(text, flag));

    }

    public static TextEditor getEditor() {
        if (display == null) {
            display = new Display();
        }
        return new TextEditor();
    }

    public TextEditor() {
        this.messages = ResourceBundle.getBundle("MessageBundle", currentLocale);
        createShell();
        createMenuBar();
        createToolBar();
        createText();
        createListener();

        assert false;
        shell.pack();
    }

    public void open() {
        shell.setSize(500, 500);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

}
