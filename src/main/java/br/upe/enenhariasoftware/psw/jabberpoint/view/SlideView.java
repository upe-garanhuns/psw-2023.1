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
import java.io.Serializable;
import java.util.ArrayList;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Style;
import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideModel;

public class SlideView implements Serializable {

	private static final long serialVersionUID = 227L;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	
	// SlideModel slideModel = new SlideModel();
	private SlideModel slideModel;
	
	public SlideView(SlideModel slideModel) {
		this.slideModel = slideModel;
	}
	
	protected TextItem slideTitle;
	protected ArrayList<SlideItem> items;

	public void draw(Graphics slideGraphics, Rectangle area, ImageObserver view) {
		float scale = getScale(area);

		int y = area.y;

		Style style = Style.getStyle(slideTitle.getLevel());
		slideTitle.draw(area.x, y, scale, slideGraphics, style, view);

		y += slideTitle.getBoundingBox(slideGraphics, view, scale, style).height;

		for (int number = 0; number < slideModel.getSize(); number++) {
			SlideItem slideItem = slideModel.getSlideItems().get(number);
			style = Style.getStyle(slideItem.getLevel());
			slideItem.draw(area.x, y, scale, slideGraphics, style, view);
			y += slideItem.getBoundingBox(slideGraphics, view, scale, style).height;
		}
	}

	private float getScale(Rectangle area) {
		return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
	}
}
