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

import java.util.ArrayList;

import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideItem;
import br.upe.enenhariasoftware.psw.jabberpoint.view.TextItem;

public class SlideModel {
	
	private TextItem slideTitle;
	private ArrayList<SlideItem> items;
	
	public SlideModel() {
		items = new ArrayList<>();
	}
	
	public void append(SlideItem anItem) {
		items.add(anItem);
	}

	public String getTitle() {
		return slideTitle.getText();
	}

	public void setTitle(String newTitle) {
		slideTitle = new TextItem(0, newTitle);
	}

	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	public SlideItem getSlideItem(int number) {
		return items.get(number);
	}

	public ArrayList<SlideItem> getSlideItems() {
		return new ArrayList<>(items);
	}

	public int getSize() {
		return items.size();
	}

}
