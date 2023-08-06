/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessible;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.view.AboutBox;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class MenuController {

  private Frame parentFrame;
  private Presentation presentation;

  protected static final String PAGENR = "Slide number";
  protected static final String TESTFILE = "classpath:test.xml";
  protected static final String SAVEFILE = "classpath:dump.xml";
  protected static final String IOEX = "IO Exception: ";
  protected static final String LOADERR = "Failed to load";
  protected static final String SAVEERR = "Failed to save";

  public MenuController(Frame frame, Presentation pres) {
    this.parentFrame = frame;
    this.presentation = pres;
  }

  public void openFile() {
    presentation.clear();

    Accessible xmlAccessor = new XMLAccessor();
    try {
      xmlAccessor.loadFile(presentation, new File(TESTFILE).getAbsolutePath());
      presentation.setSlideNumber(0);
    } catch (IOException exc) {
      JOptionPane.showMessageDialog(parentFrame, IOEX + exc, LOADERR,
              JOptionPane.ERROR_MESSAGE);
    }

    parentFrame.repaint();
  }

  public void createFile() {
    presentation.clear();
    parentFrame.repaint();
  }

  public void saveFile() {
    Accessible xmlAccessor = new XMLAccessor();
    try {
      xmlAccessor.saveFile(presentation, SAVEFILE);
    } catch (IOException exc) {
      JOptionPane.showMessageDialog(parentFrame, IOEX + exc, SAVEERR,
              JOptionPane.ERROR_MESSAGE);
    }
  }

  public void exit() {
    System.exit(0);
  }

  public void goToNextSlide() {
    presentation.nextSlide();
  }
  public void goToPreviousSlide() {
    presentation.prevSlide();
  }
  public void goToChosenSlide() {
    String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
    int pageNumber = Integer.parseInt(pageNumberStr);
    presentation.setSlideNumber(pageNumber - 1);
  }

  public void showAboutBox() {
    AboutBox.show(parentFrame);
  }
}
