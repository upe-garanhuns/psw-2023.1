package br.upe.enenhariasoftware.psw.jabberpoint.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public interface SlideItemViewer {
    public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);

    public abstract void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
}