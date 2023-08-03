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
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public abstract class SlideItem {

  // Representa um elemento presente nos slides de apresentação

  private int level = 0;

  protected SlideItem(int lev) {
    level = lev;
  }

  protected SlideItem() {
    this(0);
  }

  public int getLevel() {
    return level;
  }

  public abstract int getStyleIndent(Style myStyle, float scale);

  public abstract int getStyleLeading(Style myStyle, float scale);

  // Corrigir esse método
  // Existe implementação que utiliza o graphics e outra que não
  public abstract Rectangle getBoundingBox(Graphics graphics, ImageObserver observer, float scale,
                                           Style style);

  public abstract void draw(int baseX, int baseY, float scale, Graphics graphics, Style style,
                            ImageObserver observer);

}