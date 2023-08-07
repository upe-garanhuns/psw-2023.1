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

import java.io.IOException;

import javax.swing.JOptionPane;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.model.PresentationModel;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;
import br.upe.enenhariasoftware.psw.jabberpoint.model.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideViewerFrame;
import br.upe.enenhariasoftware.psw.jabberpoint.controller.PresentationController;

public class JabberPointApplication {
	public static void main(String[] args) {
		Style.createStyles();

	    PresentationModel presentationModel = new PresentationModel();
	    PresentationController presentation = new PresentationController();
	    
	    new SlideViewerFrame("Jabberpoint 1.6 -", presentationModel);

	    try {
	      if (args.length == 0) {
	        Accessor.getDemoAccessor().loadFile(presentationModel, "");
	      } else {
	        new XMLAccessor().loadFile(presentationModel, args[0]);
	      }

	      presentationModel.setSlideNumber(0);

	    } catch (IOException ex) {
	      JOptionPane.showMessageDialog(null, "IO Error: " + ex, "Jabberpoint Error ", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
