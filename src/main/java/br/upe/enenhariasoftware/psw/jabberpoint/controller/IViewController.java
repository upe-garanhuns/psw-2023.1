package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Slide;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface IViewController {

    void drawSlide(Graphics graphics, Rectangle area, ImageObserver viewComponent);

    void setSlide(Slide slide);

}
