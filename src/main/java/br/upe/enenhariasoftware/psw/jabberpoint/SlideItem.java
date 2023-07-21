/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public abstract class SlideItem implements Serializable {

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

	public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);

	public abstract void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);

}
