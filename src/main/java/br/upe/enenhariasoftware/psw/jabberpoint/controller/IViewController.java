package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.ISlide;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface IViewController {

    void drawSlide(Graphics graphics, Rectangle area, ImageObserver viewComponent);

    void setSlide(ISlide slide);

}
