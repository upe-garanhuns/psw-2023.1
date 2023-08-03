/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * <p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Savable;
import br.upe.enenhariasoftware.psw.jabberpoint.model.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.view.About;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class MenuController extends MenuBar {
  private static final long serialVersionUID = 227L;
  private final SlideViewerFrame parentFrame;
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

  public MenuController(SlideViewerFrame frame, Presentation pres) {
    parentFrame = frame;
    presentation = pres;
    
    MenuItem menuItem;
    
    Menu fileMenu = new Menu(FILE);
    menuItem = mkMenuItem(OPEN);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(e -> {
        presentation.clear();

        Accessor xmlAccessor = new XMLAccessor();
        try {
          xmlAccessor.loadFile(presentation, new File(TESTFILE).getAbsolutePath());
          presentation.setCurrentSlideNumber(0);
          parentFrame.updateFrame(presentation);
        } catch (IOException exc) {
          JOptionPane.showMessageDialog(MenuController.this.parentFrame, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        MenuController.this.parentFrame.repaint();
    });

    menuItem = mkMenuItem(NEW);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(e -> {
        presentation.clear();
        parentFrame.repaint();
    });

    menuItem = mkMenuItem(SAVE);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(e -> {
        Savable xmlAccessor = new XMLAccessor();
        try {
          xmlAccessor.saveFile(presentation, SAVEFILE);
        } catch (IOException exc) {
          JOptionPane.showMessageDialog(MenuController.this.parentFrame, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    });

    fileMenu.addSeparator();
    menuItem = mkMenuItem(EXIT);
    fileMenu.add(menuItem);
    
    menuItem.addActionListener(e -> System.exit(0));
    
    add(fileMenu);
    Menu viewMenu = new Menu(VIEW);
    menuItem = mkMenuItem(NEXT);
    viewMenu.add(menuItem);
    
    menuItem.addActionListener(e -> {
        presentation.nextSlide();
        parentFrame.updateFrame(presentation);
    });

    menuItem = mkMenuItem(PREV);
    viewMenu.add(menuItem);
    
    menuItem.addActionListener(e -> {
        presentation.prevSlide();
        parentFrame.updateFrame(presentation);
    });

    menuItem = mkMenuItem(GOTO);
    viewMenu.add(menuItem);
    
    menuItem.addActionListener(e ->{
        String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        presentation.setCurrentSlideNumber(pageNumber - 1);
        parentFrame.updateFrame(presentation);
    });

    add(viewMenu);
    Menu helpMenu = new Menu(HELP);
    menuItem = mkMenuItem(ABOUT);
    helpMenu.add(menuItem);

    menuItem.addActionListener(e -> About.show(MenuController.this.parentFrame));
    setHelpMenu(helpMenu); 
  }

  public MenuItem mkMenuItem(String name) {
    return new MenuItem(name, new MenuShortcut(name.charAt(0)));
  }
}
