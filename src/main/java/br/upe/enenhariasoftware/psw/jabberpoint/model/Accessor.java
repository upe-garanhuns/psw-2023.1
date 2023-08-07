/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.io.IOException;

import br.upe.enenhariasoftware.psw.jabberpoint.view.DemoPresentation;

public abstract class Accessor {

	public static final String DEMO_NAME = "Demo presentation";
	public static final String DEFAULT_EXTENSION = ".xml";

	public static Accessor getDemoAccessor() {
		return new DemoPresentation();
	}

	protected Accessor() {
	}

	public abstract void loadFile(PresentationModel presentationModel, String fileName) throws IOException;

	public abstract void saveFile(PresentationModel presentationModel, String fileName) throws IOException;

}
