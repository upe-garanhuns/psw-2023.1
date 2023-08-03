/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * <p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.controllers;

import br.upe.enenhariasoftware.psw.jabberpoint.models.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.views.Menu;
import br.upe.enenhariasoftware.psw.jabberpoint.views.SlideViewerComponent;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class SlideViewerFrame extends JFrame {

  private static final long serialVersionUID = 3227L;
  private static final String JABTITLE = "Jabberpoint 1.6";
  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;
  private final SlideViewerComponent slideViewerComponent;

  public SlideViewerFrame(String title, Presentation presentation) {
    super(title);

    slideViewerComponent = new SlideViewerComponent(presentation, this);

    setupWindow(slideViewerComponent, presentation);
  }

  public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
    setTitle(JABTITLE);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    getContentPane().add(slideViewerComponent);
    addKeyListener(new KeyController(presentation, this));
    setMenuBar(new Menu(this, presentation));
    setSize(new Dimension(WIDTH, HEIGHT));

    setVisible(true);
  }

  public void updateFrame(Presentation pres) {
    slideViewerComponent.update(pres);
  }

}
