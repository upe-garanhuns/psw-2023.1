package br.upe.enenhariasoftware.psw.jabberpoint.view.drawers;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;
import br.upe.enenhariasoftware.psw.jabberpoint.model.BitmapItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideItem;
import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class SlideItemDrawer {

    public static SlideItemDrawer choose(SlideItem slideItem, Graphics graphics, ImageObserver view) {
        if (slideItem instanceof BitmapItem) {
            return new BitmapItemDrawer(graphics, view);
        } else {
            return new TextItemDrawer(graphics);
        }
    }

    public abstract Rectangle getBoundingBox(float scale, Style myStyle, SlideItem slideItem);

    public abstract void draw(int x, int y, float scale, Style myStyle, SlideItem slideItem);


}
