package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Slide;

import java.awt.*;
import java.awt.image.ImageObserver;

public class ViewController implements IViewController{

    private Slide slide;

    public void drawSlide(Graphics graphics, Rectangle area, ImageObserver viewComponent){

        slide.draw(graphics, area, viewComponent);

    }

    public void setSlide(Slide slide){

        this.slide = slide;

    }

}
