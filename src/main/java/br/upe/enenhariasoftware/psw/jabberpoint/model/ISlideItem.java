package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface ISlideItem {

    int getLevel();

    int getStyleIndent(Style myStyle, float scale);

    int getStyleLeading(Style myStyle, float scale);

    Rectangle getBoundingBox(Graphics graphics, ImageObserver observer, float scale, Style style);

    void draw(int baseX, int baseY, float scale, Graphics graphics, Style style,
              ImageObserver observer);
}
