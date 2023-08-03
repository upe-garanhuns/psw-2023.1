package br.upe.enenhariasoftware.psw.jabberpoint.controllers;

import br.upe.enenhariasoftware.psw.jabberpoint.models.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.models.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.models.Savable;
import br.upe.enenhariasoftware.psw.jabberpoint.models.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.views.ErrorViewer;

import java.io.File;
import java.io.IOException;

public class MenuController {

    protected static final String TESTFILE = "src/main/resources/test.xml";
    protected static final String SAVEFILE = "classpath:dump.xml";

    private final SlideViewerFrame parentFrame;
    private final Presentation presentation;

    public MenuController(SlideViewerFrame frame, Presentation pres) {
        parentFrame = frame;
        presentation = pres;
    }

    public void open() {
        presentation.clear();

        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, new File(TESTFILE).getAbsolutePath());
            presentation.setCurrentSlideNumber(0);
            parentFrame.updateFrame(presentation);
        } catch (IOException exc) {
            ErrorViewer.showIOException(exc, this.parentFrame, "IO Exception: ", "Failed to load");
        }
        this.parentFrame.repaint();
    }

    public void create() {
        presentation.clear();
        parentFrame.repaint();
    }

    public void save() {
        Savable xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, SAVEFILE);
        } catch (IOException exc) {
            ErrorViewer.showIOException(exc, this.parentFrame, "IO Exception: " , "Failed to load");
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void next() {
        presentation.nextSlide();
        parentFrame.updateFrame(presentation);
    }

    public void prev() {
        presentation.prevSlide();
        parentFrame.updateFrame(presentation);
    }

    public void goTo(String pageNumber) {
        int intPageNumber = Integer.parseInt(pageNumber);
        presentation.setCurrentSlideNumber(intPageNumber - 1);
        parentFrame.updateFrame(presentation);
    }

}
