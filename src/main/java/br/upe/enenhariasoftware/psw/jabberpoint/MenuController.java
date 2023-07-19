/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 *
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class MenuController extends MenuBar {

    private static final long serialVersionUID = 227L;

    private Frame parentFrame;
    private Presentation presentation;

    protected static final String ABOUT = "About";
    protected static final String FILE = "File";
    protected static final String EXIT = "Exit";
    protected static final String GOTO = "Go to";
    protected static final String HELP = "Help";
    protected static final String NEW = "New";
    protected static final String NEXT = "Next";
    protected static final String OPEN = "Open";
    protected static final String PAGENR = "Slide number";
    protected static final String PREV = "Previous";
    protected static final String SAVE = "Save";
    protected static final String VIEW = "View";
    protected static final String TESTFILE = "classpath:test.xml";
    protected static final String SAVEFILE = "classpath:dump.xml";
    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Failed to load";
    protected static final String SAVEERR = "Failed to save";

    public MenuController(Frame parentFrame, Presentation presentation) {
        this.parentFrame = parentFrame;
        this.presentation = presentation;

        fileMenu();

        viewMenu();

        helpMenu();

    }

    private void fileMenu(){
        Menu fileMenu = new Menu(FILE);

        fileMenu.add(menuItem(OPEN, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                openFile();
            }
        }));

        fileMenu.add(menuItem(NEW, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                clearPresentation();
            }
        }));

        fileMenu.add(menuItem(SAVE, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                saveFile();
            }
        }));

        fileMenu.addSeparator();

        fileMenu.add(menuItem(EXIT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                exit();
            }
        }));

        add(fileMenu);
    }

    private void viewMenu(){
        Menu viewMenu = new Menu(VIEW);

        viewMenu.add(menuItem(NEXT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                nextSlide();
            }
        }));

        viewMenu.add(menuItem(PREV, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                previousSlide();
            }
        }));

        viewMenu.add(menuItem(GOTO, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                goToSlide();
            }
        }));

        add(viewMenu);
    }

    private void helpMenu(){
        Menu helpMenu = new Menu(HELP);

        helpMenu.add(menuItem(ABOUT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                about();
            }
        }));

        setHelpMenu(helpMenu);
    }

    private void openFile(){
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, new File(TESTFILE).getAbsolutePath());
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parentFrame, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        parentFrame.repaint();
    }

    private void clearPresentation() {
        presentation.clear();
        parentFrame.repaint();
    }

    private void saveFile(){
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, SAVEFILE);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parentFrame, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exit(){
        presentation.exit(0);
    }

    private void nextSlide(){
        presentation.nextSlide();
    }

    private void previousSlide(){
        presentation.prevSlide();
    }

    private void goToSlide(){
        String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        presentation.setSlideNumber(pageNumber - 1);
    }

    private void about(){
        About.show(parentFrame);
    }

    private MenuItem menuItem(String name, ActionListener actionListener){
        MenuItem menuItem = new MenuItem(name, new MenuShortcut(name.charAt(0)));
        menuItem.addActionListener(actionListener);
        return menuItem;
    }
}