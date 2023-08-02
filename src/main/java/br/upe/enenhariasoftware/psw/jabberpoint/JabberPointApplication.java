/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint;

import br.upe.enenhariasoftware.psw.jabberpoint.models.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.controllers.PresentationController;
import br.upe.enenhariasoftware.psw.jabberpoint.models.Style;
import br.upe.enenhariasoftware.psw.jabberpoint.models.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.views.PresentationViewer;

import java.io.IOException;

import javax.swing.JOptionPane;

public class JabberPointApplication {
	public static void main(String[] args) {
		Style.createStyles();

	    PresentationController presentationController = new PresentationController();

	    new PresentationViewer("Jabberpoint 1.6 -", presentationController);

	    try {
	      if (args.length == 0) {
	        Accessor.getDemoAccessor().loadFile(presentationController, "");
	      } else {
	        new XMLAccessor().loadFile(presentationController, args[0]);
	      }

	      presentationController.getPresentation().setSlideNumber(0);

	    } catch (IOException ex) {
	      JOptionPane.showMessageDialog(null, "IO Error: " + ex, "Jabberpoint Error ", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
