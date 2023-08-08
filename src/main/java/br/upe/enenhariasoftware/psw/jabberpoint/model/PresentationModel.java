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

import java.io.Serializable;
import java.util.ArrayList;

import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideViewerComponent;

public class PresentationModel implements Serializable {

	private static final long serialVersionUID = 227L;
	private String title;
	private ArrayList<SlideModel> showList = null;
	private int currentSlideNumber = 0;
	
	public PresentationModel() {
		slideViewComponent = null;
		clear();
	}

	public PresentationModel(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
		clear();
	}

	public void setShowView(SlideViewerComponent slideViewerComponent) {
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

	public int getSlideNumber() {
		return currentSlideNumber;
	}

	public void setSlideNumber(int number) {
		currentSlideNumber = number;
		if (slideViewComponent != null) {
			slideViewComponent.update(this, getCurrentSlide());
		}
	}

	public void prevSlide() {
		if (currentSlideNumber > 0) {
			setSlideNumber(currentSlideNumber - 1);
		}
	}

	public void nextSlide() {
		if (currentSlideNumber < (showList.size() - 1)) {
			setSlideNumber(currentSlideNumber + 1);
		}
	}

	public void clear() {
		showList = new ArrayList<>();
		setSlideNumber(-1);
	}

	public void append(SlideModel slideModel) {
		showList.add(slideModel);
	}

	public SlideModel getSlide(int number) {
		if (number < 0 || number >= getSize()) {
			return null;
		}
		return showList.get(number);
	}

	public SlideModel getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}

	private SlideViewerComponent slideViewComponent = null;

}