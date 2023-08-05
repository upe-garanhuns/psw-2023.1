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

import br.upe.enenhariasoftware.psw.jabberpoint.controllers.KeyController;
import br.upe.enenhariasoftware.psw.jabberpoint.controllers.PresentationController;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class PresentationViewer extends JFrame {

  private static final long serialVersionUID = 3227L;

  private static final String JABTITLE = "Jabberpoint 1.6";

  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;

  public PresentationViewer(String title, PresentationController presentationController) {
    super(title);

    SlideViewer slideViewer = new SlideViewer(presentationController, this);
    presentationController.getPresentation().setShowView(slideViewer);

    setupWindow(slideViewer, presentationController);
  }

  public void setupWindow(SlideViewer slideViewer, PresentationController presentation) {
    setTitle(JABTITLE);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    getContentPane().add(slideViewer);
    addKeyListener(new KeyController(presentation));
    setMenuBar(new MenuViewer(this, presentation));
    setSize(new Dimension(WIDTH, HEIGHT));

    setVisible(true);
  }

}
