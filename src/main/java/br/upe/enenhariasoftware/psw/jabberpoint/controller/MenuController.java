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



import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessor;

public class MenuController {

  private static final Accessor xmlAccessor = new XMLAccessor();
  private Frame frame;

  private static final String TESTFILE = "src/main/resources/test.xml";
  private static final String SAVEFILE = "src/main/resources/dump.xml";
  protected static final String IOEX = "IO Exception: ";
  protected static final String LOADERR = "Failed to load";
  protected static final String SAVEERR = "Failed to save";
  protected static final String SLIDEERR = "Invalid slide number";


  public MenuController(Frame frame) {
    this.frame = frame;
  }

  public void loadPresentation(PresentationController presentationController) {
    try {
      xmlAccessor.loadFile(presentationController, new File(TESTFILE).getAbsolutePath());
    } catch (IOException exc) {
      JOptionPane.showMessageDialog(frame, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
    }
    presentationController.getPresentation().setSlideNumber(0);
  }

  public void savePresentation(PresentationController presentationController) {
    try {
      xmlAccessor.saveFile(presentationController, SAVEFILE);
    } catch (IOException exc) {
      JOptionPane.showMessageDialog(frame, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
    }
  }

  public void setSlideNumber(int pageNumber, PresentationController presentationController) {
    if (pageNumber <= presentationController.getPresentationSize()) {
      presentationController.getPresentation().setSlideNumber(pageNumber - 1);
    } else {
      JOptionPane.showMessageDialog(frame, SLIDEERR,"Error",  JOptionPane.ERROR_MESSAGE);
    }
  }

}