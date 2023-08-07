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

import java.awt.Frame;

import javax.swing.JOptionPane;

public class About {

	public static void show(Frame parent) {
		JOptionPane.showMessageDialog(parent,
				"JabberPoint Copyright (c) 1995-now by Ian F. Darwin, ian@darwinsys.com.\n"
						+ "Adapted by Helaine Lins - Universidade de Pernambuco, 2023.1. \n"
						+ "The author's original copy is available at http://www.darwinsys.com/",
				"About JabberPoint", JOptionPane.INFORMATION_MESSAGE);
	}
}
