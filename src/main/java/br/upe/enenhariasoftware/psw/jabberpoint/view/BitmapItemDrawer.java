package br.upe.enenhariasoftware.psw.jabberpoint.view;

import br.upe.enenhariasoftware.psw.jabberpoint.model.BitmapItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class BitmapItemDrawer extends SlideItemDrawer <BitmapItem> {

    private Graphics g;
    private Rectangle area;
    private ImageObserver view;

    public BitmapItemDrawer() {

    }

    @Override
    void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer, BitmapItem slideItem) {
        BufferedImage bufferedImage = slideItem.getBufferedImage();

        int width = x + (int) (myStyle.indent * scale);
        int height = y + (int) (myStyle.leading * scale);

        g.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth(observer) * scale),
                (int) (bufferedImage.getHeight(observer) * scale), observer);
    }

    @Override
    Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle, BitmapItem bitmapItem) {
        BufferedImage bufferedImage = bitmapItem.getBufferedImage();

        return new Rectangle((int) (myStyle.indent * scale), 0, (int) (bufferedImage.getWidth(view) * scale),
                ((int) (myStyle.leading * scale)) + (int) (bufferedImage.getHeight(view) * scale));
    }
}
