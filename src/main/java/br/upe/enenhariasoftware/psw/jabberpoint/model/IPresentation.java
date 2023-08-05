package br.upe.enenhariasoftware.psw.jabberpoint.model;

import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideViewerComponent;

public interface IPresentation {

    void setSlideNumber(int slideNumber);

    int getSlideNumber();

    String getTitle();

    void setTitle(String newTitle);

    int getSize();

    void setShowView(SlideViewerComponent slideViewerComponent);

    void previousSlide();

    void nextSlide();

    void clear();

    void append(Slide newSlide);

    Slide getSlide(int slideNumber);

    Slide getCurrentSlide();
}
