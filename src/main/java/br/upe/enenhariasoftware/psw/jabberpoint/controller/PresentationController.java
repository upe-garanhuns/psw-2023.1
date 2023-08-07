package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Slide;

import java.util.ArrayList;

public class PresentationController {

    private Presentation presentation;

    public PresentationController(){
        presentation = new Presentation();
        clear();
    }

    public void prevSlide() {
        if (presentation.getSlideNumber() > 0) {
            presentation.setSlideNumber(presentation.getSlideNumber() - 1);
        }
    }

    public void nextSlide() {
        if (presentation.getSlideNumber() < (presentation.getSize() - 1)) {
            presentation.setSlideNumber(presentation.getSlideNumber() + 1);
        }
    }

    public void clear() {
        ArrayList<Slide> novoShowList = new ArrayList<>();
        presentation.setShowList(novoShowList);
        presentation.setSlideNumber(-1);
    }

    public void append(Slide slide) {
        presentation.getShowList().add(slide);
    }

    public void exit(int n) {
        System.exit(n);
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public int getPresentationSize(){
        return presentation.getSize();
    }
}
