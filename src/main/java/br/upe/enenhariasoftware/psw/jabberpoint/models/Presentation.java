/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.models;

import br.upe.enenhariasoftware.psw.jabberpoint.views.SlideViewer;

import java.io.Serializable;
import java.util.ArrayList;

public class Presentation implements Serializable {

	private String title;
	private transient ArrayList<Slide> showList = null;
	private SlideViewer slideViewComponent = null;
	private int currentSlideNumber = 0;

	public Presentation() {
		slideViewComponent = null;
	}

	public Presentation(SlideViewer slideViewer) {
		this.slideViewComponent = slideViewer;
	}

	public int getSize() {
		return showList.size();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String nt) {
		title = nt;
	}

	public void setShowView(SlideViewer slideViewer) {
		this.slideViewComponent = slideViewer;
	}

	public int getSlideNumber() {
		return currentSlideNumber;
	}

	public void setSlideNumber(int number) {
		currentSlideNumber = number;
		if (slideViewComponent != null && number < getSize()) {
			slideViewComponent.update(this, getCurrentSlide());
		}
	}

	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()) {
			return null;
		}
		return showList.get(number);
	}

	public Slide getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}

	public ArrayList<Slide> getShowList() {
		return showList;
	}

	public void setShowList(ArrayList<Slide> showList) {
		this.showList = showList;
	}
}
