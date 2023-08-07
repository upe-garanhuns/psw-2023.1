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
		slide.append(2, "Copyright (c) 1996-now: Ian Darwin");
		slide.append(2, "Copyright (c) 2023-now: Helaine Lins");
		slide.append(4, "Memo demo presentation");
		slide.append(1, "Navegation:");
		slide.append(3, "Next slide: PgDn ou Enter");
		slide.append(3, "Previous slide: PgUp ou up-arrow");
		slide.append(3, "Stop: q ou Q");
		presentation.append(slide);

		slide = new Slide();
		slide.setTitle("Presentation levels demo");
		slide.append(1, "Level 1");
		slide.append(2, "Level 2");
		slide.append(1, "Style Level 1");
		slide.append(2, "Style Level  2");
		slide.append(3, "Style Level 3");
		slide.append(4, "Style Level 4");
		presentation.append(slide);

		slide = new Slide();
		slide.setTitle("Second Slide");
		slide.append(1, "To open a new presentation,");
		slide.append(2, "Go to File->Open.");
		slide.append(1, " ");
		slide.append(1, "End");
		URL resource = this.getClass().getClassLoader().getResource("JabberPoint.jpg");
		slide.append(new BitmapItem(1, resource.getPath()));
		presentation.append(slide);
	}

	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new IllegalStateException("Cannot save demo presentation!");
	}
}
