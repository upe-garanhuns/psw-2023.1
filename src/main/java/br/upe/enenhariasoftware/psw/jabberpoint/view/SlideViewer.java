/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 *<p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.view;

import br.upe.enenhariasoftware.psw.jabberpoint.model.BitmapItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Slide;
import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.TextItem;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class SlideViewer {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    private final Slide slide;

    public SlideViewer(Slide slide) {
        this.slide = slide;
    }

    public static SlideItemViewer getViewer(SlideItem slideItem) {
        if (slideItem instanceof TextItem) {
            return new TextItemViewer((TextItem) slideItem);
        } else if (slideItem instanceof BitmapItem) {
            return new BitmapItemViewer((BitmapItem) slideItem);
        } else {
            return null;
        }
    }

    public static SlideViewer getViewer(Slide slide) {
        return new SlideViewer(slide);
    }

    public void draw(Graphics g, Rectangle area, ImageObserver view) {
        float scale = getScale(area);

        int y = area.y;

        SlideItem slideItem = new TextItem(0, slide.getTitle());
        Style style = Style.getStyle(slideItem.getLevel());
        SlideItemViewer slideItemViewer = getViewer(slideItem);
        if (slideItemViewer != null) {
            slideItemViewer.draw(area.x, y, scale, g, style, view);
            y += slideItemViewer.getBoundingBox(g, view, scale, style).height;
        }

        for (int number = 0; number < slide.getSize(); number++) {
            slideItem = slide.getSlideItems().get(number);
            slideItemViewer = getViewer(slideItem);
            style = Style.getStyle(slideItem.getLevel());
            if (slideItemViewer != null) {
                slideItemViewer.draw(area.x, y, scale, g, style, view);
                y += slideItemViewer.getBoundingBox(g, view, scale, style).height;
            }
        }
    }


    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) WIDTH),
                ((float) area.height) / ((float) HEIGHT));
    }
}
