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

import br.upe.enenhariasoftware.psw.jabberpoint.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideViewerComponent;
import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideViewerFrame;
import br.upe.enenhariasoftware.psw.jabberpoint.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;

import java.io.IOException;

import javax.swing.JOptionPane;

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
	      JOptionPane.showMessageDialog(null, "IO Error: " + ex, "Jabberpoint Error ", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
