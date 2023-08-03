package br.upe.enenhariasoftware.psw.jabberpoint.view;

import br.upe.enenhariasoftware.psw.jabberpoint.model.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class SlideDrawer {

    public void drawSlide(Graphics g, Rectangle area, ImageObserver view, Slide slide) {
        SlideItemDrawer slideItemDrawer;
        float scale = getScale(area);

        int y = area.y;

        slideItemDrawer = new TextItemDrawer();

        SlideItem slideItem = slide.getTextItemTitle();
        Style style = Style.getStyle(slideItem.getLevel());
        slideItemDrawer.draw(area.x, y, scale, g, style, view, slideItem);

        y += slideItemDrawer.getBoundingBox(g, view, scale, style, slideItem).height;

        for (int number = 0; number < slide.getSize(); number++) {
            slideItem = slide.getSlideItems().get(number);
            style = Style.getStyle(slideItem.getLevel());

            slideItemDrawer = SlideItemDrawer.choose(slideItem);

            slideItemDrawer.draw(area.x, y, scale, g, style, view, slideItem);
            y += slideItemDrawer.getBoundingBox(g, view, scale, style,  slideItem).height;

        }
    }

    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) Slide.WIDTH), ((float) area.height) / ((float) Slide.HEIGHT));
    }

}
