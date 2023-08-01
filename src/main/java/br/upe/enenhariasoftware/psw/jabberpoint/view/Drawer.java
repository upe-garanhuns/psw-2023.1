package br.upe.enenhariasoftware.psw.jabberpoint.view;

import br.upe.enenhariasoftware.psw.jabberpoint.model.*;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class Drawer {

    public void drawSlide(Graphics g, Rectangle area, ImageObserver view, Slide slide) {
        float scale = getScale(area);

        int y = area.y;

        SlideItem slideItem = slide.getTextItemTitle();
        Style style = Style.getStyle(slideItem.getLevel());
        drawTextItem(area.x, y, scale, g, style, view, (TextItem) slideItem);

        y += slideItem.getBoundingBox(g, view, scale, style).height;

        for (int number = 0; number < slide.getSize(); number++) {
            slideItem = slide.getSlideItems().get(number);

            style = Style.getStyle(slideItem.getLevel());
            if(slideItem instanceof BitmapItem) {
                drawBitMapItem(area.x, y, scale, g, style, view, (BitmapItem) slideItem);
            } else {
                drawTextItem(area.x, y, scale, g, style, view, (TextItem) slideItem);
            }

            y += slideItem.getBoundingBox(g, view, scale, style).height;
        }
    }

    public void drawBitMapItem (int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer, BitmapItem slideItem) {
        BufferedImage bufferedImage = slideItem.getBufferedImage();

        int width = x + (int) (myStyle.indent * scale);
        int height = y + (int) (myStyle.leading * scale);

        g.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth(observer) * scale),
                (int) (bufferedImage.getHeight(observer) * scale), observer);

    }

    public void drawTextItem(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver o, TextItem textItem) {
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

    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) Slide.WIDTH), ((float) area.height) / ((float) Slide.HEIGHT));
    }

}
