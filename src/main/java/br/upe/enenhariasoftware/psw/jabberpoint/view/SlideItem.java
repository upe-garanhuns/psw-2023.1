/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;

public class SlideItem {

	private int level = 0;

	public SlideItem(int lev) {
		level = lev;
	}

	public SlideItem() {
		this(0);
	}

	public int getLevel() {
		return level;
	}

	public Rectangle getBoundingBox(Graphics slideGraphics, ImageObserver observer, float scale, Style style) {
		return new Rectangle();
	}

	public void draw(int x, int y, float scale, Graphics slideGraphics, Style style, ImageObserver observer) {

	}
}