/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.View;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import br.upe.enenhariasoftware.psw.jabberpoint.Controller.KeyController;
import br.upe.enenhariasoftware.psw.jabberpoint.Controller.PresentationController;
import br.upe.enenhariasoftware.psw.jabberpoint.Controller.SlideComponentController;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.PresentationModel;

public class SlideFrameView extends JFrame {

  private PresentationController presController;

  private static final long serialVersionUID = 3227L;

  private static final String JABTITLE = "Jabberpoint 1.6";

  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;

  public SlideFrameView(String title, PresentationModel presentation) {
    super(title);

    presController = new PresentationController(presentation);
    SlideComponentController slideControllerComponent =
        new SlideComponentController(presentation, this);
    presController.getPresentation().setShowView(slideControllerComponent);

    setupWindow(slideControllerComponent, presentation);
  }

  public void setupWindow(SlideComponentController slideControllerComponent,
      PresentationModel presentation) {
    setTitle(JABTITLE);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    getContentPane().add(slideControllerComponent.getSlideViewComponent());
    addKeyListener(new KeyController(presentation));
    setMenuBar(new MenuView(this, presentation));
    setSize(new Dimension(WIDTH, HEIGHT));

    setVisible(true);
  }

}
