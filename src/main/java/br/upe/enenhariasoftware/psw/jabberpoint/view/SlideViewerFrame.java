/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import br.upe.enenhariasoftware.psw.jabberpoint.controller.KeyController;
import br.upe.enenhariasoftware.psw.jabberpoint.controller.MenuController;
import br.upe.enenhariasoftware.psw.jabberpoint.model.PresentationModel;

public class SlideViewerFrame extends JFrame {

	private static final long serialVersionUID = 3227L;

	private static final String JABTITLE = "Jabberpoint 1.6";

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;

	public SlideViewerFrame(String title, PresentationModel presentationModel) {
		super(title);

		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentationModel, this);
		presentationModel.setShowView(slideViewerComponent);

		setupWindow(presentationModel);
	}

	public void setupWindow(PresentationModel presentationModel) {
		setTitle(JABTITLE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentationModel, this);
		getContentPane().add(slideViewerComponent);
		addKeyListener(new KeyController(presentationModel));
		setMenuBar(new MenuController(this, presentationModel));
		setSize(new Dimension(WIDTH, HEIGHT));

		setVisible(true);
	}

}