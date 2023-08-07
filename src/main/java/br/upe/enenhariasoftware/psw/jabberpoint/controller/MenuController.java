/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.controller;

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

import java.io.Serializable;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.model.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.view.About;


public class MenuController extends MenuBar implements Serializable{

  private static final long serialVersionUID = 227L;
  
  private Frame parentMenu;
  private transient Presentation presentation;

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
    parentMenu = frame;
    presentation = pres;
    
    MenuItem menuItem = mkMenuItem(OPEN);
    
    Menu fileMenu = new Menu(FILE);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentation.clear();
        
        Accessor xmlAccessor = new XMLAccessor();
        try {
          xmlAccessor.loadFile(presentation, new File(TESTFILE).getAbsolutePath());
          presentation.setSlideNumber(0);
        } catch (IOException exc) {
          JOptionPane.showMessageDialog(parentMenu, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        
        parentMenu.repaint();
      }
    });

    MenuItem menuItemNew = mkMenuItem(NEW);
    
    fileMenu.add(menuItemNew);
    
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentation.clear();
        parentMenu.repaint();
      }
    });

    MenuItem menuItemSave = mkMenuItem(SAVE);
    
    fileMenu.add(menuItemSave);
    
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Accessor xmlAccessor = new XMLAccessor();
        try {
          xmlAccessor.saveFile(presentation, SAVEFILE);
        } catch (IOException exc) {
          JOptionPane.showMessageDialog(parentMenu, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    
    fileMenu.addSeparator();

    MenuItem menuItemExit = mkMenuItem(EXIT);
    
    fileMenu.add(menuItemExit);
    
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentation.exit(0);
      }
    });
    
    add(fileMenu);
    
    Menu viewMenu = new Menu(VIEW);

    MenuItem menuItemNext = mkMenuItem(NEXT);

    viewMenu.add(menuItemNext);
    
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentation.nextSlide();
      }
    });

    MenuItem menuItemPrev = mkMenuItem(PREV);

    viewMenu.add(menuItemPrev);
    
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentation.prevSlide();
      }
    });

    MenuItem menuItemGoTo = mkMenuItem(GOTO);

    viewMenu.add(menuItemGoTo);
    
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        presentation.setSlideNumber(pageNumber - 1);
      }
    });
    
    add(viewMenu);
    
    Menu helpMenu = new Menu(HELP);

    MenuItem menuItemAbout = mkMenuItem(ABOUT);

    helpMenu.add(menuItemAbout);
    
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        About.show(parentMenu);
      }
    });
    
    setHelpMenu(helpMenu); 
  }

  public MenuItem mkMenuItem(String name) {
    return new MenuItem(name, new MenuShortcut(name.charAt(0)));
  }
}
