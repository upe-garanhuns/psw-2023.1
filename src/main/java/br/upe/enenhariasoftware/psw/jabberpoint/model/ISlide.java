package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface ISlide {

    public void appendItem(SlideItem anItem);

    void appendText(int level, String message);

    void appendBitmap(int level, String message);

    String getTitle();

    void setTitle(String newTitle);

    SlideItem getSlideItem(int number);

    float getScale(Rectangle area);

    int getSlideItemHeight(SlideItem slideItem, Graphics graphics, ImageObserver view, float scale, Style style);

    void draw(Graphics graphics, Rectangle area, ImageObserver view);

    int getWidth();

    int getHeight();

}
