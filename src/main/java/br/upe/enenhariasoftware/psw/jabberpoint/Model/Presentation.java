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

import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideViewerComponent;

import java.util.ArrayList;

public class Presentation {

	private String title;
	private ArrayList<Slide> showList = null;
	private SlideViewerComponent slideViewComponent = null;
	private int currentSlideNumber = 0;

	public Presentation() {
		slideViewComponent = null;
	}

	public Presentation(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
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

	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	public int getSlideNumber() {
		return currentSlideNumber;
	}

	public void setSlideNumber(int number) {
		currentSlideNumber = number;
		if (slideViewComponent != null) {
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
