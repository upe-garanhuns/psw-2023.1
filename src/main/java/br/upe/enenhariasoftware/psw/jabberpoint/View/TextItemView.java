/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.View;

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
import java.io.Serializable;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.SlideModel;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.StyleModel;

public class TextItemView extends SlideItemView implements Serializable {

  private static final long serialVersionUID = 449L;


  private String text;

  private static final String EMPTYTEXT = "No Text Given";

  public TextItemView(int level, String string) {
    super(level);
    text = string;
  }

  public TextItemView() {
    this(0, EMPTYTEXT);
  }

  public String getText() {
    return text == null ? "" : text;
  }

  public AttributedString getAttributedString(StyleModel style, float scale) {
    AttributedString attrStr = new AttributedString(getText());

    attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());

    return attrStr;
  }

  @Override
  public Rectangle getBoundingBox(Graphics graphic, ImageObserver observer, float scale,
      StyleModel myStyle) {
    List<TextLayout> layouts = getLayouts(graphic, myStyle, scale);

    int xsize = 0;
    int ysize = (int) (myStyle.getLeading() * scale);

    Iterator<TextLayout> iterator = layouts.iterator();

    while (iterator.hasNext()) {
      TextLayout layout = iterator.next();
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

  @Override
  public void draw(int x, int y, float scale, Graphics graphic, StyleModel myStyle, ImageObserver observer) {
    if (text == null || text.length() == 0) {
      return;
    }

    List<TextLayout> layouts = getLayouts(graphic, myStyle, scale);
    Point pen = new Point(x + (int) (myStyle.getIndent() * scale),
        y + (int) (myStyle.getLeading() * scale));

    Graphics2D g2d = (Graphics2D) graphic;
    g2d.setColor(myStyle.getColor());

    Iterator<TextLayout> it = layouts.iterator();

    while (it.hasNext()) {
      TextLayout layout = it.next();

      pen.y += layout.getAscent();
      layout.draw(g2d, pen.x, pen.y);

      pen.y += layout.getDescent();
    }
  }

  private List<TextLayout> getLayouts(Graphics graphic, StyleModel style, float scale) {
    List<TextLayout> layouts = new ArrayList<>();

    AttributedString attrStr = getAttributedString(style, scale);
    Graphics2D g2d = (Graphics2D) graphic;

    FontRenderContext frc = g2d.getFontRenderContext();
    LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

    float wrappingWidth = (SlideModel.WIDTH - style.getIndent()) * scale;

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
