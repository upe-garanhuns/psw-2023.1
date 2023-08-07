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

import java.util.ArrayList;

import br.upe.enenhariasoftware.psw.jabberpoint.view.Slide;
import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideViewerComponent;

import java.io.Serializable;

public class PresentationController implements Serializable {
	
	public void exit(int n) {
		System.exit(n);
	
	}
}
