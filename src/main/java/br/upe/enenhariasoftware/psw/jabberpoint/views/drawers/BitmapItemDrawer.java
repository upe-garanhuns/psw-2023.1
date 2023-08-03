package br.upe.enenhariasoftware.psw.jabberpoint.views.drawers;

import br.upe.enenhariasoftware.psw.jabberpoint.models.slideitems.BitmapItem;
import br.upe.enenhariasoftware.psw.jabberpoint.models.Style;
import br.upe.enenhariasoftware.psw.jabberpoint.models.slideitems.SlideItem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class BitmapItemDrawer extends SlideItemDrawer {

    private Graphics g;
    private ImageObserver view;

    public BitmapItemDrawer(Graphics g, ImageObserver view) {
        this.g = g;
        this.view = view;
    }

    @Override
    void draw(int x, int y, float scale, Style myStyle, SlideItem bitmapItem) {
        BufferedImage bufferedImage = ((BitmapItem) bitmapItem).getBufferedImage();

        int width = x + (int) (myStyle.getIndent() * scale);
        int height = y + (int) (myStyle.getLeading() * scale);

        this.g.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth(this.view) * scale),
                (int) (bufferedImage.getHeight(this.view) * scale), this.view);
    }

    @Override
    Rectangle getBoundingBox(float scale, Style myStyle, SlideItem bitmapItem) {
        BufferedImage bufferedImage = ((BitmapItem) bitmapItem).getBufferedImage();

        return new Rectangle((int) (myStyle.getIndent() * scale), 0, (int) (bufferedImage.getWidth(view) * scale),
                ((int) (myStyle.getLeading() * scale)) + (int) (bufferedImage.getHeight(view) * scale));
    }
}
