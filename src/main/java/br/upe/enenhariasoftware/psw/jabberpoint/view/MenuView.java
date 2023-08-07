package br.upe.enenhariasoftware.psw.jabberpoint.view;

import br.upe.enenhariasoftware.psw.jabberpoint.controller.MenuController;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends MenuBar {

    private static final long serialVersionUID = 227L;

    protected static final String FILE = "File";
    protected static final String VIEW = "View";
    protected static final String HELP = "Help";
    protected static final String ABOUT = "About";
    protected static final String NEW = "New";
    protected static final String OPEN = "Open";
    protected static final String SAVE = "Save";
    protected static final String EXIT = "Exit";
    protected static final String NEXT = "Next";
    protected static final String PREV = "Previous";
    protected static final String GOTO = "Go to";

    private final transient MenuController menuController;

    public MenuView(MenuController menuController) {
        this.menuController = menuController;

        fileBox();
        viewBox();
        helpBox();
    }

    public void fileBox() {
        MenuItem menuItem;

        Menu fileMenu = new Menu(FILE);
        menuItem = mkMenuItem(OPEN);
        fileMenu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                menuController.openFile();
            }
        });

        menuItem = mkMenuItem(NEW);
        fileMenu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                menuController.createFile();
            }
        });

        menuItem = mkMenuItem(SAVE);
        fileMenu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuController.saveFile();
            }
        });

        fileMenu.addSeparator();

        menuItem = mkMenuItem(EXIT);
        fileMenu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                menuController.exit();
            }
        });

        add(fileMenu);
    }

    public void viewBox() {
        MenuItem menuItem;

        Menu viewMenu = new Menu(VIEW);
        menuItem = mkMenuItem(NEXT);
        viewMenu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                menuController.goToNextSlide();
            }
        });

        menuItem = mkMenuItem(PREV);
        viewMenu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                menuController.goToPreviousSlide();
            }
        });

        menuItem = mkMenuItem(GOTO);
        viewMenu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                menuController.goToChosenSlide();
            }
        });

        add(viewMenu);
    }

    public void helpBox() {
        MenuItem menuItem;

        Menu helpMenu = new Menu(HELP);
        menuItem = mkMenuItem(ABOUT);
        helpMenu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                menuController.showAboutBox();
            }
        });

        setHelpMenu(helpMenu);
    }

    public MenuItem mkMenuItem(String name) {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
