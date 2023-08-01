/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class Slide {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;

	protected TextItem title;

	protected ArrayList<SlideItem> items;

	public Slide() {
		items = new ArrayList<>();
	}

	public void append(SlideItem anItem) {
		items.add(anItem);
	}

	public String getTitle() {
		return title.getText();
	}

	public void setTitle(String newTitle) {
		title = new TextItem(0, newTitle);
	}

	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	public SlideItem getSlideItem(int number) {
		return items.get(number);
	}

	public List<SlideItem> getSlideItems() {
		return items;
	}

	public int getSize() {
		return items.size();
	}

	public TextItem getTextItemTitle() {
		return title;
	}
}
