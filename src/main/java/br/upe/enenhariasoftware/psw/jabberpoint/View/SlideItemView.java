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
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import br.upe.enenhariasoftware.psw.jabberpoint.Model.StyleModel;

public abstract class SlideItemView implements Serializable {

  private static final long serialVersionUID = 344L;

  private int level = 0;

  protected SlideItemView(int lev) {
    level = lev;
  }

  protected SlideItemView() {
    this(0);
  }

  public int getLevel() {
    return level;
  }

  public abstract Rectangle getBoundingBox(Graphics graphic, ImageObserver observer, float scale,
      StyleModel style);

  public abstract void draw(int x, int y, float scale, Graphics graphic, StyleModel style,
      ImageObserver observer);

}
