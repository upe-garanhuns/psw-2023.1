/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint;

import java.io.IOException;
import javax.swing.JOptionPane;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.AccessorModel;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.PresentationModel;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.StyleModel;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.XmlAccessorModel;
import br.upe.enenhariasoftware.psw.jabberpoint.View.SlideFrameView;

public class JabberPointApplication {
  public static void main(String[] args) {
    StyleModel.createStyles();

    PresentationModel presentation = new PresentationModel();

    new SlideFrameView("Jabberpoint 1.6 -", presentation); // essa linha de código é curta, mas
                                                             // faz MUITA coisa

    try {
      if (args.length == 0) {
        AccessorModel.getDemoAccessor().loadFile(presentation, "");
      } else {
        new XmlAccessorModel().loadFile(presentation, args[0]);
      }

      presentation.setSlideNumber(0);

    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "IO Error: " + ex, "Jabberpoint Error ",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
