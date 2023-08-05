package br.upe.enenhariasoftware.psw.jabberpoint.view;

import br.upe.enenhariasoftware.psw.jabberpoint.controller.MenuController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends MenuBar{

    private final transient MenuController menuController;

    protected static final String FILE = "File";
    protected static final String EXIT = "Exit";
    protected static final String OPEN = "Open";
    protected static final String SAVE = "Save";
    protected static final String NEW = "New";
    protected static final String GOTO = "Go to";
    protected static final String NEXT = "Next";
    protected static final String PREV = "Previous";
    protected static final String VIEW = "View";
    protected static final String ABOUT = "About";
    protected static final String HELP = "Help";

    public MenuView(MenuController menuController){

        this.menuController = menuController;

        fileMenu();

        viewMenu();

        helpMenu();

    }

    private void fileMenu(){
        Menu fileMenu = new Menu(FILE);

        fileMenu.add(menuItem(OPEN, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                menuController.openFile();
            }
        }));

        fileMenu.add(menuItem(NEW, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                menuController.clearPresentation();
            }
        }));

        fileMenu.add(menuItem(SAVE, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                menuController.saveFile();
            }
        }));

        fileMenu.addSeparator();

        fileMenu.add(menuItem(EXIT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                menuController.exit();
            }
        }));

        add(fileMenu);
    }

    private void viewMenu(){
        Menu viewMenu = new Menu(VIEW);

        viewMenu.add(menuItem(NEXT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                menuController.nextSlide();
            }
        }));

        viewMenu.add(menuItem(PREV, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                menuController.previousSlide();
            }
        }));

        viewMenu.add(menuItem(GOTO, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                menuController.goToSlide();
            }
        }));

        add(viewMenu);
    }

    private void helpMenu(){
        Menu helpMenu = new Menu(HELP);

        helpMenu.add(menuItem(ABOUT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                menuController.about();
            }
        }));

        setHelpMenu(helpMenu);
    }

    private MenuItem menuItem(String name, ActionListener actionListener){
        MenuItem menuItem = new MenuItem(name, new MenuShortcut(name.charAt(0)));
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

}
