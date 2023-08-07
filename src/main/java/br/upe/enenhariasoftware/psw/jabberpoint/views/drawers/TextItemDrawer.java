package br.upe.enenhariasoftware.psw.jabberpoint.views.drawers;

import br.upe.enenhariasoftware.psw.jabberpoint.models.Style;
import br.upe.enenhariasoftware.psw.jabberpoint.models.slideitems.SlideItem;
import br.upe.enenhariasoftware.psw.jabberpoint.models.slideitems.TextItem;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class TextItemDrawer extends SlideItemDrawer {

    private final Graphics graphics;

    public TextItemDrawer(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    void draw(int x, int y, float scale, Style myStyle, SlideItem textItem) {
        String text = ((TextItem) textItem).getText();

        if (text == null || text.length() == 0) {
            return;
        }

        List<TextLayout> layouts = getLayouts(this.graphics, myStyle, scale, text);
        Point pen = new Point(x + (int) (myStyle.getIndent() * scale), y + (int) (myStyle.getLeading() * scale));

        Graphics2D g2d = (Graphics2D) this.graphics;
        g2d.setColor(myStyle.getColor());

        for (TextLayout layout : layouts) {
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);

            pen.y += layout.getDescent();
        }
    }

    @Override
    Rectangle getBoundingBox(float scale, Style myStyle, SlideItem textItem) {
        List<TextLayout> layouts = getLayouts(graphics, myStyle, scale, ((TextItem) textItem).getText());

        int xsize = 0;
        int ysize = (int) (myStyle.getLeading() * scale);

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

        return new Rectangle((int) (myStyle.getIndent() * scale), 0, xsize, ysize);
    }

    private List<TextLayout> getLayouts(Graphics graphics, Style style, float scale, String text) {
        List<TextLayout> layouts = new ArrayList<>();

        AttributedString attrStr = getAttributedString(style, scale, text);
        Graphics2D g2d = (Graphics2D) graphics;

        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

        float wrappingWidth = (SlideDrawer.WIDTH - style.getIndent()) * scale;

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
