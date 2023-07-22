/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * <p>
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
import java.io.File;
import java.io.IOException;
import java.io.Serial;

import javax.swing.JOptionPane;

public class MenuController extends MenuBar {
  @Serial
  private static final long serialVersionUID = 227L;
  private final Frame frame;
  private final transient Presentation presentation;
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

  public MenuController(Frame frame, Presentation pres) {
    this.frame = frame;
    presentation = pres;
    
    MenuItem menuItem;
    
    Menu fileMenu = new Menu(FILE);
    menuItem = mkMenuItem(OPEN);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(actionEvent -> {
      presentation.clear();

      Accessor xmlAccessor = new XMLAccessor();
      try {
        xmlAccessor.loadFile(presentation, new File(TESTFILE).getAbsolutePath());
        presentation.setSlideNumber(0);
      } catch (IOException exc) {
        JOptionPane.showMessageDialog(MenuController.this.frame, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
      }
      MenuController.this.frame.repaint();
    });

    menuItem = mkMenuItem(NEW);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(actionEvent -> {
      presentation.clear();
      frame.repaint();
    });

    menuItem = mkMenuItem(SAVE);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(e -> {
      Accessor xmlAccessor = new XMLAccessor();
      try {
        xmlAccessor.saveFile(presentation, SAVEFILE);
      } catch (IOException exc) {
        JOptionPane.showMessageDialog(MenuController.this.frame, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
      }
    });

    fileMenu.addSeparator();
    menuItem = mkMenuItem(EXIT);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(actionEvent -> presentation.exit(0));
    
    add(fileMenu);
    Menu viewMenu = new Menu(VIEW);
    menuItem = mkMenuItem(NEXT);
    viewMenu.add(menuItem);
    
    menuItem.addActionListener(actionEvent -> presentation.nextSlide());

    menuItem = mkMenuItem(PREV);
    viewMenu.add(menuItem);
    
    menuItem.addActionListener(actionEvent -> presentation.prevSlide());

    menuItem = mkMenuItem(GOTO);
    viewMenu.add(menuItem);
    
    menuItem.addActionListener(actionEvent -> {
      String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
      int pageNumber = Integer.parseInt(pageNumberStr);
      presentation.setSlideNumber(pageNumber - 1);
    });

    add(viewMenu);
    Menu helpMenu = new Menu(HELP);
    menuItem = mkMenuItem(ABOUT);
    helpMenu.add(menuItem);

    menuItem.addActionListener(actionEvent -> About.show(MenuController.this.frame));
    setHelpMenu(helpMenu); 
  }

  public MenuItem mkMenuItem(String name) {
    return new MenuItem(name, new MenuShortcut(name.charAt(0)));
  }
}
