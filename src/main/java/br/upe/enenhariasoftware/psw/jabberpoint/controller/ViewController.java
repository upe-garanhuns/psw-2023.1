package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.ISlide;

import java.awt.*;
import java.awt.image.ImageObserver;

public class ViewController implements IViewController{

    private ISlide slide;

    public void drawSlide(Graphics graphics, Rectangle area, ImageObserver viewComponent){

        slide.draw(graphics, area, viewComponent);

    }

    public void setSlide(ISlide slide){

        this.slide = slide;

    }

}
