package br.upe.enenhariasoftware.psw.jabberpoint.view.drawers;
import br.upe.enenhariasoftware.psw.jabberpoint.model.*;
import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideItem;

import java.awt.*;
import java.awt.image.ImageObserver;

public class SlideDrawer {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    public void drawSlide(Graphics graphics, Rectangle area, ImageObserver view, Slide slide) {
        SlideItemDrawer slideItemDrawer;
        float scale = getScale(area);

        int y = area.y;

        slideItemDrawer = new TextItemDrawer(graphics);

        SlideItem slideItem = slide.getTextItemTitle();
        Style style = Style.getStyle(slideItem.getLevel());
        slideItemDrawer.draw(area.x, y, scale, style, slideItem);

        y += slideItemDrawer.getBoundingBox(scale, style, slideItem).height;

        for (int number = 0; number < slide.getSize(); number++) {
            slideItem = slide.getSlideItems().get(number);

            style = Style.getStyle(slideItem.getLevel());

            slideItemDrawer = SlideItemDrawer.choose(slideItem,graphics,view);
            slideItemDrawer.draw(area.x, y, scale, style, slideItem);

            y += slideItemDrawer.getBoundingBox(scale, style, slideItem).height;
        }
    }
    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) WIDTH),
                ((float) area.height) / ((float) HEIGHT));
    }
}
