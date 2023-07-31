/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 *
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class TextItem extends SlideItem {

  private String text;

  private static final String EMPTYTEXT = "No Text Given";

  public TextItem(int level, String string) {
    super(level);
    text = string;
  }

  public TextItem() {
    this(0, EMPTYTEXT);
  }

  public String getText() {
    return text == null ? "" : text;
  }

  public AttributedString getAttributedString(Style style, float scale) {
    AttributedString attrStr = new AttributedString(getText());

    attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());

    return attrStr;
  }

  @Override
  public int getStyleIndent(Style myStyle, float scale) {
    return (int) (myStyle.getIndent() * scale);
  }

  @Override
  public int getStyleLeading(Style myStyle, float scale) {
    return (int) (myStyle.getLeading() * scale);
  }

  @Override
  public Rectangle getBoundingBox(Graphics graphics, ImageObserver observer, float scale, Style myStyle) {
    List<TextLayout> layouts = getLayouts(graphics, myStyle, scale);

    int x = getStyleIndent(myStyle,scale);
    int y = 0;

    int width = 0;
    int height = getStyleLeading(myStyle,scale);

    for (TextLayout layout : layouts) {
      Rectangle2D bounds = layout.getBounds();

      if (bounds.getWidth() > width) {
        width = (int) bounds.getWidth();
      }
      if (bounds.getHeight() > 0) {
        height += bounds.getHeight();
      }
      height += layout.getLeading() + layout.getDescent();
    }

    return new Rectangle(x, y, width, height);
  }


  @Override
  public void draw(int baseX, int baseY, float scale, Graphics graphics, Style myStyle, ImageObserver o) {
    if (text == null || text.length() == 0) {
      return;
    }
    List<TextLayout> layouts = getLayouts(graphics, myStyle, scale);

    int x = baseX + getStyleIndent(myStyle,scale);
    int y = baseY + getStyleLeading(myStyle,scale);

    Point pen = new Point(x,y);

    Graphics2D g2d = (Graphics2D) graphics;
    g2d.setColor(myStyle.getColor());

    for (TextLayout layout : layouts){
      pen.y += layout.getAscent();
      layout.draw(g2d, pen.x, pen.y);

      pen.y += layout.getDescent();
    }
  }

  private List<TextLayout> getLayouts(Graphics graphics, Style myStyle, float scale) {
    List<TextLayout> layouts = new ArrayList<>();

    AttributedString attributedString = getAttributedString(myStyle, scale);
    Graphics2D g2d = (Graphics2D) graphics;

    FontRenderContext fontRenderContext = g2d.getFontRenderContext();
    LineBreakMeasurer measurer = new LineBreakMeasurer(attributedString.getIterator(), fontRenderContext);

    float wrappingWidth = (Slide.WIDTH - myStyle.getIndent()) * scale;

    while (measurer.getPosition() < getText().length()) {
      TextLayout layout = measurer.nextLayout(wrappingWidth);
      layouts.add(layout);
    }

    return layouts;
  }

  @Override
  public String toString() {
    return "TextItem[" + getLevel() + "," + getText() + "]";
  }

}
