package presentation;

import dialogmanagement.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

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
    private final String[] ITEMS = {"Deutsch", "English"};
    private Combo combo;

    private void createShell() {
        int numOfCols = 1;
        GridLayout layout = new GridLayout(numOfCols, true);
        shell = new Shell(display);
        shell.setBounds(500, 500, 1000, 1000);
        shell.setLayout(layout);
    }

    private void createMenuBar() {
        menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);

        fileItem = new MenuItem(menuBar, SWT.CASCADE);
        fileItem.setText("&File");

        fileMenu = new Menu(shell, SWT.DROP_DOWN);
        fileNew = new MenuItem(fileMenu, SWT.PUSH);
        fileNew.setText("&New");
        fileOpen = new MenuItem(fileMenu, SWT.PUSH);
        fileOpen.setText("&Open ...");
        fileSave = new MenuItem(fileMenu, SWT.PUSH);
        fileSave.setText("&Save ...");
        fileQuit = new MenuItem(fileMenu, SWT.PUSH);
        fileQuit.setText("&Quit");

        fileItem.setMenu(fileMenu);

        editItem = new MenuItem(menuBar, SWT.CASCADE);
        editItem.setText("&Edit");

        editMenu = new Menu(shell, SWT.DROP_DOWN);
        editTextColor = new MenuItem(editMenu, SWT.PUSH);
        editTextColor.setText("Text Color");

        editItem.setMenu(editMenu);
    }

    private void createToolBar() {
        toolBar = new ToolBar(shell, SWT.SHADOW_OUT);


        toolItemOpen = new ToolItem(toolBar, SWT.PUSH);
        Image openImage = new Image(display, "src/icons/full/obj16/fldr_obj.png");
        toolItemOpen.setImage(openImage);

        toolItemSave = new ToolItem(toolBar, SWT.PUSH);
        Image saveImage = new Image(display, "src/icons/full/dtool16/save_edit.png");
        toolItemSave.setImage(saveImage);

        combo = new Combo(shell, SWT.DROP_DOWN);
        combo.setItems(ITEMS);
        combo.setText(ITEMS[0]);

        GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1);
        toolBar.setLayoutData(gridData);
    }

    private void createText() {
        text = new Text(shell, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        text.setBackground(new Color(255, 255, 255));
        text.setForeground(new Color(0, 0, 0));

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        text.setLayoutData(gridData);
    }

    private void createListener() {
        fileNew.addSelectionListener(new SelectionAdapterNew());
        fileOpen.addSelectionListener(new SelectionAdapterOpen(text));
        fileSave.addSelectionListener(new SelectionAdapterSave(text));
        fileQuit.addSelectionListener(new SelectionAdapterQuit(shell));

        editTextColor.addSelectionListener(new SelectionAdapterColor(text, shell));

        toolItemOpen.addSelectionListener(new SelectionAdapterOpen(text));
        toolItemSave.addSelectionListener(new SelectionAdapterSave(text));

    }

    public static TextEditor getEditor() {
        if (display == null) {
            display = new Display();
        }
        return new TextEditor();
    }

    public TextEditor() {
        createShell();
        createMenuBar();
        createToolBar();
        createText();
        createListener();

        assert false;
        shell.pack();
    }

    public void open() {
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

}
