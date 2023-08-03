package br.upe.enenhariasoftware.psw.jabberpoint.view;

import br.upe.enenhariasoftware.psw.jabberpoint.model.BitmapItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class SlideItemDrawer <T extends SlideItem> {

    private Graphics g;
    private Rectangle area;
    private ImageObserver view;

    public static SlideItemDrawer choose(SlideItem slideItem) {
        if(slideItem instanceof BitmapItem) {
            return new BitmapItemDrawer();
        } else {
            return new TextItemDrawer();
        }
    }

    abstract void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer, T slideItem);

    abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle, T slideItem);

}
