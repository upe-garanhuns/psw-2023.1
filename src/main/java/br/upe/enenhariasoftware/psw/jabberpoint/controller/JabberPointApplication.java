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

import java.io.IOException;

import javax.swing.JOptionPane;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;
import br.upe.enenhariasoftware.psw.jabberpoint.model.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideViewerFrame;
import br.upe.enenhariasoftware.psw.jabberpoint.controller.PresentationController;


public class JabberPointApplication {
	public static void main(String[] args) {
		Style.createStyles();

	    PresentationController presentationController = new PresentationController();

	    new SlideViewerFrame("Jabberpoint 1.6 -", presentationController);

		Accessor accessor;
		if (args.length == 0) {
			accessor = Accessor.getDemoAccessor();
		} else {
			accessor = new XMLAccessor();
		}

		try {
			accessor.loadFile(presentationController, (args.length > 0) ? args[0] : "");

			presentationController.getPresentation().setSlideNumber(0);

		} catch (IOException ex) {
	      JOptionPane.showMessageDialog(null, "IO Error: " + ex, "Jabberpoint Error ", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
