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

import java.awt.Frame;

import javax.swing.JOptionPane;

public class AboutBox {

	public static void show(Frame parent) {
		JOptionPane.showMessageDialog(parent,
				"JabberPoint Copyright (c) 1995-now by Ian F. Darwin, ian@darwinsys.com.\n"
						+ "Adaptado por Helaine Barreiros para Universidade de Pernambuco, 2023.1. \n"
						+ "A cópia original do autor está disponível em http://www.darwinsys.com/",
				"Sobre JabberPoint", JOptionPane.INFORMATION_MESSAGE);
	}
}
