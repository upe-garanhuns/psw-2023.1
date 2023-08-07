/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * <p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.views;

import br.upe.enenhariasoftware.psw.jabberpoint.controllers.MenuController;
import br.upe.enenhariasoftware.psw.jabberpoint.controllers.SlideViewerFrame;
import br.upe.enenhariasoftware.psw.jabberpoint.models.Presentation;

import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;

import javax.swing.*;

public class Menu extends MenuBar {
  private static final long serialVersionUID = 227L;
  private final SlideViewerFrame parentFrame;
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

  public Menu(SlideViewerFrame frame, Presentation pres) {
    parentFrame = frame;

    MenuController menuController = new MenuController(parentFrame, pres);

    MenuItem menuItem;
    
    java.awt.Menu fileMenu = new java.awt.Menu(FILE);
    menuItem = mkMenuItem(OPEN);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(e -> menuController.open());

    menuItem = mkMenuItem(NEW);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(e -> menuController.create());

    menuItem = mkMenuItem(SAVE);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(e -> menuController.save());

    fileMenu.addSeparator();
    menuItem = mkMenuItem(EXIT);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(e -> menuController.exit());
    
    add(fileMenu);

    java.awt.Menu viewMenu = new java.awt.Menu(VIEW);
    menuItem = mkMenuItem(NEXT);
    viewMenu.add(menuItem);
    
    menuItem.addActionListener(e -> menuController.next());

    menuItem = mkMenuItem(PREV);
    viewMenu.add(menuItem);
    
    menuItem.addActionListener(e -> menuController.prev());

    menuItem = mkMenuItem(GOTO);
    viewMenu.add(menuItem);
    
    menuItem.addActionListener(e ->{
        String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
        menuController.goTo(pageNumberStr);
    });

    add(viewMenu);
    java.awt.Menu helpMenu = new java.awt.Menu(HELP);
    menuItem = mkMenuItem(ABOUT);
    helpMenu.add(menuItem);

    menuItem.addActionListener(e -> About.show(Menu.this.parentFrame));
    setHelpMenu(helpMenu); 
  }

  public MenuItem mkMenuItem(String name) {
    return new MenuItem(name, new MenuShortcut(name.charAt(0)));
  }
}
