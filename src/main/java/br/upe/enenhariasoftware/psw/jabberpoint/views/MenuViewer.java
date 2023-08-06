/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.views;

import br.upe.enenhariasoftware.psw.jabberpoint.controllers.MenuController;
import br.upe.enenhariasoftware.psw.jabberpoint.controllers.PresentationController;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;

public class MenuViewer extends MenuBar {

  private static final long serialVersionUID = 227L;

  private Frame frame;
  private PresentationController presentationController;
  private MenuController menuController;


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


  public MenuViewer(Frame frame, PresentationController presentationController) {
    this.frame = frame;
    this.menuController = new MenuController(frame);
    this.presentationController = presentationController;

    createFileMenu();
    createViewMenu();
    createHelpMenu();

  }

  private void createFileMenu() {

    Menu fileMenu = new Menu(FILE);
    MenuItem fileMenuItem = mkMenuItem(OPEN);
    fileMenu.add(fileMenuItem);

    fileMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentationController.clear();
        MenuController menuController = new MenuController(frame);
        menuController.loadPresentation(presentationController);

        MenuViewer.this.frame.repaint();
      }
    });

    fileMenuItem = mkMenuItem(NEW);
    fileMenu.add(fileMenuItem);

    fileMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentationController.clear();
        MenuViewer.this.frame.repaint();
      }
    });

    fileMenuItem = mkMenuItem(SAVE);
    fileMenu.add(fileMenuItem);

    fileMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        MenuController menuController = new MenuController(frame);
        menuController.savePresentation(presentationController);
      }
    });

    fileMenu.addSeparator();

    fileMenuItem = mkMenuItem(EXIT);
    fileMenu.add(fileMenuItem);

    fileMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentationController.exit(0);
      }
    });

    add(fileMenu);
  }

  private void createViewMenu() {
    Menu viewMenu = new Menu(VIEW);
    MenuItem viewMenuItem = mkMenuItem(NEXT);
    viewMenu.add(viewMenuItem);

    viewMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentationController.nextSlide();
      }
    });

    viewMenuItem = mkMenuItem(PREV);
    viewMenu.add(viewMenuItem);

    viewMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        presentationController.prevSlide();
      }
    });

    viewMenuItem = mkMenuItem(GOTO);
    viewMenu.add(viewMenuItem);

    viewMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);

        menuController.setSlideNumber(pageNumber, presentationController);
      }
    });

    add(viewMenu);
  }
  
  private void createHelpMenu() {
    Menu helpMenu = new Menu(HELP);

    MenuItem helpMenuItem = mkMenuItem(ABOUT);
    helpMenu.add(helpMenuItem);

    helpMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        About.show(MenuViewer.this.frame);
      }
    });

    setHelpMenu(helpMenu);
  }

  private MenuItem mkMenuItem(String name) {
    return new MenuItem(name, new MenuShortcut(name.charAt(0)));
  }
}
