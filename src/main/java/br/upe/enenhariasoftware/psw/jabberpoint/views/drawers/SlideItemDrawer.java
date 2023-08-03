package br.upe.enenhariasoftware.psw.jabberpoint.views.drawers;

import br.upe.enenhariasoftware.psw.jabberpoint.models.slideitems.BitmapItem;
import br.upe.enenhariasoftware.psw.jabberpoint.models.slideitems.SlideItem;
import br.upe.enenhariasoftware.psw.jabberpoint.models.Style;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class SlideItemDrawer {

    public static SlideItemDrawer choose(SlideItem slideItem, Graphics graphics, ImageObserver view) {
        if(slideItem instanceof BitmapItem) {
            return new BitmapItemDrawer(graphics, view);
        } else {
            return new TextItemDrawer(graphics);
        }
    }

    abstract void draw(int x, int y, float scale, Style myStyle, SlideItem slideItem);

    abstract Rectangle getBoundingBox(float scale, Style myStyle, SlideItem slideItem);

}
