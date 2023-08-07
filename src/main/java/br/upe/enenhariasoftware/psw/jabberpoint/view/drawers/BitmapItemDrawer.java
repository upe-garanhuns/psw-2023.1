package br.upe.enenhariasoftware.psw.jabberpoint.view.drawers;

import br.upe.enenhariasoftware.psw.jabberpoint.model.BitmapItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideItem;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class BitmapItemDrawer extends SlideItemDrawer {
    private final ImageObserver view;
    private final Graphics graphics;

    public BitmapItemDrawer(Graphics graphics, ImageObserver view){
        this.graphics = graphics;
        this.view = view;
    }

    @Override
    public void draw(int x, int y, float scale, Style myStyle, SlideItem bitmapItem) {
        BufferedImage bufferedImage = ((BitmapItem) bitmapItem).getBufferedImage();

        int width = x + (int) (myStyle.getIndent() * scale);
        int height = y + (int) (myStyle.getLeading() * scale);

        this.graphics.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth(this.view) * scale),
                (int) (bufferedImage.getHeight(this.view) * scale), this.view);
    }
    @Override
    public Rectangle getBoundingBox(float scale, Style myStyle, SlideItem bitmapItem) {
        BufferedImage bufferedImage = ((BitmapItem) bitmapItem).getBufferedImage();

        return new Rectangle((int) (myStyle.getIndent() * scale), 0,
                (int) (bufferedImage.getWidth(view) * scale),
                ((int) (myStyle.getLeading() * scale)) + (int) (bufferedImage.getHeight(view) * scale));
    }


}
