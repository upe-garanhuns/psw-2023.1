/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 *
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Slide implements Serializable, ISlide {
  public static final int WIDTH = 1200;
  public static final int HEIGHT = 800;
  private static final long serialVersionUID = 1L;

  protected transient TextItem title;
  protected transient List<ISlideItem> items;

  public Slide() {
    items = new ArrayList<>();
  }

  public void appendItem(ISlideItem anItem) {
    items.add(anItem);
  }

  public void appendText(int level, String message) {
    appendItem(new TextItem(level, message));
  }

  public void appendBitmap(int level, String imageName) {
    appendItem(new BitmapItem(level, imageName));
  }

  public String getTitle() {
    return title.getText();
  }

  public void setTitle(String newTitle) {
    title = new TextItem(0, newTitle);
  }

  public ISlideItem getSlideItem(int number) {
    return items.get(number);
  }

  public List<ISlideItem> getSlideItems() {
    return items;
  }

  public int getSize() {
    return items.size();
  }

  public float getScale(Rectangle area) {
    float value1 = ((float) area.width) / ((float) WIDTH);
    float value2 =  ((float) area.height) / ((float) HEIGHT);

    return Math.min(value1,value2);
  }

  public int getSlideItemHeight (ISlideItem slideItem, Graphics graphics, ImageObserver view, float scale, Style style){
    return slideItem.getBoundingBox(graphics, view, scale, style).height;
  }

  public Style getStyleItem() {
    return Style.getStyle(title.getLevel());
  }

  public void drawTitle(int x, int y, float scale, Graphics graphics, ImageObserver view) {
    TextItem titleItem = this.title;
    Style style = getStyleItem();
    titleItem.draw(x, y, scale, graphics, style, view);
  }

  public void drawSlideItems(int x, int y, float scale, Graphics graphics, ImageObserver view) {
    drawTitle(x,y,scale,graphics,view);

    Style style = getStyleItem();

    y += getSlideItemHeight(this.title, graphics, view, scale, style);

    for (ISlideItem slideItem : getSlideItems()) {
      style = Style.getStyle(slideItem.getLevel());
      slideItem.draw(x, y, scale, graphics, style, view);
      y += getSlideItemHeight(slideItem, graphics, view, scale, style);
    }
  }

  public void draw(Graphics graphics, Rectangle area, ImageObserver view) {
    float scale = getScale(area);
    int x = area.x;
    int y = area.y;

    drawSlideItems(x,y,scale,graphics,view);
  }

  public int getWidth(){
    return WIDTH;
  }

  public int getHeight(){
    return HEIGHT;
  }

}