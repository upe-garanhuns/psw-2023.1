package br.upe.enenhariasoftware.psw.jabberpoint.controllers;

import br.upe.enenhariasoftware.psw.jabberpoint.models.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.models.XMLAccessor;

import java.io.File;
import java.io.IOException;

public class MenuController {

    private static final Accessor xmlAccessor = new XMLAccessor();
    private static final String TESTFILE = "src/main/resources/test.xml";
    private static final String SAVEFILE = "src/main/resources/dump.xml";

    public static void loadPresentation (PresentationController presentationController) {
        try {
            xmlAccessor.loadFile(presentationController, new File(TESTFILE).getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        presentationController.getPresentation().setSlideNumber(0);
    }

    public static void savePresentation(PresentationController presentationController) {
        try {
            xmlAccessor.saveFile(presentationController, SAVEFILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
