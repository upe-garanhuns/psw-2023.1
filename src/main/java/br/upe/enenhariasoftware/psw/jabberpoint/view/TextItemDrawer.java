package br.upe.enenhariasoftware.psw.jabberpoint.view;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Slide;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;
import br.upe.enenhariasoftware.psw.jabberpoint.model.TextItem;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class TextItemDrawer extends SlideItemDrawer <TextItem> {
    @Override
    void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer, TextItem textItem) {
        String text = textItem.getText();

        if (text == null || text.length() == 0) {
            return;
        }

        List<TextLayout> layouts = getLayouts(g, myStyle, scale, text);
        Point pen = new Point(x + (int) (myStyle.indent * scale), y + (int) (myStyle.leading * scale));

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(myStyle.color);

        for (TextLayout layout : layouts) {
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);

            pen.y += layout.getDescent();
        }
    }

    @Override
    Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle, TextItem textItem) {
        List<TextLayout> layouts = getLayouts(g, myStyle, scale, textItem.getText());

        int xsize = 0;
        int ysize = (int) (myStyle.leading * scale);

        for (TextLayout layout : layouts) {
            Rectangle2D bounds = layout.getBounds();

            if (bounds.getWidth() > xsize) {
                xsize = (int) bounds.getWidth();
            }

            if (bounds.getHeight() > 0) {
                ysize += bounds.getHeight();
            }
            ysize += layout.getLeading() + layout.getDescent();
        }

        return new Rectangle((int) (myStyle.indent * scale), 0, xsize, ysize);
    }

    private List<TextLayout> getLayouts(Graphics g, Style s, float scale, String text) {
        List<TextLayout> layouts = new ArrayList<>();

        AttributedString attrStr = getAttributedString(s, scale, text);
        Graphics2D g2d = (Graphics2D) g;

        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

        float wrappingWidth = (Slide.WIDTH - s.indent) * scale;

        while (measurer.getPosition() < text.length()) {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }

        return layouts;
    }

    public AttributedString getAttributedString(Style style, float scale, String text) {
        AttributedString attrStr = new AttributedString(text);

        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());

        return attrStr;
    }

}
