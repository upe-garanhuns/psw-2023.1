/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.View;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import br.upe.enenhariasoftware.psw.jabberpoint.Controller.MenuController;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.PresentationModel;

public class MenuView extends MenuBar {

  private static final long serialVersionUID = 227L;

  private Frame mainParent;
  private PresentationModel presentation;
  private MenuItem menuItem;
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

  protected static final String TESTFILE = "classpath:test.xml";
  protected static final String SAVEFILE = "classpath:dump.xml";

  protected static final String IOEX = "IO Exception: ";
  protected static final String LOADERR = "Failed to load";
  protected static final String SAVEERR = "Failed to save";

  public MenuView(Frame frame, PresentationModel presentation) {
    mainParent = frame;
    this.presentation = presentation;
    menuController = new MenuController(this);

    Menu fileMenu = new Menu(FILE);
    menuItem = mkMenuItem(OPEN);
    fileMenu.add(menuItem);
    menuController.actionOpen();

    menuItem = mkMenuItem(NEW);
    fileMenu.add(menuItem);
    menuController.actionNew();

    menuItem = mkMenuItem(SAVE);
    fileMenu.add(menuItem);
    menuController.actionSave();

    fileMenu.addSeparator();

    menuItem = mkMenuItem(EXIT);
    fileMenu.add(menuItem);
    menuController.actionExit();

    add(fileMenu);

    Menu viewMenu = new Menu(VIEW);
    menuItem = mkMenuItem(NEXT);
    viewMenu.add(menuItem);
    menuController.actionNext();

    menuItem = mkMenuItem(PREV);
    viewMenu.add(menuItem);
    menuController.actionPrev();

    menuItem = mkMenuItem(GOTO);
    viewMenu.add(menuItem);
    menuController.actionGoto();

    add(viewMenu);

    Menu helpMenu = new Menu(HELP);
    menuItem = mkMenuItem(ABOUT);
    helpMenu.add(menuItem);
    menuController.actionAbout();

    setHelpMenu(helpMenu);
  }

  public MenuItem mkMenuItem(String name) {
    return new MenuItem(name, new MenuShortcut(name.charAt(0)));
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public static String getAbout() {
    return ABOUT;
  }

  public static String getFile() {
    return FILE;
  }

  public static String getExit() {
    return EXIT;
  }

  public static String getGoto() {
    return GOTO;
  }

  public static String getHelp() {
    return HELP;
  }

  public static String getNew() {
    return NEW;
  }

  public static String getNext() {
    return NEXT;
  }

  public static String getOpen() {
    return OPEN;
  }

  public static String getPagenr() {
    return PAGENR;
  }

  public static String getPrev() {
    return PREV;
  }

  public static String getSave() {
    return SAVE;
  }

  public static String getView() {
    return VIEW;
  }

  public static String getTestfile() {
    return TESTFILE;
  }

  public static String getSavefile() {
    return SAVEFILE;
  }

  public static String getIoex() {
    return IOEX;
  }

  public static String getLoaderr() {
    return LOADERR;
  }

  public static String getSaveerr() {
    return SAVEERR;
  }

  public MenuItem getMenuItem() {
    return menuItem;
  }

  public void setMenuItem(MenuItem menuItem) {
    this.menuItem = menuItem;
  }

  public Frame getMainParent() {
    return mainParent;
  }

  public void setMainParent(Frame mainParent) {
    this.mainParent = mainParent;
  }

  public PresentationModel getPresentation() {
    return presentation;
  }

  public void setPresentation(PresentationModel presentation) {
    this.presentation = presentation;
  }
}
