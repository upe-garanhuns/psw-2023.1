/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.view;

import br.upe.enenhariasoftware.psw.jabberpoint.controller.FrameController;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class SlideViewerFrame extends JFrame {

  private static final long serialVersionUID = 3227L;

  private static final String JABTITLE = "Jabberpoint 1.6";

  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;
  private final FrameController frameController;

  public SlideViewerFrame(String title, Presentation presentation, FrameController frameController) {
    super(title);
    this.frameController = frameController;

    SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
    presentation.setShowView(slideViewerComponent);

    setupWindow(slideViewerComponent, presentation);
  }

  public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
    setTitle(JABTITLE);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        frameController.windowClosing();
      }
    });

    getContentPane().add(slideViewerComponent);
    addKeyListener(frameController.createKeyController(presentation));
    setMenuBar(frameController.createMenuControllerMenuBar(this, presentation));
    setSize(new Dimension(WIDTH, HEIGHT));

    setVisible(true);
  }

}
