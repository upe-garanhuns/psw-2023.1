/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.view.ErrorView;
import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideViewerFrame;

import java.io.IOException;

public class JabberPointApplication {
  public static void main(String[] args) {
    Style.createStyles();

    Presentation presentation = new Presentation();
    FrameController frameController = new FrameController(presentation);

    new SlideViewerFrame("Jabberpoint 1.6 -", presentation, frameController);

    try {
      if (args.length == 0) {
        Accessor.getDemoAccessor().loadFile(presentation, "");
      } else {
        new XMLAccessor().loadFile(presentation, args[0]);
      }

      presentation.setSlideNumber(0);

    } catch (IOException ex) {
      ErrorView.showExceptionMessage(ex);
    }
  }
}
