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

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Slide;
import br.upe.enenhariasoftware.psw.jabberpoint.model.TextItem;

public class TextItemViewer implements SlideItemViewer {
    private final TextItem textItem;

    public TextItemViewer(TextItem textItem) {
        this.textItem = textItem;
    }

    public AttributedString getAttributedString(Style style, float scale) {
        AttributedString attrStr = new AttributedString(textItem.getText());

        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, textItem.getText().length());

        return attrStr;
    }

    private List<TextLayout> getLayouts(Graphics g, Style s, float scale) {
        List<TextLayout> layouts = new ArrayList<>();

        AttributedString attrStr = getAttributedString(s, scale);
        Graphics2D g2d = (Graphics2D) g;

        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

        float wrappingWidth = (Slide.WIDTH - s.indent) * scale;

        while (measurer.getPosition() < textItem.getText().length()) {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }

        return layouts;
    }

    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
        List<TextLayout> layouts = getLayouts(g, style, scale);

        int xsize = 0;
        int ysize = (int) (style.leading * scale);

        for (TextLayout layout : layouts) {
            Rectangle2D bounds = layout.getBounds();

            if (bounds.getWidth() > xsize) {
                xsize = (int) bounds.getWidth();
            }

            if (bounds.getHeight() > 0) {
                ysize += (int) bounds.getHeight();
            }
            ysize += (int) (layout.getLeading() + layout.getDescent());
        }

        return new Rectangle((int) (style.indent * scale), 0, xsize, ysize);
    }

    @Override
    public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
        if (textItem.getText() == null || textItem.getText().isEmpty()) {
            return;
        }

        List<TextLayout> layouts = getLayouts(g, style, scale);
        Point pen = new Point(x + (int) (style.indent * scale), y + (int) (style.leading * scale));

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(style.color);

        for (TextLayout layout : layouts) {
            pen.y += (int) layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);

            pen.y += (int) layout.getDescent();
        }
    }
}