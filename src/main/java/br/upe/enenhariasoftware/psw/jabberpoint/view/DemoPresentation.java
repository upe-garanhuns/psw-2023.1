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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.model.PresentationModel;
import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideModel;
import br.upe.enenhariasoftware.psw.jabberpoint.controller.BitmapItemController;

public class DemoPresentation extends Accessor {

    public void loadFile(PresentationModel presentationModel, String unusedFilename) throws FileNotFoundException {

        presentationModel.setTitle("Demo presentation");

        SlideModel slideModel;
        slideModel = new SlideModel();

        slideModel.setTitle("JabberPoint");
        slideModel.append(2, "Copyright (c) 1996-now: Ian Darwin");
        slideModel.append(2, "Copyright (c) 2023-now: Helaine Lins");
        slideModel.append(4, "Memo demo presentation");
        slideModel.append(1, "Navegation:");
        slideModel.append(3, "Next slide: PgDn ou Enter");
        slideModel.append(3, "Previous slide: PgUp ou up-arrow");
        slideModel.append(3, "Stop: q ou Q");
        presentationModel.append(slideModel);

        slideModel = new SlideModel();
        slideModel.setTitle("Presentation levels demo");
        slideModel.append(1, "Level 1");
        slideModel.append(2, "Level 2");
        slideModel.append(1, "Style Level 1");
        slideModel.append(2, "Style Level  2");
        slideModel.append(3, "Style Level 3");
        slideModel.append(4, "Style Level 4");
        presentationModel.append(slideModel);

        slideModel = new SlideModel();
        slideModel.setTitle("Second Slide");
        slideModel.append(1, "To open a new presentation,");
        slideModel.append(2, "Go to File->Open.");
        slideModel.append(1, " ");
        slideModel.append(1, "End");
        URL resource = this.getClass().getClassLoader().getResource("JabberPoint.jpg");
        slideModel.append(new BitmapItemController(1, resource.getPath()));
        presentationModel.append(slideModel);
    }

    public void saveFile(PresentationModel presentationModel, String fileName) throws IOException {
        throw new IllegalStateException("Cannot save demo presentation!");
    }
}