/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 *
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;

public class Slide implements Serializable {

  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;
  private static final long serialVersionUID = 1L;

  protected transient TextItem title;
  protected transient ArrayList<SlideItem> items;

  public Slide() {
    items = new ArrayList<>();
  }

  public void append(SlideItem anItem) {
    items.add(anItem);
  }

  public void append(int level, String message) {
    append(new TextItem(level, message));
  }

  public String getTitle() {
    return title.getText();
  }

  public void setTitle(String newTitle) {
    title = new TextItem(0, newTitle);
  }

  public SlideItem getSlideItem(int number) {
    return items.get(number);
  }

  public ArrayList<SlideItem> getSlideItems() {
    return items;
  }

  public int getSize() {
    return items.size();
  }

  private float getScale(Rectangle area) {
    float value1 = ((float) area.width) / ((float) WIDTH);
    float value2 =  ((float) area.height) / ((float) HEIGHT);

    return Math.min(value1,value2);
  }

  private int getSlideItemHeight (SlideItem slideItem, Graphics graphics, ImageObserver view, float scale, Style style){
    return slideItem.getBoundingBox(graphics, view, scale, style).height;
  }

  public void draw(Graphics graphics, Rectangle area, ImageObserver view) {
    TextItem textItem = this.title;
    float scale = getScale(area);
    int x = area.x;
    int y = area.y;
    Style style = Style.getStyle(textItem.getLevel());

    textItem.draw(x, y, scale, graphics, style, view);
    y += getSlideItemHeight(textItem, graphics, view, scale, style);

    for (SlideItem slideItem : getSlideItems()) {
      style = Style.getStyle(slideItem.getLevel());
      slideItem.draw(x, y, scale, graphics, style, view);
      y += getSlideItemHeight(slideItem, graphics, view, scale, style);
    }
  }

}