/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * <p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint;

import br.upe.enenhariasoftware.psw.jabberpoint.controllers.SlideViewerFrame;
import br.upe.enenhariasoftware.psw.jabberpoint.models.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.models.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.models.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.models.Style;
import br.upe.enenhariasoftware.psw.jabberpoint.views.ErrorViewer;

import java.io.IOException;

public class JabberPointApplication {
	public static void main(String[] args) {
		Style.createStyles();

	    Presentation presentation = new Presentation();

	    SlideViewerFrame frame = new SlideViewerFrame("Jabberpoint 1.6 -", presentation);

	    try {
	      if (args.length == 0) {
	        Accessor.getDemoAccessor().loadFile(presentation, "");
	      } else {
	        new XMLAccessor().loadFile(presentation, args[0]);
	      }

	      presentation.setCurrentSlideNumber(0);
		  frame.updateFrame(presentation);

	    } catch (IOException ex) {
			ErrorViewer.showIOException(ex, null, "IO Error: ", "Jabberpoint Error ");
	    }
	}
}
