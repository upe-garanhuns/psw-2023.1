package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

public interface ISlide {

    void appendItem(ISlideItem anItem);

    void appendText(int level, String message);

    void appendBitmap(int level, String message);

    String getTitle();

    void setTitle(String newTitle);

    ISlideItem getSlideItem(int number);

    List<ISlideItem> getSlideItems();

    float getScale(Rectangle area);

    int getSlideItemHeight(ISlideItem slideItem, Graphics graphics, ImageObserver view, float scale, Style style);

    Style getStyleItem();
    void drawTitle(int x, int y, float scale, Graphics graphics, ImageObserver view);

    void drawSlideItems(int x, int y, float scale, Graphics graphics,ImageObserver view);

    void draw(Graphics graphics, Rectangle area, ImageObserver view);

    int getWidth();

    int getHeight();

}
