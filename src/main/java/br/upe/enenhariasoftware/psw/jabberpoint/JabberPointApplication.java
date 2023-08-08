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

public class JabberPointApplication {
	
	public static void main(String[] args) {
		Style.createStyles();
		PresentationModel presentationModel = new PresentationModel();
		new SlideViewerFrame("Jabberpoint 1.6 -", presentationModel);
		loadPresentation(presentationModel, args);
	}

	private static void loadPresentation(PresentationModel presentationModel, String[] args) {
		try {
			Accessor accessor;
			if (args.length == 0) {
				accessor = Accessor.getDemoAccessor();
			} else {
				accessor = new XMLAccessor();
			}
			accessor.loadFile(presentationModel, args.length > 0 ? args[0] : "");
			presentationModel.setSlideNumber(0);
		} catch (IOException ex) {
			showErrorMessage("IO Error: " + ex, "Jabberpoint Error ");
		}
	}

	private static void showErrorMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
}