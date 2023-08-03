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

import java.io.FileNotFoundException;
import java.net.URL;

public class DemoPresentation extends Accessor {

	public void loadFile(Presentation presentation, String unusedFilename) throws FileNotFoundException {

		presentation.setTitle("Demo presentation");

		Slide slide;
		slide = new Slide();

		slide.setTitle("JabberPoint");
		slide.appendText(2, "Copyright (c) 1996-now: Ian Darwin");
		slide.appendText(2, "Copyright (c) 2023-now: Helaine Lins");
		slide.appendText(4, "Memo demo presentation");
		slide.appendText(1, "Navegation:");
		slide.appendText(3, "Next slide: PgDn ou Enter");
		slide.appendText(3, "Previous slide: PgUp ou up-arrow");
		slide.appendText(3, "Stop: q ou Q");
		presentation.append(slide);

		slide = new Slide();
		slide.setTitle("Presentation levels demo");
		slide.appendText(1, "Level 1");
		slide.appendText(2, "Level 2");
		slide.appendText(1, "Style Level 1");
		slide.appendText(2, "Style Level  2");
		slide.appendText(3, "Style Level 3");
		slide.appendText(4, "Style Level 4");
		presentation.append(slide);

		slide = new Slide();
		slide.setTitle("Second Slide");
		slide.appendText(1, "To open a new presentation,");
		slide.appendText(2, "Go to File->Open.");
		slide.appendText(1, " ");
		slide.appendText(1, "End");
		URL resource = this.getClass().getClassLoader().getResource("JabberPoint.jpg");
		slide.appendBitmap(1, resource.getPath());
		presentation.append(slide);
	}

	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new IllegalStateException("Cannot save demo presentation!");
	}

}